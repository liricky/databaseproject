package cn.liricky.database.controller;

import cn.liricky.database.dao.ClassMapper;
import cn.liricky.database.dao.StudentClassMapper;
import cn.liricky.database.dao.UserMapper;
import cn.liricky.database.model.entity.Class;
import cn.liricky.database.model.entity.ClassExample;
import cn.liricky.database.model.entity.StudentClass;
import cn.liricky.database.model.entity.StudentClassExample;
import cn.liricky.database.model.vo.Result;
import cn.liricky.database.model.vo.resultinfo.SearchClassSuccessInfo;
import cn.liricky.database.tools.JwtTools;
import cn.liricky.database.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class ClassController {
    @Resource
    private ClassMapper classMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentClassMapper studentClassMapper;

    @GetMapping("/searchclass")
    public Result searchClass(HttpServletRequest httpServletRequest, @RequestParam(value = "classId") Integer classId, @RequestParam(value = "teacherId") String teacherId, @RequestParam(value = "userId") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(userid)){
            return ResultTool.error(400,"登录验证信息无效");
        }
        if(StringUtils.isEmpty(teacherId) && classId == null){
            return ResultTool.error(400,"参数数量不足");
        }
        ClassExample classExample = new ClassExample();
        if(StringUtils.isEmpty(teacherId) && classId != null){
            classExample.createCriteria().andClassIdEqualTo(classId);
        } else if(!StringUtils.isEmpty(teacherId) && classId == null){
            classExample.createCriteria().andTeacherIdEqualTo(teacherId);
        } else {
            classExample.createCriteria().andClassIdEqualTo(classId).andTeacherIdEqualTo(teacherId);
        }
        List<Class> classList = classMapper.selectByExample(classExample);
        if(classList.isEmpty()){
            return ResultTool.error(400,"课程不存在");
        }
        List<SearchClassSuccessInfo> searchClassSuccessInfoList = new LinkedList<>();
        for(Class class1 : classList) {
            SearchClassSuccessInfo searchClassSuccessInfo = new SearchClassSuccessInfo();
            searchClassSuccessInfo.setClassid(class1.getClassId());
            searchClassSuccessInfo.setClassname(class1.getClassName());
            searchClassSuccessInfo.setCredit(class1.getCredit());
            searchClassSuccessInfo.setTeachername(userMapper.selectByPrimaryKey(class1.getTeacherId()).getUsername());
            searchClassSuccessInfo.setDepartment(class1.getDepartment());
            searchClassSuccessInfoList.add(searchClassSuccessInfo);
        }
        return ResultTool.success(searchClassSuccessInfoList);
    }

    @PostMapping("/chooseclass")
    public Result chooseclass(HttpServletRequest httpServletRequest, @RequestBody cn.liricky.database.model.request.Class class1){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(class1.getUserId())){
            return ResultTool.error(400,"登录验证信息无效");
        }
        ClassExample classExample = new ClassExample();
        if(StringUtils.isEmpty(class1.getTeacherId()) && class1.getClassid() != null){
            classExample.createCriteria().andClassIdEqualTo(class1.getClassid());
        } else if(!StringUtils.isEmpty(class1.getTeacherId()) && class1.getClassid() == null){
            classExample.createCriteria().andTeacherIdEqualTo(class1.getTeacherId());
        } else {
            classExample.createCriteria().andClassIdEqualTo(class1.getClassid()).andTeacherIdEqualTo(class1.getTeacherId());
        }
        List<Class> classList = classMapper.selectByExample(classExample);
        if(classList.size() == 1){
            Class class2 = classMapper.selectByPrimaryKey(class1.getClassid());
            StudentClass studentClass = new StudentClass();
            studentClass.setClassId(class2.getClassId());
            studentClass.setIsFinished(Boolean.FALSE);
            studentClass.setUserId(userId);
            try {
                studentClassMapper.insertSelective(studentClass);
            } catch(Exception e){
                return ResultTool.error(405,"操作失败");
            }
            return ResultTool.success();
        } else{
            return ResultTool.error(400,"请确定选择的条件正确");
        }
    }

    @PostMapping("/cancelclass")
    public Result cancelclass(HttpServletRequest httpServletRequest, @RequestBody cn.liricky.database.model.request.Class class1){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(class1.getUserId())){
            return ResultTool.error(400,"登录验证信息无效");
        }
        StudentClassExample studentClassExample = new StudentClassExample();
        if(class1.getClassid() != null){
            studentClassExample.createCriteria().andClassIdEqualTo(class1.getClassid());
        } else {
            return  ResultTool.error(400,"参数无效");
        }
        List<StudentClass> studentClassList = studentClassMapper.selectByExample(studentClassExample);
        if(studentClassList.size() == 1){
        try{
            studentClassMapper.deleteByExample(studentClassExample);
        } catch(Exception e){
            return ResultTool.error(405,"操作失败");
        }
            return ResultTool.success();
        } else{
            return ResultTool.error(400,"请确定选择的条件正确");
        }
    }
}

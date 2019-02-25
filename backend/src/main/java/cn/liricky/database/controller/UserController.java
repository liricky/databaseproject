package cn.liricky.database.controller;

import cn.liricky.database.dao.ClassMapper;
import cn.liricky.database.dao.StudentClassMapper;
import cn.liricky.database.dao.UserMapper;
import cn.liricky.database.model.entity.StudentClass;
import cn.liricky.database.model.entity.StudentClassExample;
import cn.liricky.database.model.entity.User;
import cn.liricky.database.model.request.Login;
import cn.liricky.database.model.vo.Result;
import cn.liricky.database.model.vo.resultinfo.LoginSuccessInfo;
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
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentClassMapper studentClassMapper;

    @Resource
    private ClassMapper classMapper;

    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        if(StringUtils.isEmpty(login.getUserId()) || StringUtils.isEmpty(login.getUserPwd())){
            return ResultTool.error(400,"登录信息无效");
        }
        User user = userMapper.selectByPrimaryKey(login.getUserId());
        if(user != null){
            if (user.getPassword().equals(login.getUserPwd())) {
                LoginSuccessInfo loginSuccessInfo = new LoginSuccessInfo();
                loginSuccessInfo.setToken(JwtTools.createJwt(user.getUserId()));
                loginSuccessInfo.setUserAge(user.getAge());
                loginSuccessInfo.setUserDepartment(user.getDepartment());
                loginSuccessInfo.setUserId(user.getUserId());
                loginSuccessInfo.setUserName(user.getUsername());
                loginSuccessInfo.setUserSex(user.getSex());
                loginSuccessInfo.setIdentity(user.getIdentity());
                return ResultTool.success(loginSuccessInfo);
            } else{
                return ResultTool.error(401,"登录密码错误");
            }
        } else{
            return ResultTool.error(401,"登录用户无效");
        }
    }

    @GetMapping("/getclass")
    public Result getclass(HttpServletRequest httpServletRequest, @RequestParam(value = "userId") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(userid)){
            return ResultTool.error(400,"登录验证信息无效");
        }
        List<SearchClassSuccessInfo> searchClassSuccessInfoList = new LinkedList<>();
        StudentClassExample studentClassExample = new StudentClassExample();
        studentClassExample.createCriteria().andUserIdEqualTo(userId);
        List<StudentClass> studentClassList = studentClassMapper.selectByExample(studentClassExample);
        for(StudentClass studentClass: studentClassList){
            SearchClassSuccessInfo searchClassSuccessInfo = new SearchClassSuccessInfo();
            searchClassSuccessInfo.setClassid(studentClass.getClassId());
            searchClassSuccessInfo.setClassname(classMapper.selectByPrimaryKey(studentClass.getClassId()).getClassName());
            searchClassSuccessInfo.setCredit(classMapper.selectByPrimaryKey(studentClass.getClassId()).getCredit());
            searchClassSuccessInfo.setTeachername(userMapper.selectByPrimaryKey(classMapper.selectByPrimaryKey(studentClass.getClassId()).getTeacherId()).getUsername());
            searchClassSuccessInfo.setDepartment(classMapper.selectByPrimaryKey(studentClass.getClassId()).getDepartment());
            searchClassSuccessInfoList.add(searchClassSuccessInfo);
        }

        return ResultTool.success(searchClassSuccessInfoList);
    }
}

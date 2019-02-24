package cn.liricky.database.controller;

import cn.liricky.database.model.vo.Result;
import cn.liricky.database.tools.JwtTools;
import cn.liricky.database.tools.ResultTool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class ClassController {


    @GetMapping("/searchclass")
    public Result searchClass(HttpServletRequest httpServletRequest, @RequestParam(value = "classId") Integer classId, @RequestParam(value = "teacherId") Integer teacherId, @RequestParam(value = "userId") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(userid)){
            return ResultTool.error(400."登录验证信息无效");
        }

    }
}

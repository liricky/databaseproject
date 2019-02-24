package cn.liricky.database.controller;

import cn.liricky.database.dao.UserMapper;
import cn.liricky.database.model.entity.User;
import cn.liricky.database.model.request.Login;
import cn.liricky.database.model.vo.Result;
import cn.liricky.database.model.vo.resultinfo.LoginSuccessInfo;
import cn.liricky.database.tools.JwtTools;
import cn.liricky.database.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserMapper userMapper;

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
                loginSuccessInfo.setIdentity(user.getIdentity());
                return ResultTool.success(loginSuccessInfo);
            } else{
                return ResultTool.error(401,"登录密码错误");
            }
        } else{
            return ResultTool.error(401,"登录用户无效");
        }
    }
}

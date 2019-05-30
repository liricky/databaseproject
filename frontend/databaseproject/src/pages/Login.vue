<template>
    <div class="main">
      <div class="headline">
        <font size="10">学生选课成绩管理系统</font>
      </div>
      <div class="loginBox">
        <br>
        <h1 class="title">登录</h1>
        <br>
        <font size="4">&nbsp;&nbsp;学号：</font>
        <Input v-model="userId" placeholder="请输入学号/工号" prefix="md-contact" style="width: 300px" />
        &nbsp;&nbsp;
        <br>
        <font size="4">&nbsp;&nbsp;密码：</font>
        <Input v-model="userPwd" placeholder="请输入密码" prefix="md-key" style="width: 300px" type="password"/>
        &nbsp;&nbsp;
        <br>
        <RadioGroup v-model="identityGroup" style="position: relative;left: 110px;top: 10px;">
          <Radio label="学生"></Radio>
          <Radio label="老师"></Radio>
          <Radio label="管理员"></Radio>
        </RadioGroup>
        <divider></divider>
        <div class="error-show"><h3 v-show="errorTip">账号或密码错误</h3><h3 v-show="errorTip1">账号、密码和身份不能为空</h3></div>
        <Button class="loginButton" type="primary" @click="login"><h3>登 录</h3></Button>
      </div>
    </div>
</template>
<style>
  .loginBox{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #61fffc;
    box-shadow:10px 20px 50px white inset;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
    border-bottom-left-radius: 20px;
    border-bottom-right-radius: 20px;
    opacity: 0.9;
  }
  .loginButton{
    position: relative;
    left: 50%;
    transform: translate(-50%, -15%);
  }
  .title{
    text-align: center;
  }
  .headline{
    position: absolute;
    left: 50%;
    top:15%;
    transform: translate(-50%, 0);
    opacity: 0.8;
  }
  .main{
    height: 100vh;
    width: 100vw;
    overflow: hidden;
    position: relative;
    background: url("./../assets/loginbackground.png") no-repeat;
  }
  .error-show{
    position: absolute;
    top: 70%;
    left: 50%;
    transform: translate(-50%, 0);
    text-align: center;
  }
</style>
<script>
import axios from 'axios'

export default {
  data () {
    return {
      errorTip: false,
      errorTip1: false,
      userId: '',
      userPwd: '',
      userToken: '',
      userName: '',
      identityGroup: null,
      userIdentity: null,
    }
  },
  methods: {
    login () {
      if (!this.userId || !this.userPwd || !this.identityGroup) {
        this.errorTip = false;
        this.errorTip1 = true
      } else {
        if(this.identityGroup === '学生')
          this.userIdentity = 2;
        else if(this.identityGroup === '老师')
          this.userIdentity = 1;
        else if(this.identityGroup === '管理员')
          this.userIdentity = 0;
        axios({
          url: '/login',
          data: {
            userid: this.userId,
            pwd: this.userPwd,
            userIdentity: this.userIdentity
          },
          headers: {
            'Content-Type': 'application/json'
          },
          method: 'post'
        }).then((response) => {
          let res = response.data
          if (res.code === 200) {
            this.errorTip = false
            this.errorTip1 = false
            if(res.data.identity === 2) {
              var _this = this
              setTimeout(function () {
                _this.$router.push({path: '/selectcourse'})
              }, 1000)
              this.$store.commit('isLogin1', res.data.token)
              this.$store.commit('isLogin2', res.data.userName)
              // this.$store.commit('isLogin3', res.data.userId)
              // this.$store.commit('isLogin4', res.data.userAge)
              // this.$store.commit('isLogin5', res.data.userSex)
              // this.$store.commit('isLogin6', res.data.userDepartment)
              this.$store.commit('setIdentity', res.data.identity)
              this.$Message.info('登录成功！')
            } else if(res.data.identity === 1){
              var _this = this
              //  添加跳转到教师的界面
              setTimeout(function () {
                _this.$router.push({path: '/'})
              }, 1000)
              //  添加教师信息的前端缓存
            } else {
              var _this = this
              setTimeout(function () {
                _this.$router.push({path: '/Manager'})
              }, 1000)
              //  添加管理员信息的前端缓存

            }
          } else {
            this.errorTip1 = false
            this.errorTip = true
          }
        })
      }
    }
  }
}
</script>

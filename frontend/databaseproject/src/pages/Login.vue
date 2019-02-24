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
        <Input v-model="userId" placeholder="请输入学号/工号" style="width: 300px" />
        &nbsp;&nbsp;
        <br>
        <font size="4">&nbsp;&nbsp;密码：</font>
        <Input v-model="userPwd" placeholder="请输入密码" style="width: 300px" type="password"/>
        &nbsp;&nbsp;
        <br>
        <divider></divider>
        <div class="error-show" v-show="errorTip"><h3>账号或密码错误</h3></div>
        <div class="error-show" v-show="errorTip1"><h3>账号和密码不能为空</h3></div>
        <Button class="loginButton" type="primary" @click="login"><h3>登 录</h3></Button>
      </div>
    </div>
</template>
<style scoped>
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
      userName: ''
    }
  },
  methods: {
    login () {
      if (!this.userId || !this.userPwd) {
        this.errorTip1 = true
      } else {
        axios({
          url: '/login',
          data: {
            userId: this.userId,
            userPwd: this.userPwd
          },
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          },
          method: 'post'
        }).then((response) => {
          let res = response.data
          if (res.status === 'success') {
            this.errorTip = false
            var _this = this
            setTimeout(function () {
              _this.$router.push({path: '/HomePage'})
            }, 1000)
            localStorage.setItem('token', res.data.token)
            localStorage.setItem('userName', res.data.userName)
            localStorage.setItem('userId', res.data.userId)
            this.$store.commit('isLogin1', res.data.token)
            this.$store.commit('isLogin2', res.data.userName)
            this.$store.commit('isLogin3', res.data.userId)
            this.$Message.info('登录成功！')
          } else {
            this.errorTip = true
          }
        })
      }
    }
  }
}
</script>

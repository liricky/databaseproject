<template>
  <div class="head-content">
    <div id="word">
      <font size="10" color="white">学生选课成绩管理系统</font>
      <div id="loginbtn">
        <font size="4" v-if="$store.state.token" v-text="$store.state.userName" color="white"></font>
        &nbsp;&nbsp;
        <Button type="primary" shape="circle" @click="jumpLogout">退出</Button>
      </div>
    </div>
    <div id="navigation">
      <Menu id="menu" mode="horizontal" :theme="theme1" active-name="1" @on-select="routerTo">
        <MenuItem id="SelectCourse" name="/SelectCourse">
          <Icon type="ios-book" />
          学生选课
        </MenuItem>
        <MenuItem id="ReportCard" name="/ReportCard">
          <Icon type="ios-document" />
          学生成绩单
        </MenuItem>
        <MenuItem id="Schedule" name="/Schedule">
          <Icon type="ios-calendar" />
          课程表
        </MenuItem>
      </Menu>
    </div>
    <div class="studentdetail">
      <div class="body">
        <div class="box">
          <font size="4">学号： {{this.$store.state.userId}}</font>
        </div>
        <div class="box">
          <font size="4">姓名: {{this.$store.state.userName}}</font>
        </div>
        <div class="box">
          <font size="4">年龄: {{this.$store.state.userAge}}</font>
        </div>
        <div class="box">
          <font size="4">性别: {{this.$store.state.userSex}}</font>
        </div>
        <div>
          <font size="4">所在学院: {{this.$store.state.userDepartment}}</font>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      theme1: 'light'
    }
  },
  methods: {
    jumpLogout(){
        this.$store.commit('isLogout');
        this.$router.push({path: '/Login'});
        this.$Message.info("退出成功！");
    },
    routerTo(name){
      this.$router.push(name)
    }
  }
}
</script>

<style scoped>
  #loginbtn{
    position: absolute;
    right: 2%;
  }
  .head-content{
    position: relative;
    width: 100%;
  }
  #word{
    display: flex;
    align-items: center;
    justify-content: center;
    background: #245086;
  }
  #navigation{
    height: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .body{
    width: 80%;
    margin: auto;
  }
  .box{
    float: left;
    width: 20%;
    text-align: center;
  }
</style>

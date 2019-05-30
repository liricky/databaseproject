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
    <div id="navigation" v-if="this.$store.state.userIdentity === 2">
      <Menu id="menu" mode="horizontal" :theme="theme1" active-name="1" @on-select="routerTo">
        <MenuItem id="SelectCourse" name="/SelectCourse">
          <Icon type="ios-book" />
          学生选课
        </MenuItem>
        <MenuItem id="Grade" name="/grade">
          <Icon type="ios-document" />
          学生成绩单
        </MenuItem>
        <MenuItem id="Schedule" name="/Schedule">
          <Icon type="ios-calendar" />
          课程表
        </MenuItem>
      </Menu>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

export default {
  data () {
    return {
      theme1: 'light'
    }
  },
  methods: {
    jumpLogout(){
      axios({
        url: '/logout',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        method: 'post'
      }).then((response) => {
        let res = response.data
        if (res.code === 200) {
          this.$router.push({path: '/Login'});
          this.$store.commit('isLogout');
          this.$Message.info("退出成功！");
        } else {
          this.$Message.warning("退出失败！");
        }
      })
    },
    routerTo(name){
      this.$router.push(name)
    }
  }
}
</script>

<style>
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
</style>

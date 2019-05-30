<template>
  <div>
    <tophead></tophead>
    <div class="studentdetail">
      <div class="body">
        <font size="4">姓名: {{this.$store.state.userName}}</font>
      </div>
    </div>
    <!--搜索课程和选课框-->
    <div style="position: relative;top: 20px;width: 80%;margin: auto;text-align: center;">
      <div style="margin: auto;"><font size="5"><b>平均分为: {{this.avg}}</b></font></div>
      <!--选课列表-->
      <Card class="main-content">
        <Table border :columns="columns" :data="data" size="large"></Table>
      </Card>
      <!--分页-->
      <Page :total="100" class="split-page"/>
    </div>
  </div>
</template>

<script>
  import tophead from '@/components/Head';
  import axios from 'axios'

  export default {
    name: 'Grade',
    components: {
      tophead
    },
    data() {
      return {
        columns: [
          {
            title: '课程号',
            key: 'classNumber',
            align: 'center',
          },
          {
            title: '课程名',
            key: 'className',
            align: 'center'
          },
          {
            title: '教师名',
            key: 'teacherName',
            align: 'center'
          },
          {
            title: '学分',
            key: 'credit',
            align: 'center'
          },
          {
            title: '开课学院',
            key: 'department',
            align: 'center'
          },
          {
            title: '成绩',
            key: 'grade',
            align: 'center'
          }
        ],
        data: [],
        avg: 0
      }
    },
    mounted() {
      this.initData()
    },
    methods: {
      initData () {
        axios({
          url: '/student/grade',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': this.$store.state.token
          },
          method: 'get',
          params: {
            userId: this.$store.state.userId
          }
        }).then((response) => {
          let res = response.data;
          if (res.code === 200) {
            this.data = res.data.info
            this.avg = res.data.average
          } else {
            this.$Message.error(res.message)
          }
        }).catch((err) => {
          console.error(err)
          this.$Message.error("请检查网络连接！")
        })
      }
    }
  }
</script>

<style>
  .main-content{
    position: fixed;
    border-bottom:500px;
    left:50px;
    right:50px;
    top: 210px;
    bottom: 50px;
  }

  .split-page{
    position: fixed;
    bottom: 10px;
    left:550px;
    width: 100%;
  }

  .body{
    width: 85%;
    margin: auto;
    text-align: center;
  }

  .studentdetail{
    margin: 1% auto;
  }

  .body{
    width: 85%;
    margin: auto;
  }
</style>

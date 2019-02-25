<template>
    <div>
      <tophead></tophead>
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
            <font size="4">学院: {{this.$store.state.userDepartment}}</font>
          </div>
        </div>
      </div>
      <div class="schedule">
        <divider></divider>
        <h2 v-if="status1 === 'fail'">暂无课程</h2>
        <Row class="cardbox" style="background:#eee;padding:20px" v-if="status1 === 'success'">
          <div class="box">
            <font size="4">课程号</font>
          </div>
          <div class="box">
            <font size="4">课程名称</font>
          </div>
          <div class="box">
            <font size="4">教师名称</font>
          </div>
          <div class="box">
            <font size="4">学分</font>
          </div>
          <div>
            <font size="4">学院</font>
          </div>
          <br>
          <Col class="cardcol" span="25" v-for="lesson in lessons">
            <Card class="card" :bordered="true">
              <div class="box">
                <font size="2">{{lesson.classid}}</font>
              </div>
              <div class="box">
                <font size="2">{{lesson.classname}}</font>
              </div>
              <div class="box">
                <font size="2">{{lesson.teachername}}</font>
              </div>
              <div class="box">
                <font size="2">{{lesson.credit}}</font>
              </div>
              <div>
                <font size="2">{{lesson.department}}</font>
              </div>
            </Card>
          </Col>
        </Row>
      </div>
    </div>
</template>
<style scoped>
  .card{
    margin: auto;
    border: 1px solid black;
  }
  .cardcol{
    margin: 4px auto;
  }
  .box{
    float: left;
    width: 20%;
    text-align: center;
  }
  .schedule{
    width: 80%;
    margin: auto;
  }
  .body{
    width: 85%;
    margin: auto;
  }
  .box{
    float: left;
    width: 20%;
    text-align: center;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import axios from 'axios'

  export default {
    data () {
      return {
        status1: '',
        errormsg1: '',
        lessons: []
      }
    },
    components: {
      tophead
    },
    created(){
      this.getclass();
    },
    methods: {
      getclass(){
        axios({
          url: '/getclass',
          method: 'get',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': this.$store.state.token
          },
          params: {
            userId: this.$store.state.userId,
          }
        }).then((response) => {
          let res = response.data;
          if(res.code === 200){
            this.$set(this,'lessons',res.data);
            if(this.lessons.length === 0){
              this.status1 = 'fail';
            } else {
              this.status1 = 'success';
            }
          } else{
            this.errormsg1 = res.message;
          }
        })
      },
    }
  }
</script>

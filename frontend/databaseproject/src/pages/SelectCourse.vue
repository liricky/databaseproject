<template>
    <div>
      <tophead></tophead>
      <div class="body">
        <divider></divider>
        <div class="topbody">
          <font size="4">&nbsp;&nbsp;课程号：</font>
          <Input v-model="classId" placeholder="请输入课程号" style="width: 300px" />
          &nbsp;&nbsp;&nbsp;&nbsp;
          <font size="4">&nbsp;&nbsp;教师号：</font>
          <Input v-model="teacherId" placeholder="请输入教师号" style="width: 300px" />
          <br>
          <Button class="Button" type="primary" @click="search"><h3>搜 索</h3></Button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <Button class="Button" type="primary" @click="select"><h3>选 课</h3></Button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <Button class="Button" type="primary" @click="cancel"><h3>退 课</h3></Button>
          <font size="5"> </font>
          <br>
          <font size="4" v-if="status2 === 'success'">选课成功！</font>
          <font size="4" v-if="status3 === 'success'">退课成功！</font>
        </div>
        <br>
        <div class="bottombody">
          <Row class="cardbox" style="background:#eee;padding:20px" v-if="status1 === 'success'">
            <div class="box">
              <font size="4">课程号</font>
            </div>
            <div class="box">
              <font size="4">课程名称</font>
            </div>
            <div class="box">
              <font size="4">教师号</font>
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
    </div>
</template>
<style scoped>
  .body{
    width: 80%;
    margin: auto;
  }
  .topbody{
    background: #42e1ff;
    box-shadow:10px 10px 30px white inset;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    text-align: center;
  }
  .Button{
    position: relative;
    transform: translate(0, -10%);
  }
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
</style>
<script>
import tophead from '@/components/Head'
import axios from 'axios'

export default {
  data () {
    return {
      classId: '',
      teacherId: '',
      // lessons: [
      //   {
      //     classid: 12345678,
      //     classname: '数据库',
      //     teachername: '李晓强',
      //     credit: 4,
      //     department: '计算机学院'
      //   },
      //   {
      //     classid: 12345678,
      //     classname: '数据库',
      //     teachername: '李晓强',
      //     credit: 4,
      //     department: '计算机学院'
      //   }
      // ],
      lesson: [],
      status1: '',
      errormsg1: '',
      status2: '',
      errormsg2: '',
      status3: '',
      errormsg3: '',
    }
  },
  components: {
    tophead
  },
  method: {
    searchclass(){
      axios({
        url: '/searchclass',
        method: 'get',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': this.$store.state.token
        },
        param: {
          userId: this.$store.state.userId,
          classId: this.classId,
          teacherId: this.teacherId
        }
      }).then((response) => {
        let res = response.data;
        if(res.code === 200){
          this.lessons = res.data;
          this.status1 = 'success';
        } else{
          this.errormsg1 = res.message;
        }
      })
    },
    chooseclass(){
      axios({
        url: '/chooseclass',
        method: 'post',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': this.$store.state.token
        },
        data: {
          userId: this.$store.state.userId,
          classId: this.classId,
          teacherId: this.teacherId
        }
      }).then((response) => {
        let res = response.data;
        if(res.code === 200){
          this.status2 = 'success';
        } else{
          this.errormsg2 = res.message;
        }
      })
    },
    cancelclass(){
      axios({
        url: '/cancelclass',
        method: 'post',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': this.$store.state.token
        },
        data: {
          userId: this.$store.state.userId,
          classId: this.classId,
          teacherId: this.teacherId
        }
      }).then((response) => {
        let res = response.data;
        if(res.code === 200){
          this.status3 = 'success';
        } else{
          this.errormsg3 = res.message;
        }
      })
    }
  }
}
</script>

<template>
    <div>
      <tophead></tophead>
      <div class="studentdetail">
        <div class="body1">
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
      <div class="body">
        <divider></divider>
        <div class="topbody">
          <font size="4">&nbsp;&nbsp;课程号：</font>
          <Input v-model="classId" placeholder="请输入课程号" style="width: 300px" />
          &nbsp;&nbsp;&nbsp;&nbsp;
          <font size="4">&nbsp;&nbsp;教师号：</font>
          <Input v-model="teacherId" placeholder="请输入教师号" style="width: 300px" />
          <br>
          <Button class="Button" type="primary" @click="searchclass"><h3>搜 索</h3></Button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <Button class="Button" type="primary" @click="chooseclass"><h3>选 课</h3></Button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <Button class="Button" type="primary" @click="cancelclass"><h3>退 课</h3></Button>
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
              <font size="4">教师名称</font>
            </div>
            <div class="box">
              <font size="4">学分</font>
            </div>
            <div>
              <font size="4">学院</font>
            </div>
            <br>
            <Col class="cardcol" span="25" v-for="lesson in lessons" :key="lesson.classId">
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
  .body1{
    width: 85%;
    margin: auto;
  }
  .box{
    float: left;
    width: 20%;
    text-align: center;
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
      lessons: [],
      status1: '',
      status2: '',
      status3: '',
    }
  },
  components: {
    tophead
  },
  methods: {
    searchclass(){
      if(this.classId === '' && this.teacherId === ''){
        this.$Message.info('请输入课程号或教师号来查询课程');
      } else {
        if((this.isnumber(this.teacherId) || this.teacherId === '') && (this.classId === '' || this.isnumber(this.classId))) {
          axios({
            url: '/searchclass',
            method: 'get',
            headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              'Authorization': this.$store.state.token
            },
            params: {
              userId: this.$store.state.userId,
              classId: this.classId || 1,
              teacherId: this.teacherId || 'a'
            }
          }).then((response) => {
            let res = response.data;
            if (res.code === 200) {
              this.$set(this, 'lessons', res.data);
              this.status1 = 'success';
              this.status2 = '';
              this.status3 = '';
              this.$Message.info('搜索成功');
              this.classId = '';
              this.teacherId = '';
            } else {
              if (res.message === '课程不存在') {
                this.$Message.info('搜索的课程不存在');
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              } else {
                this.$Message.info('搜索失败');
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              }
            }
          })
        } else{
          this.$Message.info('请输入符合类型要求的课程号或教师号来搜索课程');
          this.classId = '';
          this.teacherId = '';
          this.status2 = '';
          this.status1 = '';
          this.status3 = '';
        }
      }
    },
    chooseclass(){
      if(this.classId === ''){
        this.$Message.info("请输入课程号来完成选课");
      } else {
        if((this.isnumber(this.teacherId) || this.teacherId === '') && (this.classId === '' || this.isnumber(this.classId))) {
          axios({
            url: '/chooseclass',
            method: 'post',
            headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              'Authorization': this.$store.state.token
            },
            data: {
              userId: this.$store.state.userId,
              classId: this.classId || 1,
              teacherId: this.teacherId || 'a'
            }
          }).then((response) => {
            let res = response.data;
            if (res.code === 200) {
              this.status2 = 'success';
              this.status1 = '';
              this.status3 = '';
              this.$Message.info('选课成功');
              this.classId = '';
              this.teacherId = '';
            } else {
              if (res.message === '已经选择了该门课程') {
                this.$Message.info(res.message);
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              } else if (res.message === '选修的课程不存在') {
                this.$Message.info(res.message);
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              } else {
                this.$Message.info('选课失败');
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              }
            }
          })
        } else{
          this.$Message.info('请输入符合类型要求的课程号或教师号来选择课程');
          this.classId = '';
          this.teacherId = '';
          this.status2 = '';
          this.status1 = '';
          this.status3 = '';
        }
      }
    },
    cancelclass(){
      if(this.classId === ''){
        this.$Message.info("请输入课程号来完成退课");
      } else {
        if(this.teacherId === '' && (this.classId === '' || this.isnumber(this.classId))) {
          axios({
            url: '/cancelclass',
            method: 'post',
            headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              'Authorization': this.$store.state.token
            },
            data: {
              userId: this.$store.state.userId,
              classId: this.classId || 1,
              teacherId: this.teacherId || 'a'
            }
          }).then((response) => {
            let res = response.data;
            if (res.code === 200) {
              this.status3 = 'success';
              this.status1 = '';
              this.status2 = '';
              this.$Message.info('退课成功');
              this.classId = '';
              this.teacherId = '';
            } else {
              if (res.message === '该门课程尚未选修') {
                this.$Message.info(res.message);
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              } else {
                this.$Message.info('退课失败');
                this.teacherId = '';
                this.classId = '';
                this.status2 = '';
                this.status1 = '';
                this.status3 = '';
              }
            }
          })
        } else{
          this.$Message.info('请输入符合类型要求的课程号来退课');
          this.classId = '';
          this.teacherId = '';
          this.status2 = '';
          this.status1 = '';
          this.status3 = '';
        }
      }
    },
    isnumber(number){
      var reg=/^\d{8}$/;
      return reg.test(number);
    }
  }
}
</script>

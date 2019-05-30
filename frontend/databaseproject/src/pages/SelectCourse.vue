<template>
  <div>
    <tophead></tophead>
    <div class="studentdetail">
      <div class="body1">
        <font size="4">姓名: {{this.$store.state.userName}}</font>
      </div>
    </div>
    <div class="body">
      <divider></divider>
      <div class="topbody">
        <br>
        <font size="4">课程号：</font>
        <Input v-model="classId" placeholder="请输入课程号" style="width: 300px"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <font size="4">教师号：</font>
        <Input v-model="teacherId" placeholder="请输入教师号" style="width: 300px"/>
        <br>
        <Button style="margin: 1% auto auto auto;" class="Button" type="primary" @click="searchclass"><h3>搜 索</h3>
        </Button>
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
          <div>
            <font size="4">学院</font>
          </div>
          <div>
            <font size="4">教师</font>
          </div>
          <div class="box">
            <font size="4">学分</font>
          </div>
          <div class="box">
            <font size="4">成绩比例</font>
          </div>
          <br>
          <Col class="cardcol" span="25" v-for="lesson in lessons" :key="lesson.id">
            <Card class="card" :bordered="true">
              <div class="box">
                <h3>{{lesson.course.id}}</h3>
              </div>
              <div class="box">
                <h3>{{lesson.course.name}}</h3>
              </div>
              <div>
                <h3>{{lesson.course.department.name}}</h3>
              </div>
              <div class="box">
                <h3>{{lesson.teacher.name}}</h3>
              </div>
              <div class="box">
                <h3>{{lesson.course.credit}}</h3>
              </div>
              <div class="box">
                <h3>{{lesson.course.ratio}}</h3>
              </div>
              <div style="text-align:center;margin: auto;">
                <Button v-if="!lesson.isDelete" style="margin: 3px auto 0 auto;" class="Button" type="primary" @click="chooseclass(lesson.id)"><h4>选 课</h4></Button>
                <Button v-if="!lesson.isDelete" style="margin: 3px auto 0 auto;" class="Button" type="primary" @click="cancelclass(lesson.id)"><h4>退 课</h4></Button>
              </div>
            </Card>
          </Col>
        </Row>
      </div>
    </div>
  </div>
</template>
<style>
  .body {
    width: 80%;
    margin: auto;
  }

  .body1 {
    width: 85%;
    margin: auto;
    text-align: center;
  }

  .box {
    float: left;
    width: 16%;
    text-align: center;
  }

  .topbody {
    background: #42e1ff;
    box-shadow: 10px 10px 30px white inset;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    text-align: center;
  }

  .Button {
    position: relative;
    transform: translate(0, -10%);
  }

  .card {
    margin: auto;
    border: 1px solid black;
  }

  .cardcol {
    margin: 2px auto;
  }

  .studentdetail {
    margin: 1% auto;
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
      searchclass () {
        axios({
          url: '/student/searchclass',
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
          let res = response.data
          if (res.code === 200) {
            this.$set(this, 'lessons', res.data)
            this.status1 = 'success'
            this.status2 = ''
            this.status3 = ''
            this.$Message.info('搜索成功')
            this.classId = ''
            this.teacherId = ''
          } else {
            if (res.message === '课程不存在') {
              this.$Message.info('搜索的课程不存在')
              this.teacherId = ''
              this.classId = ''
              this.status2 = ''
              this.status1 = ''
              this.status3 = ''
            } else {
              this.$Message.info('搜索失败')
              this.teacherId = ''
              this.classId = ''
              this.status2 = ''
              this.status1 = ''
              this.status3 = ''
            }
          }
        })
      },
      chooseclass (classId) {
        axios({
          url: '/student/chooseclass',
          method: 'post',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': this.$store.state.token
          },
          data: {
            userId: this.$store.state.userId,
            classId: classId,
          }
        }).then((response) => {
          let res = response.data
          if (res.code === 200) {
            this.status2 = 'success'
            this.status1 = ''
            this.status3 = ''
            this.$Message.info('选课成功')
            this.classId = ''
            this.teacherId = ''
          } else {
            this.$Message.warning()
            // 返回的报错提示
          }
        })
      },
      cancelclass (classId) {
        axios({
          url: '/student/cancelclass',
          method: 'post',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': this.$store.state.token
          },
          data: {
            userId: this.$store.state.userId,
            classId: classId,
          }
        }).then((response) => {
          let res = response.data
          if (res.code === 200) {
            this.status3 = 'success'
            this.status1 = ''
            this.status2 = ''
            this.$Message.info('退课成功')
            this.classId = ''
            this.teacherId = ''
          } else {
            this.$Message.warning()
            // 返回的报错提示
          }
        })
      },
      isnumber (number) {
        var reg = /^\d*$/
        return reg.test(number)
      }
    }
  }
</script>

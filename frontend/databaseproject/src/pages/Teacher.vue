<template>
  <div>
    <Head></Head>
    <div class="main">
      <Tabs value="tabs">
        <TabPane label="成绩管理" name="score">
          <div>
            <Select v-model="classModel" style="width:300px">
              <Option v-for="classes in classList" :value="classes.name" :key="classes.id">{{classes.name}}</Option>
            </Select>

          </div>
        </TabPane>
        <TabPane label="课程管理" name="course">
          <Table border :columns="columns1" :data="classList"></Table>
        </TabPane>
        <TabPane label="成绩统计" name="statics">
          <Button type="success" @click="gradeInfo">查看成绩分布情况</Button>
          <Modal v-model="modal1" title="成绩分布情况" class="grade-info">
            <div id="echartContainer" style="width:500px; height:500px"></div>
          </Modal>
        </TabPane>
      </Tabs>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import Head from './../components/Head'
  import echarts from 'echarts'

  export default {
    name: 'Teacher',
    data() {
      return {
        classList: null,
        modal1: null,
        columns1: [
          {
            title: '课程号',
            key: 'id'
          },
          {
            title: '课程名称',
            key: 'name'
          },
          {
            title: 'Action',
            key: 'action',
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                  },
                  style: {
                  },
                  on: {
                    click: () => {
                      this.openclass(params.row.id)
                    }
                  }
                }, '开课'),
                h('Button', {
                  props: {
                    type: 'primary',
                  },
                  style: {
                  },
                  on: {
                    click: () => {
                      this.closeclass(params.row.id)
                    }
                  }
                }, '闭课')
              ]);
            }
          }
        ],
      }
    },
    components: {
      Head
    },
    methods: {
      gradeInfo() {
        axios({
          url: '/teacher/gradeList',
          method: 'get',
          params: {

          }
        }).then((res) => {
          if(res.data.code === 200) {
            console.log(res.data.data)
            this.xData = res.data.data
            console.log(this.xData)
            this.$Message.success('数据加载成功')
          } else {
            this.$Message.warning(res.message)
          }
        }).then(() => {
          var myChart = echarts.init(document.getElementById('echartContainer'));
          // 绘制图表
          myChart.setOption({
            title: { text: '课程成绩情况' },
            tooltip: {},
            xAxis: {
              data: ["<60","60-69","70-79","80-89","90-99"]
            },
            yAxis: {},
            series: [{
              name: '人数',
              type: 'bar',
              data: this.xData
            }]
          });
          this.modal1 = true
        })
      },
      openclass(id) {
        axios({
          url: '',
          method: 'post',
          data: {
            classid: id
          },
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
            'Authorization': this.$store.state.token
          }
        }).then((res) => {
          if(res.data.code === 200) {
            this.$Message.success('开设课程成功')
          } else {
            this.$Message.error('开设课程失败')
          }
        })
      },
      closeclass(id) {
        axios({
          url: '',
          method: 'post',
          data: {
            classid: id
          },
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
            'Authorization': this.$store.state.token
          }
        }).then((res) => {
          if(res.data.code === 200) {
            this.$Message.success('开设课程成功')
          } else {
            this.$Message.error('开设课程失败')
          }
        })
      }
    }
  }
</script>

<style>
  .main{
    width: 80%;
    margin: 1% auto;
  }
</style>

<template>
  <div>
    <Head></Head>
    <div class="mamain">
      <Tabs value="tabs">
        <TabPane label="学生管理" name="student">
          <div>
            <Button type="primary" @click="showStuDrawer"><h3>添加学生</h3></Button>
            <Drawer width="70" title="创建新学生" :closable="false" v-model="stuDrawer">
              <Form ref="stuForm" :model="stuForm" :label-width="100">
                <FormItem label="姓名" prop="name">
                  <Input type="text" v-model="stuForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="stuForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="性别" prop="sex">
                  <Select v-model="stuForm.sex" style="width:300px">
                    <Option :value="男">男</Option>
                    <Option :value="女">女</Option>
                  </Select>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="stuForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="密码" prop="password">
                  <Input type="password" v-model="stuForm.password" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('stuForm')">创建</Button>
                  <Button @click="handleReset('stuForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Drawer>
            <Table border :columns="columns1" :data="studentList"></Table>
            <Modal
              v-model="stuModal"
              title="修改学生信息"
              @on-ok="ok"
              @on-cancel="cancel">
              <Form ref="stuForm" :model="stuForm" :label-width="100">
                <FormItem label="姓名" prop="name">
                  <Input type="text" v-model="stuForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="stuForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="性别" prop="sex">
                  <Select v-model="stuForm.sex" style="width:300px">
                    <Option :value="男">男</Option>
                    <Option :value="女">女</Option>
                  </Select>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="stuForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="密码" prop="password">
                  <Input type="password" v-model="stuForm.password" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('stuForm')">修改</Button>
                  <Button @click="handleReset('stuForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Modal>
          </div>
        </TabPane>
        <TabPane label="教师管理" name="teacher">
          <div>
            <Button type="primary" @click="showTeaDrawer"><h3>添加教师</h3></Button>
            <Drawer width="70" title="创建新教师" :closable="false" v-model="teaDrawer">
              <Form ref="teaForm" :model="teaForm" :label-width="100">
                <FormItem label="姓名" prop="name">
                  <Input type="text" v-model="teaForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="teaForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="性别" prop="sex">
                  <Select v-model="teaForm.sex" style="width:300px">
                    <Option :value="男">男</Option>
                    <Option :value="女">女</Option>
                  </Select>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="teaForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="密码" prop="password">
                  <Input type="password" v-model="teaForm.password" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('teaForm')">创建</Button>
                  <Button @click="handleReset('teaForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Drawer>
            <Table border :columns="columns2" :data="teacherList"></Table>
            <Modal
              v-model="teaModal"
              title="修改教师信息"
              @on-ok="ok"
              @on-cancel="cancel">
              <Form ref="teaForm" :model="teaForm" :label-width="100">
                <FormItem label="姓名" prop="name">
                  <Input type="text" v-model="teaForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="teaForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="性别" prop="sex">
                  <Select v-model="teaForm.sex" style="width:300px">
                    <Option :value="男">男</Option>
                    <Option :value="女">女</Option>
                  </Select>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="teaForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="密码" prop="password">
                  <Input type="password" v-model="teaForm.password" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('teaForm')">修改</Button>
                  <Button @click="handleReset('teaForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Modal>
          </div>
        </TabPane>
        <TabPane label="学院管理" name="department">
          <div>
            <Button type="primary" @click="showDepDrawer"><h3>添加学院</h3></Button>
            <Drawer width="70" title="创建新学院" :closable="false" v-model="depDrawer">
              <Form ref="depForm" :model="depForm" :label-width="100">
                <FormItem label="姓名" prop="name">
                  <Input type="text" v-model="depForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="depForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('depForm')">创建</Button>
                  <Button @click="handleReset('depForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Drawer>
            <Table border :columns="columns3" :data="departmentList"></Table>
            <Modal
              v-model="depModal"
              title="修改学院信息"
              @on-ok="ok"
              @on-cancel="cancel">
              <Form ref="teaForm" :model="depForm" :label-width="100">
                <FormItem label="学院名称" prop="name">
                  <Input type="text" v-model="depForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="depForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('depForm')">修改</Button>
                  <Button @click="handleReset('depForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Modal>
          </div>
        </TabPane>
        <TabPane label="课程管理" name="course">
          <div>
            <Button type="primary" @click="showCouDrawer"><h3>添加课程</h3></Button>
            <Drawer width="70" title="创建新课程" :closable="false" v-model="couDrawer">
              <Form ref="couForm" :model="couForm" :label-width="100">
                <FormItem label="课程名称" prop="name">
                  <Input type="text" v-model="couForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="couForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="学分" prop="credit">
                  <Input type="text" v-model="couForm.credit" style="width:300px"></Input>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="couForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="成绩比例" prop="ratio">
                  <Input type="text" v-model="couForm.ratio" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('couForm')">创建</Button>
                  <Button @click="handleReset('couForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Drawer>
            <Table border :columns="columns4" :data="courseList"></Table>
            <Modal
              v-model="couModal"
              title="更新课程信息"
              @on-ok="ok"
              @on-cancel="cancel">
              <Form ref="couForm" :model="couForm" :label-width="100">
                <FormItem label="课程名称" prop="name">
                  <Input type="text" v-model="couForm.name" style="width:300px"></Input>
                </FormItem>
                <FormItem label="学院" prop="department">
                  <Select v-model="couForm.department" style="width:300px">
                    <Option v-for="dep in departmentList" :key="dep.id" :value="dep.name">{{dep.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="学分" prop="credit">
                  <Input type="text" v-model="couForm.credit" style="width:300px"></Input>
                </FormItem>
                <FormItem label="逻辑删除状态" prop="sex">
                  <Select v-model="couForm.isDelete" style="width:300px">
                    <Option :value="true">逻辑删除</Option>
                    <Option :value="false">解除逻辑删除</Option>
                  </Select>
                </FormItem>
                <FormItem label="成绩比例" prop="ratio">
                  <Input type="text" v-model="couForm.ratio" style="width:300px"></Input>
                </FormItem>
                <FormItem>
                  <Button type="primary" @click="handleSubmit('couForm')">创建</Button>
                  <Button @click="handleReset('couForm')" style="margin-left: 8px">重置</Button>
                </FormItem>
              </Form>
            </Modal>
          </div>
        </TabPane>
      </Tabs>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import Head from './../components/Head'

  export default {
    name: 'Manager',
    data(){
      return{
        stuModal: false,
        stuDrawer: false,
        depDrawer: false,
        couDrawer: false,
        teaDrawer: false,
        studentList: null,
        teacherList: null,
        tempId: null,
        departmentList: null,
        courseList: null,
        stuForm: {
          name: "",
          department: "",
          sex: "",
          isDelete: "",
          password: ""
        },
        teaForm: {
          name: "",
          department: "",
          sex: "",
          isDelete: "",
          password: ""
        },
        depForm: {
          name: "",
          isDelete: ""
        },
        couForm: {
          name: "",
          department: "",
          credit: "",
          isDelete: "",
          ratio: ""
        },
        columns1: [
          {
            title: '姓名',
            key: 'name',
            render: (h, params) => {
              return h('div', [
                h('strong', params.row.name)
              ]);
            }
          },
          {
            title: '学号',
            key: 'id'
          },
          {
            title: '学院',
            key: 'department.name'
          },
          {
            title: '性别',
            key: 'sex'
          },
          {
            title: '逻辑删除状态',
            key: 'isDelete'
          },
          {
            title: '操作',
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
                      this.changeStu(params.row.id)
                    }
                  }
                }, '修改')
              ]);
            }
          }
        ],
        columns2: [
          {
            title: '姓名',
            key: 'name',
            render: (h, params) => {
              return h('div', [
                h('strong', params.row.name)
              ]);
            }
          },
          {
            title: '工号',
            key: 'id'
          },
          {
            title: '学院',
            key: 'department.name'
          },
          {
            title: '性别',
            key: 'sex'
          },
          {
            title: '逻辑删除状态',
            key: 'isDelete'
          },
          {
            title: '操作',
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
                      this.changeTea(params.row.id)
                    }
                  }
                }, '修改')
              ]);
            }
          }
        ],
        columns3: [
          {
            title: '学院名称',
            key: 'name',
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('strong', params.row.name)
              ]);
            }
          },
          {
            title: '逻辑删除状态',
            key: 'isDelete',
            align: 'center',
          },
          {
            title: '操作',
            key: 'action',
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                  },
                  on: {
                    click: () => {
                      this.changeDep(params.row.id)
                    }
                  }
                }, '修改')
              ]);
            }
          }
        ],
        columns4: [
          {
            title: '课程名',
            key: 'name',
            render: (h, params) => {
              return h('div', [
                h('strong', params.row.name)
              ]);
            }
          },
          {
            title: '课程号',
            key: 'id'
          },
          {
            title: '学院',
            key: 'department.name'
          },
          {
            title: '学分',
            key: 'credit'
          },
          {
            title: '逻辑删除状态',
            key: 'isDelete'
          },
          {
            title: '成绩比例',
            key: 'ratio'
          },
          {
            title: '操作',
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
                      this.changeCou(params.row.id)
                    }
                  }
                }, '修改')
              ]);
            }
          }
        ],
      }
    },
    components: {
      Head
    },
    created() {
      this.getdata();
    },
    methods: {
      getdata() {

        // axios({
        //   url: '/admin/student',
        //   headers: {
        //     'Content-Type': 'application/json'
        //   },
        //   method: 'get',
        // }).then((response) => {
        //   let res = response.data
        //   if (res.code === 200) {
        //     console.log(res.data)
        //     this.studentList = res.data;
        //   } else {
        //     this.$Message.warning('获取学生信息失败')
        //   }
        // });
        // axios({
        //   url: '/admin/teacher',
        //   headers: {
        //     'Content-Type': 'application/json'
        //   },
        //   method: 'get',
        // }).then((response) => {
        //   let res = response.data
        //   if (res.code === 200) {
        //     console.log(res.data)
        //     this.teacherList = res.data;
        //   } else {
        //     this.$Message.warning('获取教师信息失败')
        //   }
        // });
        axios({
          url: '/admin/department',
          headers: {
            'Content-Type': 'application/json'
          },
          method: 'get',
        }).then((response) => {
          let res = response.data
          if (res.code === 200) {
            this.departmentList = res.data;
            console.log(typeof this.departmentList)
          } else {
            this.$Message.warning('获取院系信息失败')
          }
        });
        // axios({
        //   url: '/admin/course',
        //   headers: {
        //     'Content-Type': 'application/json'
        //   },
        //   method: 'get',
        // }).then((response) => {
        //   let res = response.data
        //   if (res.code === 200) {
        //     console.log(res.data)
        //     this.courseList = res.data;
        //   } else {
        //     this.$Message.warning('获取课程信息失败')
        //   }
        // })
      },
      changeStu(id) {
        this.stuModal = true;
        this.tempId = id;
        this.stuForm.name = "";
        this.stuForm.sex = "";
        this.stuForm.department = "";
        this.stuForm.isDelete = "";
        this.stuForm.password = "";
      },
      changeTea(id) {
        this.teaModal = true;
        this.tempId = id;
        this.teaForm.name = "";
        this.teaForm.sex = "";
        this.teaForm.department = "";
        this.teaForm.isDelete = "";
        this.teaForm.password = "";
      },
      changeDep(id) {
        this.depModal = true;
        this.tempId = id;
        this.depForm.name = "";
        this.depForm.isDelete = "";
      },
      changeCou(id) {
        this.couModal = true;
        this.tempId = id;
        this.couForm.name = "";
        this.couForm.department = "";
        this.couForm.credit = "";
        this.couForm.isDelete = "";
        this.couForm.ratio = "";
      },
      handleSubmit (name) {
        if(name === 'stuForm'){

        } else if(name === 'teaForm'){

        } else if(name === 'depForm'){

        } else if(name === 'couForm'){

        }
      },
      showStuDrawer() {
        this.stuDrawer = true;
        this.stuForm.name = "";
        this.stuForm.sex = "";
        this.stuForm.department = "";
        this.stuForm.isDelete = "";
        this.stuForm.password = "";
      },
      showTeaDrawer() {
        this.teaDrawer = true;
        this.teaForm.name = "";
        this.teaForm.sex = "";
        this.teaForm.department = "";
        this.teaForm.isDelete = "";
        this.teaForm.password = "";
      },
      showDepDrawer() {
        this.depDrawer = true;
        this.depForm.name = "";
        this.depForm.isDelete = "";
      },
      showCouDrawer() {
        this.couDrawer = true;
        this.couForm.name = "";
        this.couForm.department = "";
        this.couForm.credit = "";
        this.couForm.isDelete = "";
        this.couForm.ratio = "";
      }
    }
  }
</script>

<style>
  .mamain{
    width: 80%;
    margin: 1% auto;
  }

  .ivu-tabs-tab{font-size: 20px;display:inline-block;height:100%;padding:8px 16px;margin-right:16px;-webkit-box-sizing:border-box;box-sizing:border-box;cursor:pointer;text-decoration:none;position:relative;-webkit-transition:color .3s ease-in-out;transition:color .3s ease-in-out}
</style>

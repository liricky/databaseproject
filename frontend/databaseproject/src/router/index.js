import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '',
      redirect: {name: 'login'}
      // redirect: {name: 'selectcourse'}
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/pages/Login')
    },
    {
      path: '/selectcourse',
      name: 'selectcourse',
      component: () => import('@/pages/SelectCourse')
    }
  ]
})

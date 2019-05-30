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
    },
    {
      path: '/schedule',
      name: 'schedule',
      component: () => import('@/pages/Schedule')
    },
    {
      path: '/grade',
      name: 'grade',
      component: () => import('@/pages/Grade')
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('@/pages/Manager')
    },
    {
      path: '/teacher',
      name: 'teacher',
      component: () => import('@/pages/Teacher')
    },
  ]
})

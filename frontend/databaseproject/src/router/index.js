import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '',
      redirect: {name: 'login'}
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/pages/Login')
    }
  ]
})

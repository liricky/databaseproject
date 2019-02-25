import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: '' || localStorage.getItem('token'),
    userName: '' || localStorage.getItem('userName'),
    userId: '' || localStorage.getItem('userId'),
    userAge: '' || localStorage.getItem('userAge'),
    userSex: '' || localStorage.getItem('userSex'),
    userDepartment: '' || localStorage.getItem('userDepartment')
  },
  mutations: {
    isLogin1 (state, msg) {
      state.token = msg
      localStorage.setItem('token', msg)
    },
    isLogin2 (state, msg) {
      state.userName = msg
      localStorage.setItem('userName', msg)
    },
    isLogin3 (state, msg) {
      state.userId = msg
      localStorage.setItem('userId', msg)
    },
    isLogin4 (state, msg) {
      localStorage.setItem('userAge', msg)
    },
    isLogin5 (state, msg) {
      localStorage.setItem('userSex', msg)
    },
    isLogin6 (state, msg) {
      localStorage.setItem('userDepartment', msg)
    },
    isLogout (state) {
      state.token = ''
      state.userName = ''
      state.userId = ''
      state.userAge = ''
      state.userSex = ''
      state.userDepartment = ''
      localStorage.removeItem('token')
      localStorage.removeItem('userName')
      localStorage.removeItem('userId')
      localStorage.removeItem('userAge')
      localStorage.removeItem('userSex')
      localStorage.removeItem('userDepartment')
    }
  },
  getters: {
    getToken (state) {
      return state.token
    },
    getUserId (state) {
      return state.userId
    },
    getUserName (state) {
      return state.userName
    },
    getUserAge (state) {
      return state.userAge
    },
    getUserSex (state) {
      return state.userSex
    },
    getUserDepartment (state) {
      return state.userDepartment
    }
  }
})

export default store

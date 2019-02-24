import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: '' || localStorage.getItem('token'),
    userName: '' || localStorage.getItem('userName'),
    userId: '' || localStorage.getItem('userId')
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
    isLogout (state) {
      state.token = ''
      state.userName = ''
      state.userId = ''
      localStorage.removeItem('token')
      localStorage.removeItem('userName')
      localStorage.removeItem('userId')
    }
  },
  getters: {
    getToken (state) {
      return state.token
    },
    getUserName (state) {
      return state.userName
    }
  }
})

export default store

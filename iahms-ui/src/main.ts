import { createApp } from 'vue'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './assets/icons/iconfont/iconfont.js'
import './assets/styles/main.scss'

import App from './App.vue'
import router from './router/index'
import store from './store/index'
import echarts from './lib/echarts'
// import Primevue from 'primevue/config'

const app = createApp(App)
// 注册ElementPlusIcon全局组件(全部图标)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// 注册全局变量
app.config.globalProperties.$echarts = echarts  // vue2的写法（应当遗弃
app.provide('echarts', echarts)   // vue3推荐做法（或者使用 Vuex or Pinia

// 从 localStorage 加载主题
const savedTheme = localStorage.getItem('theme') || 'light';
// document.getElementById('#app')?.setAttribute('data-theme', savedTheme)
store.commit('SET_THEME', savedTheme);

// 注册插件
app.use(router)
app.use(store)
app.mount('#app')


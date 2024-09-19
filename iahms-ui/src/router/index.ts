import {
  // createMemoryHistory,
  createWebHistory,
  createRouter,
} from 'vue-router'
import routes from './routes'
import store from '@/store/index'

// 把路由存进 store，for导航组件
store.commit('SET_ROUTES', routes);
for (let i = 0; i < routes.length; i++) {
  if (routes[i].path === '/hotel') {
    // 只取hotel模块下的子路由
    const hotelRoutes = routes[i].children || []
    store.commit('SET_HOTEL_ROUTES', hotelRoutes.slice())
    break
  }
}

// 初始化 router
const router = createRouter({
  // history: createMemoryHistory(),
  history: createWebHistory(),
  routes,
  // 是否严格管理路由
  strict: true,
  // 跳转后页面滚动行为
  scrollBehavior: () => ({ left: 0, top: 0 })
})

// 全局路由守卫
router.beforeEach((to, from, next) => {
  // do something
  // 打印2级路径
  console.log('path: ', to.matched[1]?.path ? to.matched[1].path : to.path);
  next()
})

export default router
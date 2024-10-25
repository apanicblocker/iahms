import {
  // createMemoryHistory,
  createWebHistory,
  createRouter,
} from 'vue-router'
import routes from './routes'

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
import { createMemoryHistory, createRouter } from 'vue-router'

const routes = [
  // 根页面路由
  { path: '/', redirect: '/hotel-index' },
  {
    path: '/hotel-index',
    name: 'HotelIndex',
    component: () => import('@/views/hotel/index.vue')
  },
  {
    path: '/hotel-about',
    name: 'HotelAbout',
    component: () => import('@/views/hotel/about.vue')
  },
  {
    path: '/component-test',
    name: 'ComponentTest',
    component: () => import('@/views/test.vue')
  },

  // ErrorPages
  {
    path: '/:catchAll(.*)',
    component: () => import('@/views/error/404.vue')
  },
];

const router = createRouter({
  history: createMemoryHistory(),
  routes
})

// 在路由守卫中添加逻辑（跳转相同路径变为刷新）
router.afterEach((to, from) => {
  // 如果目标路由的路径与当前路由的路径相同
  if (to.path === from.path && to.hash === from.hash) {
    // 执行页面刷新
    window.location.reload()
  }
});

export default router
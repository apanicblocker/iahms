import {
  // createMemoryHistory,
  createWebHistory,
  createRouter,
  RouteRecordRaw
} from 'vue-router'

const routes: RouteRecordRaw[] = [
  // 根页面路由
  { path: '/', redirect: '/hotel/dashboard' },
  {
    path: '/hotel',
    component: () => import('@/views/hotel/index.vue'),
    meta: {
      title: '酒店管理',	// TODO: 页面标题
      role: 'admin',		// TODO: 权限控制
    },
    children: [
      {
        path: '/hotel/dashboard',
        component: () => import('@/views/hotel/dashboard.vue')
      },
      {
        path: '/hotel/roomstate',
        component: () => import('@/views/hotel/roomstate.vue')
      },
      {
        path: '/hotel/order',
        component: () => import('@/views/hotel/order.vue')
      },
      {
        path: '/hotel/customer',
        component: () => import('@/views/hotel/customer.vue')
      },
      {
        path: '/hotel/settings',
        component: () => import('@/views/hotel/settings.vue')
      },
      {
        path: '/hotel/aboutUs',
        component: () => import('@/views/hotel/about.vue')
      },
    ]
  },

  {
    path: '/hotel/helpCenter',
    component: () => import('@/views/hotel/help.vue')
  },
  {
    path: '/component-test',
    component: () => import('@/views/hotel/test.vue')
  },

  // ErrorPages
  {
    path: '/:catchAll(.*)',
    component: () => import('@/views/error/404.vue')
  },
];

const router = createRouter({
  // history: createMemoryHistory(),
  history: createWebHistory(),
  routes,
  // 是否严格管理路由
  strict: true,
  // 跳转后页面滚动行为
  scrollBehavior: () => ({ left: 0, top: 0 })
})

// // 在路由守卫中添加逻辑（跳转相同路径变为刷新）
// router.afterEach((to, from) => {
//   // 如果目标路由的路径与当前路由的路径相同
//   if (to.path === from.path && to.hash === from.hash) {
//     // 执行页面刷新
//     window.location.reload()
//   }
// });
// router.beforeEach((to, from, next) => {
//   if (to.path === from.path && to.hash === from.hash) {
//     // 如果路由相同，可以选择导航到首页或其他页面
//     next('/') // 先导航到根路径
//     from.path === '/' ? window.location.reload() : router.replace({ path: '/' }) // 如果已经是根路径，则刷新页面
//   } else {
//     next()
//   }
// })

export default router
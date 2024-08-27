import {
  // createMemoryHistory,
  createWebHistory,
  createRouter,
  RouteRecordRaw
} from 'vue-router'
import store from '@/store/index'

const routes: RouteRecordRaw[] = [
  // 根页面路由
  { path: '/', redirect: '/hotel/dashboard' },
  {
    path: '/hotel',
    component: () => import('@/views/hotel/index.vue'),
    redirect: '/hotel/dashboard', // 默认子路由
    meta: {
      title: '酒店管理',	// TODO: 页面标题
      role: 'admin',		// TODO: 权限控制
    },
    children: [
      {
        path: '/hotel/dashboard',
        component: () => import('@/views/hotel/dashboard.vue'),
        meta: {
          title: '首页',
          role: 'admin',
        },
      },
      {
        path: '/hotel/roomstate',
        component: () => import('@/views/hotel/roomstate.vue'),
        meta: {
          title: '房态',
          role: 'admin',
        },
      },
      {
        path: '/hotel/order',
        component: () => import('@/views/hotel/order.vue'),
        redirect: '/hotel/order/orderManage',
        meta: {
          title: '订单',
          role: 'admin',
        },
        children: [
          {
            path: '/hotel/order/orderManage',
            redirect: '/hotel/order/orderManage/list',
            meta: {
              icon: 'DocumentChecked',
              title: '订单管理',
            },
            children: [
              {
                path: '/hotel/order/orderManage/list',
                component: () => import('@/views/hotel/order/order-list.vue'),
                meta: {
                  title: '住宿订单',
                }
              },
            ]
          },
        ],
      },
      {
        path: '/hotel/customer',
        component: () => import('@/views/hotel/customer.vue'),
        meta: {
          title: '客户',
          role: 'admin',
        },
      },
      {
        path: '/hotel/report',
        component: () => import('@/views/hotel/report.vue'),
        redirect: '/hotel/report/dataGather',
        meta: {
          title: '统计',
          role: 'admin',
        },
        children: [
          {
            path: '/hotel/report/dataGather',
            redirect: '/hotel/report/dataGather/revenueReport',
            meta: {
              icon: 'DocumentChecked',
              title: '数据汇总',
            },
            children: [
              {
                path: '/hotel/report/dataGather/revenueReport',
                component: () => import('@/views/hotel/report/report-revenue.vue'),
                meta: {
                  title: '营收汇总'
                }
              },
              {
                path: '/hotel/report/dataGather/receiveReport',
                component: () => import('@/views/hotel/report/report-receive.vue'),
                meta: {
                  title: '收款汇总'
                }
              },
              {
                path: '/hotel/report/dataGather/channelReport',
                component: () => import('@/views/hotel/report/report-channel.vue'),
                meta: {
                  title: '渠道汇总'
                }
              },
              {
                path: '/hotel/report/dataGather/roomRevenueReport',
                component: () => import('@/views/hotel/report/report-room-revenue.vue'),
                meta: {
                  title: '客房营业汇总'
                }
              },
            ],
          },
        ]
      },
      {
        path: '/hotel/settings',
        component: () => import('@/views/hotel/settings.vue'),
        meta: {
          title: '设置',
          role: 'admin',
        },
      },
      {
        path: '/hotel/aboutUs',
        component: () => import('@/views/hotel/about.vue'),
      },
    ]
  },

  {
    path: '/hotel/helpCenter',
    component: () => import('@/views/hotel/help.vue')
  },
  {
    path: '/component-test',
    component: () => import('@/views/hotel/test.vue'),
    meta: {
      title: '组件测试',
      role: 'admin',
    }
  },

  // ErrorPages
  {
    path: '/:catchAll(.*)',
    component: () => import('@/views/error/404.vue')
  },
];

// 将路由数据存到 Vuex 中
function initRoutes() {
  store.commit('SET_ROUTES', routes);
  for (let i = 0; i < routes.length; i++) {
    if (routes[i].path === '/hotel') {
      // 只取hotel模块下的子路由
      const hotelRoutes = routes[i].children || []
      store.commit('SET_HOTEL_ROUTES', hotelRoutes.slice())
      break
    }
  }
}
initRoutes()

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
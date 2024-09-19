import { RouteRecordRaw } from 'vue-router'

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
        redirect: '/hotel/customer/customerManage',
        meta: {
          title: '客户',
          role: 'admin',
        },
        children: [
          {
            path: '/hotel/customer/customerManage',
            redirect: '/hotel/customer/customerManage/customerList',
            meta: {
              icon: 'HomeFilled',
              title: '客户管理'
            },
            children: [
              {
                path: '/hotel/customer/customerManage/customerList',
                component: () => import('@/views/hotel/customer/customer-list.vue'),
                meta: {
                  title: '客户列表'
                }
              }
            ]
          },
          {
            path: '/hotel/customer/smsManager',
            redirect: '/hotel/customer/smsManager/smsBalance',
            meta: {
              title: '短信余额'
            }
          }
        ],
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
]

export default routes

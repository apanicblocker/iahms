import { RouteRecordRaw } from 'vue-router'

/**
 * 以下 meta属性没有的统一表示 false
 * displaySideNav     是否启用侧边导航菜单（用于展示当前路由的子路由菜单）
 * icon               子菜单在侧边导航菜单的展示图标
 * title              导航展示的标题文本
 * role               访问权限，没有的表示都能访问
 */
const routes: RouteRecordRaw[] = [
  // 根页面路由
  { path: '/', redirect: '/hotel/dashboard' },
  {
    path: '/login',
    component: () => import('@/views/login.vue'),
  },
  {
    path: '/register',
    component: () => import('@/views/register.vue'),
  },
  // ErrorPages
  {
    path: '/:catchAll(.*)',
    component: () => import('@/views/error/404.vue')
  },
  {
    path: '/hotel',
    component: () => import('@/views/hotel/index.vue'),
    redirect: '/hotel/dashboard', // 默认子路由
    meta: { role: 'admin', },
    children: [
      // 首页
      {
        path: '/hotel/dashboard',
        component: () => import('@/views/hotel/dashboard.vue'),
        meta: { role: 'admin' },
      },
      // 房态
      {
        path: '/hotel/roomstate',
        component: () => import('@/views/hotel/roomstate.vue'),
        meta: { role: 'admin', },
      },
      // 订单
      {
        path: '/hotel/order',
        redirect: '/hotel/order/orderManage',
        meta: { displaySideNav: true, title: '订单', role: 'admin', },
        children: [
          {
            path: '/hotel/order/orderManage',
            redirect: '/hotel/order/orderManage/list',
            meta: { icon: 'DocumentChecked', title: '订单管理', },
            children: [
              {
                path: '/hotel/order/orderManage/list',
                component: () => import('@/views/hotel/order/order-list.vue'),
                meta: { title: '住宿订单', }
              },
            ]
          },
          {
            path: '/hotel/order/orderManageTest',
            redirect: '/hotel/order/orderManage/listTest',
            meta: { icon: 'DocumentChecked', title: '订单管理', },
            children: [
              {
                path: '/hotel/order/orderManage/listTest',
                component: () => import('@/views/hotel/roomstate.vue'),
                meta: { title: '住宿订单', }
              },
            ]
          },
        ],
      },
      // 客户
      {
        path: '/hotel/customer',
        redirect: '/hotel/customer/customerManage',
        meta: { displaySideNav: true, title: '客户', role: 'admin', },
        children: [
          {
            path: '/hotel/customer/customerManage',
            redirect: '/hotel/customer/customerManage/customerList',
            meta: { icon: 'HomeFilled', title: '客户管理' },
            children: [
              {
                path: '/hotel/customer/customerManage/customerList',
                component: () => import('@/views/hotel/customer/customer-list.vue'),
                meta: { title: '客户列表' }
              }
            ]
          },
          {
            path: '/hotel/customer/smsManager',
            redirect: '/hotel/customer/smsManager/smsBalance',
            meta: { title: '短信余额' }
          }
        ],
      },
      // 统计
      {
        path: '/hotel/report',
        redirect: '/hotel/report/dataGather',
        meta: { displaySideNav: true, title: '统计', role: 'admin', },
        children: [
          {
            path: '/hotel/report/dataGather',
            redirect: '/hotel/report/dataGather/revenueReport',
            meta: { icon: 'DocumentChecked', title: '数据汇总', },
            children: [
              {
                path: '/hotel/report/dataGather/revenueReport',
                component: () => import('@/views/hotel/report/report-revenue.vue'),
                meta: { title: '营收汇总' }
              },
              {
                path: '/hotel/report/dataGather/receiveReport',
                component: () => import('@/views/hotel/report/report-receive.vue'),
                meta: { title: '收款汇总' }
              },
              {
                path: '/hotel/report/dataGather/channelReport',
                component: () => import('@/views/hotel/report/report-channel.vue'),
                meta: { title: '渠道汇总' }
              },
              {
                path: '/hotel/report/dataGather/roomRevenueReport',
                component: () => import('@/views/hotel/report/report-room-revenue.vue'),
                meta: { title: '客房营业汇总' }
              },
            ],
          },
        ]
      },
      // 设置
      {
        path: '/hotel/settings',
        component: () => import('@/views/hotel/settings.vue'),
        meta: { displaySideNav: true, role: 'admin', },
      },
      // 关于我们
      {
        path: '/hotel/aboutUs',
        component: () => import('@/views/hotel/about.vue'),
      },
    ]
  },
  {
    path: '/hotel/helpCenter',
    component: () => import('@/views/hotel/help.vue'),
  },
  {
    path: '/component-test',
    component: () => import('@/views/hotel/test.vue'),
    meta: { title: '组件测试', role: 'admin', }
  },
]

export default routes

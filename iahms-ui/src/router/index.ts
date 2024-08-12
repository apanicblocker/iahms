import { createMemoryHistory, createRouter } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Index',
    component: () => import('@/views/index.vue')
  },
  {
    path: '/about-us',
    name: 'AboutUs',
    component: () => import('@/views/about-us.vue')
  }
];

const router = createRouter({
  history: createMemoryHistory(),
  routes
})

// router.beforeEach((to, from, next) => {
//   if (to.path === '/login' && userIsLoggedIn) {
//     next('/home');
//   } else {
//     next();
//   }
// });

export default router
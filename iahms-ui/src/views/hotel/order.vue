<script>
import PageSidebar from './components/page-sidebar.vue';

export default {
  name: 'OrderPage',
  components: {
    PageSidebar,
  },
  data() {
    return {
      currentPath: '/hotel/order/list',
      sidebarData: [
        {
          icon: 'DocumentChecked',
          name: '订单管理',
          path: 'null',
          children: [
            {
              name: '住宿订单',
              path: '/hotel/order/list',
            }
          ],
        },
        {
          icon: 'ScaleToOriginal',
          name: '扫码牌收款',
          path: 'null',
        }
      ],
    }
  }
}
</script>

<template>
  <div class="order-page">
    <div class="sidebar">
      <PageSidebar :menus="sidebarData" :activeMenuIndex="currentPath"/>
    </div>
    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.order-page {
  display: flex;
  height: 100%;
  color: var(--base-c-text-1);

  main {
    padding: 16px;
  }
}

</style>

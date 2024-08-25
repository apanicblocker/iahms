<script>
export default {
  name: 'PageSidebar',
  props: {
    menus: {
      type: Array,
      default: () => [
        // 菜单头部
        // 菜单内容
        {
          type: 'group',
          preIcon: '',
          title: '订单管理',
          sufIcon1: '',
          sufIcon2: '',
          path: '',
          children: [
            {
              type: 'item',
              title: '住宿订单',
              path: '/hotel/order/list'
            },
            {
              type: 'item',
              title: '订单详情',
              path: '/hotel/order/detail'
            }
          ]
        },
        {
          type: 'item',
          title: '扫码牌收款',
          path: '/hotel/QRCollection'
        }
      ]
    }
  },
  data() {
    return {
      sidebarState: true
    }
  },
  methods: {
    renderMenu(menus) {

    }
  }
}
</script>

<template>
  <div class="page-sidebar">
    <div class="header">
      <div class="title">收起导航</div>
      <el-icon>
        <DArrowLeft v-if="sidebarState" />
        <DArrowRight v-else />
      </el-icon>
    </div>
    <div class="body">
      <div
        class="menu-item"
        v-for="(menuItem, menuIndex) in menus"
        :key="menuIndex"
        :class="{active: $route.path === menuItem.path}"
        @click="$router.push(menuItem.path)"
      >
        <div class="self-content">
          <div class="primary">
            <div class="pre-icon"></div>
            <div class="title">{{ menuItem.title }}</div>
          </div>
          <div class="suf-icon"></div>
        </div>
        <div class="children-content" v-if="menuItem.children && menuItem.children.length > 0">
          <div
            class="sub-menu-item"
            v-for="(subMenuItem, subMenuIndex) in menuItem.children"
            :key="subMenuIndex"
            @click="$router.push(subMenuItem.path)"
          >{{ subMenuItem.title }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-sidebar {
  width: 156px;
  padding: 16px 0 40px 0;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 8px;
  margin-bottom: 8px;

  .title {
    height: 36px;
    line-height: 36px;
    padding-left: 36px;
    text-align: left;
  }
}

.menu-item {
  padding: 8px;
  /* 完啦，这里样式会造成大量冗余。还是用组件算了 */
}
</style>

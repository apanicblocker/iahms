<script>
export default {
  name: 'PageSidebar',
  props: {
    menus: {
      type: Array,
      default: () => [
        // {
        //   icon: '',
        //   name: '订单管理',
        //   path: 'null',
        //   children: [
        //     {
        //       icon: '',
        //       name: '住宿订单',
        //       path: '/hotel/order/list',
        //     }
        //   ],
        // },
        // {
        //   icon: '',
        //   name: '扫码牌收款',
        //   path: '/',
        // },
      ]
    },
    activeMenuIndex: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      // 是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）
      isCollapse: false
    }
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // handleOpen(key, keyPath) {
    //   // console.log(key, keyPath)
    // },
    // handleClose(key, keyPath) {
    //   // console.log(key, keyPath)
    // }
  }
}
</script>

<template>
  <div class="page-sidebar">
    <el-menu
      :default-active="activeMenuIndex"
      class="menu-content"
      :collapse="isCollapse"
      :router="true"
      @open="handleOpen"
      @close="handleClose"
      style="
        --el-menu-bg-color: transparent;
        --el-menu-text-color: var(--base-c-text-1);
        --el-menu-text-active-color: var(--base-c-accent-2);
        --el-menu-hover-bg-color: var(--base-c-bg-2);
      "
    >
      <div class="header" @click="toggleCollapse">
        <div class="title" v-show="!isCollapse">收起导航</div>
        <el-icon class="title-icon">
          <DArrowRight v-if="isCollapse" />
          <DArrowLeft v-else />
        </el-icon>
      </div>
      <el-sub-menu
        v-for="(menuItem, menuIndex) in menus.filter(item => item.children)"
        :key="menuIndex"
        :index="menuItem.path"
      >
        <template #title>
          <el-icon><component :is="menuItem.icon"/></el-icon>
          <span>{{ menuItem.name }}</span>
        </template>
        <el-menu-item
          v-for="(subMenuItem, subMenuIndex) in menuItem.children"
          :key="subMenuIndex"
          :index="subMenuItem.path"
        >{{ subMenuItem.name }}</el-menu-item>
      </el-sub-menu>
      <el-menu-item
        v-for="(item, index) in menus.filter(item => !item.children)"
        :key="index"
        :index="item.path"
      >
        <el-icon><component :is="item.icon"/></el-icon>
        <span>{{ item.name }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.page-sidebar {
  height: 100%;
  padding: 8px 0 40px 0;
  background-color: var(--base-c-bg-1);
}

.menu-content {
  border-right: none;
  overflow: hidden;

  .header {
    box-sizing: content-box;
    cursor: pointer;
    display: flex;
    align-items: center;
    width: 156px;
    height: 56px;
    padding: 0 20px 0 25px;
    overflow: hidden;
    font-size: 14px;
    color: var(--base-c-text-1);

    .title {
      padding-left: 23px;
      flex-grow: 1;
      text-align: left;
    }
  }

  .el-menu-item.is-active {
    background-color: var(--base-c-bg-2);
  }
}
</style>

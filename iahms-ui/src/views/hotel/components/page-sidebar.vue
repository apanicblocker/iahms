<script>
export default {
  name: 'PageSidebar',
  props: {
    pageName: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      curPage: this.$route.matched[1].path,
      activeMenuIndex: this.$route.fullPath,
      defaultOpeneds: this.$store.getters.defaultOpeneds(this.pageName).slice(),
      // 是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）
      isCollapse: this.$store.state.common.sidebarCollapsed || false,
      menuList: [],
    }
  },
  watch: {
    defaultOpeneds: {
      handler(newVal) {
        this.$store.commit('SET_DEFAULT_OPENEDS', {
          defaultOpeneds: newVal.slice(),
          pageName: this.pageName,
        })
      },
      deep: true
    }
  },
  created() {
    this.getRouteMenus()
  },
  methods: {
    getRouteMenus() {
      let hotelRoutes = this.$store.state.common.hotelRoutes.slice()
      for (let i = 0; i < hotelRoutes.length; i++) {
        let route = hotelRoutes[i]
        if (route.path === this.$route.matched[1].path) {
          this.menuList = route.children
          // console.log(this.menuList)
          break
        }
      }
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
      this.$store.commit('SET_SIDEBAR_COLLAPSED', this.isCollapse)
    },
    handleOpen(key) {
      if (this.defaultOpeneds.indexOf(key) === -1) {
        this.defaultOpeneds.push(key)
      }
    },
    handleClose(key) {
      let indexRemove = this.defaultOpeneds.indexOf(key) !== -1
      if (indexRemove) { this.defaultOpeneds.splice(indexRemove, 1) }
    },
  }
}
</script>

<template>
  <div class="page-sidebar">
    <el-menu
      class="menu-content"
      :default-active="activeMenuIndex"
      :default-openeds="defaultOpeneds"
      :collapse="isCollapse"
      :router="true"
      style="
        --el-menu-bg-color: transparent;
        --el-menu-text-color: var(--base-c-text-1);
        --el-menu-text-active-color: var(--base-c-accent-2);
        --el-menu-hover-bg-color: var(--base-c-bg-2);
      "
      @open="handleOpen"
      @close="handleClose"
    >
      <div class="header" @click="toggleCollapse">
        <div class="title" v-show="!isCollapse">收起导航</div>
        <el-icon class="title-icon">
          <DArrowRight v-if="isCollapse" />
          <DArrowLeft v-else />
        </el-icon>
      </div>
      <el-sub-menu
        v-for="(menuItem, menuIndex) in menuList.filter(item => item.children)"
        :key="menuIndex"
        :index="menuItem.path"
      >
        <template #title>
          <el-icon><component :is="menuItem.meta.icon"/></el-icon>
          <span>{{ menuItem.meta.title }}</span>
        </template>
        <el-menu-item
          v-for="(subMenuItem, subMenuIndex) in menuItem.children"
          :key="subMenuIndex"
          :index="subMenuItem.path"
        >{{ subMenuItem.meta.title }}</el-menu-item>
      </el-sub-menu>

      <el-menu-item
        v-for="(item, index) in menuList.filter(item => !item.children)"
        :key="index"
        :index="item.path"
      >
        <el-icon><component :is="item.meta.icon"/></el-icon>
        <span>{{ item.meta.title }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.page-sidebar {
  height: 100%;
  padding: 8px 0 40px 0;
  background-color: var(--base-c-bg-1);
  overflow-x: hidden;
  overflow-y: auto;
}

.menu-content {
  border-right: none;
  overflow: hidden;

  .header {
    box-sizing: content-box;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 156px;
    height: 56px;
    padding: 0 20px 0 25px;
    overflow: hidden;
    font-size: 14px;

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

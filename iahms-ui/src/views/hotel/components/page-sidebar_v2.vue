<script lang="ts" setup>

import { useRoute } from 'vue-router'
import { useStore } from 'vuex'

interface MenuItem {
  path: string,
  meta: { icon?: string, title: string, },
  children?: MenuItem[],
}

const props = defineProps({
  pageName: {
    type: String,
    required: true,
  }
})

const store = useStore()
const route = useRoute()

const menuList: Ref<MenuItem[]> = ref([])
// 侧边栏收缩状态
const isCollapse = computed(() => store.state.common.sidebarCollapsed || false)
// 默认激活的菜单项
const activeMenuIndex: Ref<string> = ref(route.fullPath)
// 默认打开的菜单组
const defaultOpeneds = computed(() => store.getters.defaultOpeneds(props.pageName).slice())

watch(
  defaultOpeneds,
  (newVal) => {
    store.commit('SET_DEFAULT_OPENEDS', {
      defaultOpeneds: newVal.slice(),
      pageName: props.pageName,
    })
  },
  { deep: true }
)

onMounted(() => {
  getRouteMenus()
})

const getRouteMenus = () => {
  let hotelRoutes = computed(() => store.state.common.hotelRoutes.slice())
  for (let i = 0; i < hotelRoutes.value.length; i++) {
    let tempRoute = hotelRoutes.value[i]
    if (tempRoute.path === route.matched[1].path) {
      menuList.value = tempRoute.children
      break
    }
  }
}
const toggleCollapse = () => {
  store.commit('SET_SIDEBAR_COLLAPSED', !isCollapse.value)
}
const handleOpen = (key: string) => {
  if (defaultOpeneds.value.indexOf(key) === -1) {
    defaultOpeneds.value.push(key)
  }
}
const handleClose = (key: string) => {
  let indexRemove = defaultOpeneds.value.indexOf(key)
  if (indexRemove !== -1) {
    defaultOpeneds.value.splice(indexRemove, 1)
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
      @open="handleOpen"
      @close="handleClose"
    >
      <el-menu-item @click="toggleCollapse">
        <template #default>
          <div class="header" :class="{ 'sidebar-open': !isCollapse }">
            <div class="title" v-show="!isCollapse">收起导航</div>
            <el-icon class="title-icon">
              <DArrowRight v-if="isCollapse" />
              <DArrowLeft v-else />
            </el-icon>
          </div>
        </template>
      </el-menu-item>
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
  --el-menu-bg-color: transparent;
  --el-menu-text-color: var(--base-c-text-1);
  --el-menu-text-active-color: var(--base-c-accent-2);
  --el-menu-hover-bg-color: var(--base-c-bg-2);

  --menu-bg-color: var(--base-c-bg-1);
  --menu-bg-active-color: var(--base-c-bg-2);

  height: 100%;
  padding: 8px 0 40px 0;
  background-color: var(--menu-bg-color);
  overflow-x: hidden;
  overflow-y: auto;
}

.menu-content {
  border-right: none;
  overflow: hidden;

  .header {
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 156px;
    height: 56px;
    overflow: hidden;
    font-size: 14px;

    .title {
      flex-grow: 1;
      text-align: left;
    }
    .title-icon {
      margin: 0;
      font-size: 14px;
    }
  }

  .el-menu-item.is-active {
    background-color: var(--menu-active-bg-color);
  }
}

.sidebar-open {
  padding-left: 29px;
  .title-icon {
    width: 14px;
  }
}
</style>

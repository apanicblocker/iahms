<script lang="ts" setup>

/**
 * 该组件只针对第二级路径进行菜单解析，且只解析两层
 * 如: /hotel/order/aaa/bbb 这样的路径才会尝试获取其子路径然后转为菜单展示
 * 如: /hotel/order/aaa/bbb/ccc 这样的只会解析到bbb，bbb的子路由不会再被识别
 * 如: /help 这样的会直接忽略
 */

import { RouteRecord, useRoute, } from 'vue-router'
import { useStore } from 'vuex'

// // 用props把参数让给外面的话也是个不错的主意
// // 毕竟pageName只是vuex里的一个标识罢了，只要不重复就没有问题。
// // 问题是，他目前只针对二级路由来解析菜单，如果参数外放的话外面可不知道这个情况可能会乱改参数哦
// const props = defineProps({
//   pageName: {
//     type: String,
//     required: true,
//   },
// })

const store = useStore()
const route = useRoute()

// 获取当前路径的二级路由对象
const curPathRoute: Ref<RouteRecord> = ref(route.matched[1])
// 获取第二级路由用于标识
const pageName: Ref<string> = ref(route.matched[1]?.path.split('/')[2])

const menuRef = useTemplateRef('menu-content')
// 控制组件是否渲染
const display = computed(() => curPathRoute.value.meta.displaySideNav || false)
// 侧边菜单是否折叠
const isCollapse = computed(() => store.state.common.sidebarCollapsed || false)
// 默认激活当前菜单项
const activeMenuIndex = ref(route.fullPath)
// 默认打开的菜单组
const defaultOpeneds = computed(() => store.getters.defaultOpeneds(pageName.value).slice())

onMounted(() => {
  initSideNav()
})

onUnmounted(() => {
  // 卸载当前页面之前先让vuex记住数据，跳转页面就会卸载
  store.commit('SET_DEFAULT_OPENEDS', {
    defaultOpeneds: defaultOpeneds.value.slice(),
    pageName: pageName.value,
  })
})

const initSideNav = () => {
  // console.log("init defaultOpeneds: ", defaultOpeneds.value);
}

// 切换侧栏开关状态
const toggleCollapse = () => {
  const newStatus = !isCollapse.value
  store.commit('SET_SIDEBAR_COLLAPSED', newStatus)
  if (newStatus) {
    // 关闭侧栏的时候把数据传到vuex
    store.commit('SET_DEFAULT_OPENEDS', {
      defaultOpeneds: defaultOpeneds.value.slice(),
      pageName: pageName.value,
    })
  } else {
    // 开的时候打开关之前已经打开了的子菜单
    for (let i = 0; i < defaultOpeneds.value.length; i++) {
      console.log('defaultOpeneds.value['+ i +']', defaultOpeneds.value[i]);
      menuRef.value.open(defaultOpeneds.value[i])
    }
  }
}
const handleOpen = (key: string) => {
  if (defaultOpeneds.value.indexOf(key) === -1) { // 防重复塞入
    defaultOpeneds.value.push(key)
  }
  console.log("open: " + key, defaultOpeneds.value)
}
const handleClose = (key: string) => {
  let indexRemove = defaultOpeneds.value.indexOf(key)
  if (indexRemove !== -1 && !isCollapse.value) { // 防重复删
    defaultOpeneds.value.splice(indexRemove, 1)
  }
  console.log("close: " + key, defaultOpeneds.value)
}
</script>

<template>
  <div class="sidebar" v-if="display">
    <el-menu class="menu-content"
      ref="menu-content"
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
      <!-- 有子项菜单渲染 -->
      <el-sub-menu
        v-for="(menuItem, menuIndex) in curPathRoute.children.filter(item => item.children)"
        :key="menuIndex"
        :index="menuItem.path"
      >
        <template #title>
          <el-icon><component :is="menuItem.meta?.icon"/></el-icon>
          <span>{{ menuItem.meta?.title }}</span>
        </template>
        <el-menu-item
          v-for="(subMenuItem, subMenuIndex) in menuItem.children"
          :key="subMenuIndex"
          :index="subMenuItem.path"
        >{{ subMenuItem.meta?.title }}</el-menu-item>
      </el-sub-menu>
      <!-- 无子项菜单渲染 -->
      <el-menu-item
        v-for="(item, index) in curPathRoute.children.filter(item => !item.children)"
        :key="index"
        :index="item.path"
      >
        <el-icon><component :is="item.meta?.icon"/></el-icon>
        <span>{{ item.meta?.title }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.sidebar {
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
    justify-content: space-around;
    align-items: center;
    width: 160px;
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

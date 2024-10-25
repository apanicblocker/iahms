<script lang="ts" setup>

import { useRoute, useRouter } from 'vue-router'

interface NavItem {
  title: string
  path: string
}

const route = useRoute()
const router = useRouter()

const navList: NavItem[] = [
  { title: '首页', path: '/hotel/dashboard'},
  { title: '房态', path: '/hotel/roomstate'},
  { title: '订单', path: '/hotel/order'},
  { title: '客户', path: '/hotel/customer'},
  { title: '统计', path: '/hotel/report'},
  { title: '设置', path: '/hotel/settings'},
  { title: '关于我们', path: '/hotel/aboutUs'},
  { title: '帮助中心', path: '/hotel/helpCenter'},
]

onMounted(() => {
  initNav()
})

// 根据路由初始化选中项
const initNav = () => {
  let currentPath = route.matched[1]?.path
  if (currentPath) {
    for (let i = 0; i < navList.length; i++) {
      if (currentPath.includes(navList[i].path)) {
        setActive(i)
        return
      }
    }
  }
  // 不在列表中则不高亮了
  setActive(-1)
}

// 点击导航栏
const setActive = (index: number) => {
  // 清除所有active
  let navItems = document.getElementsByClassName('nav-item')
  if (navItems.length === 0) return
  for (let i = 0; i < navItems.length; i++) {
    navItems[i].classList.remove('active')
  }
  // 负数不处理高亮
  if (index >= 0) {
    // 添加active
    document.getElementsByClassName('nav-item')[index].classList.add('active')
  }
}
</script>

<template>
  <div class="nav-container">
    <div class="nav-item"
      v-for="(item, index) in navList"
      :key="index"
      @click="router.push(item.path)"
    >{{ item.title }}</div>
  </div>
</template>

<style scoped>
.nav-container {
  display: flex;
  height: 56px;
}

.nav-item {
  cursor: pointer;
  height: 56px;
  line-height: 56px;
  font-size: 14px;
  padding: 0 16px;
}

.active {
  border-bottom: 4px solid var(--base-c-accent-1);
  font-weight: 600;
  color: var(--base-c-accent-1);
  /* 如果想增强导航栏字段的文字部分可以解开下面的注释 */
  /* color: var(--base-c-text-1);
  background-color: var(--base-c-primary-1);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04); */
}
</style>

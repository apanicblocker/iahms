<script lang="ts" setup>

import PageHeaderTitle from './page-header-title.vue'
import PageHeaderNav from './page-header-nav.vue'
import ThemeSwitch from './theme-switch.vue'

import { useRouter } from 'vue-router'

const router = useRouter()

const titleText: Ref<string> = ref('呆逼bad头欣')
const imgPath: Ref<string> = ref('/public/vite.svg')

</script>

<template>
  <div class="header">
    <div class="navbar">
      <!-- 导航头 -->
      <PageHeaderTitle :titleText="titleText" :imgPath="imgPath"/>
      <el-divider direction="vertical" />
      <!-- 导航列表 -->
      <PageHeaderNav />
      <!-- 测试页面 -->
      <RouterLink to="/component-test" >测试页面</RouterLink>
    </div>
    <div class="flex-full"></div>
    <div class="start-center">
      <!-- 其他功能快捷入口 -->
      <div class="quick-function">
        <!-- TODO -->
      </div>
      <el-divider direction="vertical" />
      <!-- 光暗模式切换开关 -->
      <ThemeSwitch />
      <el-divider direction="vertical" />
      <!-- 用户头像 -->
      <div class="user-avatar" @click="router.push('/login')">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-user-l"></use>
        </svg>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 56px;
  padding: 0 24px;
  background-color: var(--base-c-bg-1);
  border-bottom: 1px solid var(--base-c-bg-2);
  box-shadow: 0 2px 12px 0 var(--base-c-bg-2);
}

.el-divider {
  margin: 0 16px;
}

.navbar {
  display: flex;
  align-items: center;
}

.flex-full {
  flex: 1;
}

.start-center {
  display: flex;
  align-items: center;
}

.quick-function {
  display: flex;
  /* TODO */
}

.theme-toggler {
  .switch-icon {
    background-color: var(--base-c-bg-2);
    border-radius: 10px;
  }
}

.user-avatar {
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 32px;
  height: 32px;

  > svg {
    width: 24px;
    height: 24px;
  }
}
</style>

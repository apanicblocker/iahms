<script>
export default {
  name: 'PageHeaderNav',
  props: {
    navList: {
      type: Array,
      default: () => [
        {
          name: '根目录',
          path: '/'
        },
      ]
    },
  },
  data() {
    return {}
  },
  mounted() {
    this.initNav()
  },
  methods: {
    initNav() {
      // 根据路由初始化选中项
      let currentPath = this.$route.fullPath
      for (let i = 0; i < this.navList.length; i++) {
        if (currentPath === this.navList[i].path) {
          this.setActive(i)
          return
        }
      }
      // 没有匹配到，默认选中第一个
      this.setActive(0)
    },
    setActive(index) {
      // 清除所有active
      let navItems = document.getElementsByClassName('nav-item')
      if (navItems.length === 0) return
      for (let i = 0; i < navItems.length; i++) {
        navItems[i].classList.remove('active')
      }
      // 添加active
      document.getElementsByClassName('nav-item')[index].classList.add('active')
    }
  }
}
</script>

<template>
  <div class="nav-container">
    <div
      class="nav-item"
      v-for="(item, index) in navList"
      :key="index"
      @click="$router.push(item.path)"
    >{{ item.name }}</div>
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
  color: var(--base-c-text-1)
}

.active {
  color: var(--base-c-accent-2);
  border-bottom: 4px solid var(--base-c-accent-2);
}
</style>

<script>
export default {
  name: 'PageHeaderNav',
  data() {
    return {
      navList: [],
    }
  },
  created() {
    this.getRoutes()
  },
  mounted() {
    this.initNav()
  },
  methods: {
    // 获取当前页面需要的路由参数
    getRoutes() {
      let routes = this.$store.state.common.routes
      for (let i = 0; i < routes.length; i++) {
        if (routes[i].path === '/hotel') {
          // 只取hotel模块下的子路由
          let hotelRouters = routes[i].children
          for (let routerItem of hotelRouters) {
            let tempNode = {name: '', path: ''}
            tempNode.name = routerItem.meta?.title
            tempNode.path = routerItem.path
            // 如果 name和 path都存在，则添加到navList中
            if (tempNode.name && tempNode.path) {
              this.navList.push(tempNode)
            }
          }
          break
        }
      }
    },
    // 根据路由初始化选中项
    initNav() {
      let currentPath = this.$route.matched[1]?.path
      if (currentPath) {
        // 如果是根目录，则默认选中第一个
        for (let i = 0; i < this.navList.length; i++) {
          if (currentPath === this.navList[i].path) {
            this.setActive(i)
            return
          }
        }
      }
      this.setActive(0)
    },
    // 点击导航栏
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
  color: var(--base-c-text-1);
}

.active {
  color: var(--base-c-accent-1);
  font-weight: 600;
  border-bottom: 4px solid var(--base-c-accent-1);
  /* 如果想增强导航栏字段的文字部分可以解开下面的注释 */
  /* color: var(--base-c-text-1);
  background-color: var(--base-c-primary-1);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04); */
}
</style>

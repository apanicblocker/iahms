<script>
import PageHeaderTitle from './page-header-title.vue';
import PageHeaderNav from './page-header-nav.vue';

export default {
  name: 'PageHeader',
  components: {
    PageHeaderTitle,
    PageHeaderNav,
  },
  data() {
    return {
      titleText: '小鸟公寓',
      imgPath: '/src/assets/vue.svg',
      navList: [
        {
          name: '首页',
          path: '/hotel/dashboard'
        },
        {
          name: '房态',
          path: '/hotel/roomstate'
        },
        {
          name: '订单',
          path: '/hotel/order'
        },
        {
          name: '客户',
          path: '/hotel/customer'
        },
        {
          name: '设置',
          path: '/hotel/settings'
        },
        {
          name: '关于',
          path: '/hotel/about'
        },
        {
          name: '组件测试',
          path: '/component-test'
        }
      ],

      darkTheme: false,
    }
  },
  mounted() {
    // this.test()
  },
  methods: {
    // test() {
    //   this.$store.commit('increment')
    //   console.log(this.$store.state.count);
    // }
  }
}
</script>

<template>
  <div class="page-header">
    <div class="nav-bar">
      <!-- 导航头 -->
      <PageHeaderTitle :titleText="titleText" :imgPath="imgPath"/>
      <el-divider direction="vertical" />
      <!-- 导航列表 -->
      <PageHeaderNav :navList="navList" />
    </div>
    <div class="flex-full"></div>
    <div class="start-center">
      <!-- 其他功能快捷入口 -->
      <div class="quick-function">
        <!-- TODO -->
      </div>
      <el-divider direction="vertical" />
      <!-- 光暗模式切换开关 -->
      <div class="theme-toggler">
        <el-switch
          v-model="darkTheme"
          inline-prompt
          style="
            --el-switch-on-color: var(--base-c-bg-3);
            --el-switch-off-color: var(--base-c-bg-3);
            --el-switch-border-color: var(--base-c-bg-2);
            font-size: 18px;"
        >
          <template #active-action>
            <svg class="icon switch-icon" aria-hidden="true">
              <use xlink:href="#icon-moon1"></use>
            </svg>
          </template>
          <template #inactive-action>
            <Sunny class="switch-icon"/>
          </template>
        </el-switch>
      </div>
      <el-divider direction="vertical" />
      <!-- 用户头像 -->
      <div class="user-avatar">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-user-l"></use>
        </svg>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 56px;
  padding: 0 24px;
  background-color: var(--base-c-bg-1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-divider {
  margin: 0 16px;
}

.nav-bar {
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
    color: var(--base-c-text-1);
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
    color: var(--base-c-text-1);
  }
}
</style>

<script>
export default {
  name: 'PageHeader',
  props: {
    titleText: {
      type: String,
      default: '请输入公寓名称'
    },
    imgPath: {
      type: String,
      default: '/src/assets/vue.svg'
    },
    navList: {
      type: Array,
      default: () => [
        {
          text: '空',
          path: '/'
        }
      ]
    }
  },
  data() {
    return {

    }
  },
  methods: {

  }
}
</script>

<template>
  <header>
    <div class="iahms-header">
      <nav class="nav-bar">
        <div class="title-container">
          <img class="logo-icon" :src="imgPath" alt="LOGO图片" style="cursor: pointer"
            @click="$router.push('/about-us')"
          >
          <div class="title-text">{{ titleText }}</div>
          <div class="level-tag"></div>
        </div>
        <div class="nav-container"
          v-for="(item, index) in navList"
          :key="index"
        >
          <div class="nav-item">{{ item.text }}</div>
        </div>
      </nav>
      <div class="func-bar">
        <div class="quick-function"></div>
        <div class="user-avatar"></div>
      </div>
    </div>
  </header>
</template>

<style scoped>

</style>

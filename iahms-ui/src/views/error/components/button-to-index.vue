<script>
export default {
  name: 'ButtonToIndex',
  data() {
    return {}
  },
  methods: {
    handleClick() {
      window.location.reload();  // 结果是跟下面一样的。。。
      // this.$router.replace('/hotel-index')
    }
  }
}
</script>

<template>
  <button class="button-to-index">
    <router-link to="/">返回首页</router-link>
  </button>
</template>

<style scoped>
</style>

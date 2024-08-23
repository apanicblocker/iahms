<script>
export default {
  name: '401',
  components: {
    ButtonToIndex: () => import('./components/button-to-index')
  },
  data() {
    return {}
  }
}
</script>

<template>
  <h1>401</h1>
  <p>没有权限访问</p>
  <ButtonToIndex />
</template>

<style scoped>
</style>

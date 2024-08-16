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
    }
  },
  data() {
    return {}
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
</style>

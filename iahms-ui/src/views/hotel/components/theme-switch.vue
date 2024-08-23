<script>
export default {
  name: 'ThemeSwitch',
  data() {
    return {
      // 开启（滑块停在右边）则为暗色主题
      // 目前只有两种，后续扩展的话就直接替换对应scss文件吧(反正就两种主要主题色)
      switchState: this.$store.state.common.theme === 'dark',
    }
  },
  watch: {
    switchState(val) {
      this.$store.commit('SET_THEME', val ? 'dark' : 'light')
    }
  },
}
</script>

<template>
  <div class="theme-switch">
    <el-switch
      v-model="switchState"
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
</template>

<style scoped>
.switch-icon {
  color: var(--base-c-text-1);
  background-color: var(--base-c-bg-2);
  border-radius: 10px;
}
</style>

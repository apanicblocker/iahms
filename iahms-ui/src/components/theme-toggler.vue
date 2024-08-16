<script>
import { Sunny, Moon } from '@element-plus/icons-vue';
import { ref } from 'vue';

// 遗弃，暂时不用
export default {
  name: 'ThemeToggler',
  props: {
    visible: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      // 查询浏览器主题（浏览器状态，应该在别的地方查询的吧），这边只要根据值作出反应就好了
      darkTheme: ref(window.matchMedia('(prefers-color-scheme: dark)').matches),
    };
  },
  watch: {
    darkTheme(val) {
      if (val) {
        document.getElementById('app').setAttribute('data-theme', 'dark');
      } else {
        document.getElementById('app').setAttribute('data-theme', 'light');
      }
    }
  }
}
</script>

<template>
  <div class="theme-toggler">
    <el-switch
      v-if="visible"
      v-model="darkTheme"
      inline-prompt
      style="
        --el-switch-on-color: var(--base-c-bg-3);
        --el-switch-off-color: var(--base-c-bg-3);
        --el-switch-border-color: var(--base-c-bg-2);
        font-size: 18px;
      "
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

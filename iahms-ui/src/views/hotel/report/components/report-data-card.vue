<script lang="ts" setup>

interface CardData {
  icon?: string,
  title: string,
  value: number | string,
  tips?: number | string,
}

const props = defineProps({
  cardData: {
    type: Object as () => CardData,
    default: () => ({
      icon: 'HomeFilled',
      title: '请输入标题',
      value: 0,
      tips: 1,
    })
  },
})
</script>

<template>
  <div class="report-data-card">
    <main>
      <slot name="main">
        <el-icon class="icon" v-if="props.cardData.icon">
          <component :is="props.cardData.icon" />
        </el-icon>
        <div class="content">
          <slot name="content">
            <div class="title">{{ props.cardData.title }}</div>
            <div class="value">￥{{ props.cardData.value }}</div>
          </slot>
        </div>
      </slot>
    </main>
    <footer class="tips"
      :class="{'has-icon': props.cardData.icon}"
      v-if="props.cardData.tips"
    >
      <slot name="tips">
        <span>占比</span>
        {{ props.cardData.tips }}<span>%</span>
      </slot>
    </footer>
  </div>
</template>

<style scoped>
.report-data-card {
  --icon-bg-color: var(--base-c-primary-2);
  --icon-text-color: var(--base-c-bg-1);

  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  main {
    display: flex;
    align-items: center;
  }
}

.icon {
  width: 48px;
  height: 48px;
  margin-right: 24px;
  background-color: var(--icon-bg-color);
  border-radius: 6px;
  font-size: 28px;
  color: var(--icon-text-color);
}
.content {
  flex-grow: 1;

  .title {
    font-size: 14px;
  }
  .value {
    margin-top: 8px;
    font-size: 26px;
    font-weight: bold;
  }
}
.tips {
  margin-top: 8px;
  font-size: 12px;
}
.tips.has-icon {
  margin-left: 72px;
}
</style>

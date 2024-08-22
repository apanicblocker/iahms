<script>
export default {
  name: 'DataCards',
  props: {
    cardDataList: {
      type: Array,
      default: () => [
        {
          title: '标题',
          value: '数值',
          param: '参数',
        },
      ]
    }
  },
  emits: ['clickCard'],
}
</script>

<template>
  <div class="data-cards">
    <div
      class="data-card-item"
      v-for="(item, index) in cardDataList"
      :key="index"
      @click="$emit('clickCard', item.param)"
    >
      <div class="title">{{ item.title }}</div>
      <div class="value">{{ item.value }}</div>
    </div>
  </div>
</template>

<style scoped>
.data-cards {
  display: flex;
}

.data-card-item {
  cursor: pointer;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  height: 80px;
  padding: 8px 0 0 16px;
  background-color: var(--base-c-bg-1);
  text-align: left;
  color: var(--base-c-text-1);

  &:hover {
    background-color: var(--base-c-accent-1);
    font-weight: bold;
    color: var(--base-c-bg-1);
    transform: scale(1.05);
    .title {
      opacity: 1;
    }
  }

  &+& {
    margin-left: 16px;
  }
  .title {
    opacity: 0.8;
  }
  .value {
    flex: 1;
    display: flex;
    align-items: center;
    font-size: 30px;
    font-weight: bold;
  }
}
</style>

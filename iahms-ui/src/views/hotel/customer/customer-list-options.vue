<script lang="ts" setup>

interface Option {
  label: string,
  param?: any,
}

const emit = defineEmits(['clickLabel'])

const optionList = ref([
  { label: '查单', param: null },
  { label: '详情', param: null },
  { label: '加标签', param: null },
])
</script>

<template>
  <div class="customer-list-options">
    <div class="option"
      v-for="(item, index) in optionList"
      :key="index"
    >
      <span @click="emit('clickLabel', item.param)">{{ item.label }}</span>
      <el-divider direction="vertical" />
    </div>
    <el-popover
      placement="bottom"
      width="auto"
      trigger="hover"
      popper-style="
        min-width: auto;
        padding: 0;
      "
    >
      <template #reference>
        <el-icon class="option"><MoreFilled /></el-icon>
      </template>
      <div class="hidden-options">
        <div class="blacklist-btn" @click="emit('clickLabel', 'blacklist')">加入黑名单</div>
        <div class="delete-btn" @click="emit('clickLabel', 'delete')">删除</div>
      </div>
    </el-popover>
  </div>
</template>

<style scoped>
.customer-list-options {
  --option-font-color: var(--base-c-accent-1);

  display: flex;
  align-items: center;
  padding: 0 12px;
}
.option {
  cursor: pointer;
  color: var(--option-font-color);
}
.hidden-options {
  background-color: transparent;

  > * {
    cursor: pointer;
    padding: 6px 12px;
  }
  .blacklist-btn {
    color: black;
    &:hover {
      background-color: rgb(0, 0, 0, 0.1);
    }
  }
  .delete-btn {
    color: red;
    &:hover {
      background-color: rgb(255, 0, 0, 0.1);
    }
  }
}
</style>

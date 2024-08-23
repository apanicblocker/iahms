<script>

export default {
  name: 'DashboardCommonFunction',
  data() {
    return {
      dataList: [
        // TODO: 现在只有首页，后续再加
        {
          name: '住宿订单',
          iconPath: 'el-icon-s-order',
          routePath: '/hotel/order',
          isShow: true,
        },
      ],
      dialogVisible: ref(false),
    }
  },
  methods: {
    showDialog() {
      this.dialogVisible = true
    },
  },
}
</script>

<template>
  <div class="dashboard-common-function">
    <div
      class="common-function-item"
      v-for="(item, index) in dataList"
      :key="index"
      @click="$router.push(item.routePath)"
    >
      <div class="icon"></div>
      <div class="text">{{ item.name }}</div>
    </div>
    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <span>暂未开放</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.dashboard-common-function {
  display: flex;
}

.common-function-item {
  box-sizing: content-box;
  cursor: pointer;
  flex: 0.125;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  align-items: center;
  height: 64px;
  padding: 4px 0 8px 0;
  background-color: var(--base-c-bg-2);

  & + & {
    margin-left: 16px;
  }
  .icon {
    width: 40px;
    height: 40px;
    border: 1px solid var(--base-c-text-2);
  }
  .text {
    font-size: 14px;
  }
  &:hover {
    filter: brightness(1.1);
    background-color: var(--base-c-bg-3);
  }
}

</style>

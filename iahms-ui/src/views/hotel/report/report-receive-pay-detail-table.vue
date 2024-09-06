<script lang="ts" setup>
import BaseTable from '@/views/hotel/components/base-table.vue';

const props = defineProps({
  theadData: {
    type: Array,
    default: () => []
  },
  tbodyData: {
    type: Array,
    default: () => []
  }
})
// 标签页组件变量
const activeName = ref('netReceipt')
// 分页组件变量
const currentPage = ref(0)
const disabled = ref(false)
const background = ref(undefined)
const handleSizeChange = () => {}
const handleCurrentChange = () => {}
</script>

<template>
  <div class="report-receive-pay-detail-table">
    <el-tabs class="tabs"
      v-model="activeName"
    >
      <el-tab-pane label="净收款" name="netReceipt">
        <BaseTable :theadData="props.theadData" :tbodyData="props.tbodyData">
          <template #header>
            <!-- <div class="control-btn">
              <el-pagination
                v-model:current-page="currentPage"
                :page-size="16"
                :disabled="disabled"
                :background="background"
                layout="prev, next, slot"
                :total="100"
                prev-text="前16天"
                next-text="后16天"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              >
                <template #default>
                  <el-button>导出报表</el-button>
                </template>
              </el-pagination>
            </div> -->
          </template>
        </BaseTable>
      </el-tab-pane>
      <el-tab-pane label="收款" name="receipt">
        <BaseTable :theadData="props.theadData" :tbodyData="props.tbodyData"></BaseTable>
      </el-tab-pane>
      <el-tab-pane label="退款" name="refund">
        <BaseTable :theadData="props.theadData" :tbodyData="props.tbodyData"></BaseTable>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.control-btn {
  margin-bottom: 16px;
}
</style>

<style>
.report-receive-pay-detail-table .el-pagination {
  display: flex;
  justify-content: flex-end;
  button {
    margin-left: 16px;
  }
}
</style>

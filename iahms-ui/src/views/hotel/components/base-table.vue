<script lang="ts" setup>

import { TableColumnCtx } from 'element-plus';

interface SpanMethodProps {
  row: any
  column: TableColumnCtx<any>
  rowIndex: number
  columnIndex: number
}

interface SummaryMethodProps<T = any> {
  columns: TableColumnCtx<T>[]
  data: T[]
}

interface TheadDataItem {
  prop: string
  label: string
  width?: number
}

const props = defineProps({
  tableHeight: {
    type: Number,
  },
  theadData: {
    type: Array<TheadDataItem>,
    default: () => [],
  },
  tbodyData: {
    type: Array,
    default: () => [],
  },
  spanData: {
    type: Array<number>,
    default: () => [],
  },
  isShowSummary: {
    type: Boolean,
    default: false,
  },
})

var lastType = ''
var curSpanIndex = 0
const spanMethod = ({
  row,
  column,
  rowIndex,
  columnIndex
}: SpanMethodProps) => {
  // 方法二（感觉方法二的代码更直观，方法一的优势是数据结构更简洁
  if (column.property === 'type') {
    const curType = row.type
    if (curType !== lastType) {
      // 重置索引位置为第二次循环做准备（每个单元格都要执行该方法两次！！！）
      if (curSpanIndex >= props.spanData.length) curSpanIndex = 0
      lastType = curType
      const index = props.spanData[curSpanIndex++]
      if (index > 0) {
        return {
          rowspan: index,
          colspan: 1,
        }
      } else {
        return {
          rowspan: 1,
          colspan: Math.abs(index)
        }
      }
    } else {
      return {
        rowspan: 0,
        colspan: 0,
      }
    }
  }
}

// 计算合计方法（官方模板：改）
const getSummaries = (param: SummaryMethodProps) => {
  const { columns, data } = param
  const sums: (string | VNode)[] = []
  columns.forEach((column, index) => {
    if (index === 0) {
      sums[index] = h('div', { style: { textDecoration: 'underline' } }, [
        'Total Cost',
      ])
      return
    }
    const values = data.map((item) => Number(item[column.property]))
    if (!values.every((value) => Number.isNaN(value))) {
      sums[index] = `￥${values.reduce((prev, curr) => {
        const value = Number(curr)
        if (!Number.isNaN(value)) {
          return prev + curr
        } else {
          return prev
        }
      }, 0)}`
    } else {
      sums[index] = 'N/A'
    }
  })
  console.log('sums: ', sums);

  return sums
}
</script>

<template>
  <div class="base-table">
    <slot name="header"></slot>
    <slot name="table-DIY">
      <el-table
        class="table"
        :data="tbodyData"
        :height="props.tableHeight"
        :span-method="props.spanData.length > 0 ? spanMethod : undefined"
        :show-summary="isShowSummary"
        :summary-method="getSummaries"
        border
        style="
          --el-table-header-bg-color: var(--base-c-bg-2);
          --el-table-header-text-color: var(--base-c-text-1);
          --el-table-border-color: var(--base-c-bg-3);
          --el-table-text-color: var(--base-c-text-1);
          --el-table-tr-bg-color: var(--base-c-bg-1);
          --el-table-row-hover-bg-color: var(--base-c-accent-1);
        "
      >
        <template #empty>
          <el-empty :image-size="40">
            <template #description>暂无数据</template>
          </el-empty>
        </template>
        <el-table-column
          v-for="(item, index) in theadData"
          :key="index"
          :prop="item.prop"
          :label="item.label"
          :width="item.width"
          min-width="100"
        />
      </el-table>
    </slot>
    <slot name="footer"></slot>
  </div>
</template>

<style scoped>
.base-table {
  overflow: auto;
}
</style>

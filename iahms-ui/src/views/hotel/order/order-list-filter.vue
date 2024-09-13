<script lang="ts" setup>

import { formatDate } from '@/utils/dataFormat'

import FilterItemContainer from '@/views/hotel/components/filter-item-container.vue'

interface OrderFilterParam {
  keyword: string,
  dateRangeMode: string,
  checkinTimeStart: string | undefined,
  checkinTimeEnd: string | undefined,
  checkoutTimeStart: string | undefined,
  checkoutTimeEnd: string | undefined,
  createTimeStart: string | undefined,
  createTimeEnd: string | undefined,
  durationInTimeStart: string | undefined,
  durationInTimeEnd: string | undefined,

  channelId: string,
  roomTypeId: string,
  checkinTypeId: string,
  checkinStatusId: string,
  payStatusId: string,
  boolBundle: boolean | ''
}

const filterTitleWidth = '64px'           // 筛选项的标题固定宽度
const isPanelClosed = ref<boolean>(false) // 面板关闭状态
const commonDateRange = ref<[Date, Date]>([new Date(), new Date()])
const filterData = reactive<OrderFilterParam>({
  keyword: '',
  dateRangeMode: 'createTime',
  checkinTimeStart: undefined,
  checkinTimeEnd: undefined,
  checkoutTimeStart: undefined,
  checkoutTimeEnd: undefined,
  createTimeStart: undefined,
  createTimeEnd: undefined,
  durationInTimeStart: undefined,
  durationInTimeEnd: undefined,

  channelId: '',
  roomTypeId: '',
  checkinTypeId: '',
  checkinStatusId: '',
  payStatusId: '',
  boolBundle: '',
})

const submitForm = () => {
  switch(filterData.dateRangeMode) {
    case('checkinTime'): {
      filterData.checkinTimeStart = formatDate(commonDateRange.value[0])
      filterData.checkinTimeEnd = formatDate(commonDateRange.value[1])
      break
    }
    case('checkoutTime'): {
      filterData.checkoutTimeStart = formatDate(commonDateRange.value[0])
      filterData.checkoutTimeEnd = formatDate(commonDateRange.value[1])
      break
    }
    case('createTimeState'): {
      filterData.createTimeStart = formatDate(commonDateRange.value[0])
      filterData.createTimeEnd = formatDate(commonDateRange.value[1])
      break
    }
    case('durationInTime'): {
      filterData.durationInTimeStart = formatDate(commonDateRange.value[0])
      filterData.durationInTimeEnd = formatDate(commonDateRange.value[1])
      break
    }
  }
  console.log('TODO：提交filterData: ', filterData)
}
</script>

<template>
  <div class="order-list-filter" :class="{ isClosed: isPanelClosed }">
    <div class="filter-list">
      <FilterItemContainer class="filter-item large">
        <template #content>
          <el-input class="searchbar"
            v-model="filterData.keyword"
            placeholder="请输入订单号、渠道订单号、房间号、姓名、手机号查询"
            @change="submitForm"
          >
            <template #suffix>
              <el-icon style="color: var(--base-c-accent-2);"><Search /></el-icon>
            </template>
          </el-input>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="日期选择" :titleWidth="filterTitleWidth">
        <template #content>
          <div class="date-select-picker">
            <el-select
              v-model="filterData.dateRangeMode"
              popper-class="popper"
              @change="submitForm"
              style="width: 130px; margin-right: -1px;"
            >
              <el-option label="入住时间" value="checkinTime" />
              <el-option label="离店时间" value="checkoutTime" />
              <el-option label="创建时间" value="createTime" />
              <el-option label="在住时间" value="durationInTime" />
            </el-select>
            <el-date-picker
              v-model="commonDateRange"
              type="daterange"
              popper-class="popper"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              style="width: 100%; border-radius: 0 4px 4px 0;"
              @change="submitForm"
            />
          </div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="渠道" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.channelId"
            popper-class="popper"
            placeholder="全部"
            @change="submitForm"
          >
          <!-- 从数据库导入(总之不应该是写死的) -->
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="房型" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.roomTypeId"
            popper-class="popper"
            placeholder="全部"
            @change="submitForm"
          >

          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="入住类型" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.checkinTypeId"
            popper-class="popper"
            placeholder="全部"
          >
          
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="入住状态" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.checkinStatusId"
            popper-class="popper"
            placeholder="全部"
          >
          
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="结帐状态" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.payStatusId"
            popper-class="popper"
            placeholder="全部"
          >
          
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="是否包栋" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.boolBundle"
            popper-class="popper"
            placeholder="全部"
          >
          
          </el-select>
        </template>
      </FilterItemContainer>
      <div id="control-btns">
        <button class="close-btn" @click="() => isPanelClosed = !isPanelClosed">
          收起筛选 <el-icon class="el-icon"><ArrowUp /></el-icon>
        </button>
        <el-divider direction="vertical" style="height: 24px; margin: 0 8px;"/>
        <button class="export-detail">导出明细</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.order-list-filter {
  position: relative;
  height: 160px;  /* 原本是auto自适应的，但是不写死没有动画过渡效果 */
  padding: 16px 16px 0 0;
  background-color: var(--base-c-bg-1);
  overflow: hidden;
  transition: height 0.5s ease;

  &.isClosed {
    height: 64px;
    .close-btn .el-icon{
      transform: rotate(180deg);
    }
  }
}

.filter-list {
  display: flex;
  flex-wrap: wrap;

  .searchbar {
    --el-input-bg-color: var(--base-c-primary-1);
    opacity: 0.8;
  }
  .date-select-picker {
    display: flex;
    .el-select {
      --el-border-radius-base: 4px 0 0 4px;
      &:hover { z-index: 2; }
    }
  }
  .filter-item {
    height: 32px;
    padding-left: 16px;
    margin-bottom: 16px;
  }
  .large { width: 50%; }
  .small { width: 25%; }
}

#control-btns {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 50%;
  padding: 16px;
  background-color: var(--base-c-bg-1);
  text-align: right;

  button + button {
    margin-left: 12px;
  }
  .close-btn {
    padding: 0;
    border: none;
    color: var(--base-c-accent-1);

    &:hover {
      background-color: inherit;
      filter: brightness(1.1);
    }
    .el-icon {
      display: inline-block;
      vertical-align: middle;
      font-size: 14px;
      height: 21px;
      line-height: 24px;
    }
  }
  .export-detail {
    border: 1px solid var(--base-c-accent-1);
    color: var(--base-c-accent-1);

    &:hover {
      filter: brightness(1.1)
    }
  }
}
</style>

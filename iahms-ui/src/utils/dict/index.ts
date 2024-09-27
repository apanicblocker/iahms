import { useStore } from "vuex"
import DictData from "./DictData"

const store = useStore()

// 从后端api获取数据字典列表
function getDataDict() {
  console.log("TODO: 获取字典数据列表")
}

export function getDictLabel(dictType: string, dictValue: string) {
  console.log("TODO: 获取指定字典数据标签");
  // const dataDict = store.common.dataDict
  // dataDict
  return 1;
}

/**
 * @classdesc 字典数据
 * @property {String} label 标签
 * @property {*} value 标签
 * @property {Object} raw 原始数据
 */
export default class DictData {
  label: string
  value: string
  raw: string
  constructor(label: string, value: string, raw: string) {
    this.label = label
    this.value = value
    this.raw = raw
  }
}
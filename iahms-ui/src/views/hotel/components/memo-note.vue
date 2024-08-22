<script>
export default {
  name: 'MemoNote',
  props: {
    setMemoOnce: {
      type: String,
      default: ''
    },
    tipsText: {
      type: String,
      default: ''
    }
  },
  emits: ['updateNote'],
  data() {
    return {}
  }
}
</script>

<template>
  <div class="memo-note">
    <div class="head">
      <div class="title">备忘录</div>
      <div class="tips">{{ tipsText }}</div>
    </div>
    <div class="content">
      <textarea
        class="textarea"
        rows="16"
        tabindex="0"
        autocomplete="off"
        maxlength="32767"
        placeholder="这里是备忘的内容"
        :value="setMemoOnce"
        @change="$emit('updateNote', $event.target.value)"
      ></textarea>
    </div>
  </div>
</template>

<style scoped>
.memo-note {
  background-color: var(--base-c-bg-1);
  color: var(--base-c-text-1);
}

.head {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 2px solid var(--base-c-bg-2);

  .title {
    font-size: 18px;
    font-weight: bold;
  }
  .tips {
    font-size: 12px;
  }
}

.content {
  padding: 16px 8px 0 16px;

  .textarea {
    width: 100%;
    height: 100%;
    min-height: 21px;
    background-color: transparent;
    border: none;
    font-size: 16px;
    outline: none;
    resize: none;

    &::placeholder {
      color: var(--base-c-text-2);
    }
  }
}
</style>

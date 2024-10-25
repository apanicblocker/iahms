<script lang="ts" setup>

const registerForm = ref({
  username: "admin",
  password: "123456",
  code: "",
  uuid: "",
})
const registerRules = {

}
const submitRegisterForm = () => {
  console.log('愉快的提交了注册表单：）')
  console.log('registerForm: ', registerForm.value)
}
const clearRegisterForm = () => {
  registerForm.value = {
    username: "admin",
    password: "123456",
    code: "",
    uuid: "",
  }
}

</script>

<template>
  <div class="to-index">
    <router-link to="/">返回首页</router-link>
  </div>
  <div class="login">
    <el-form class="login-form"
      :model="registerForm"
      :rules="registerRules"
      label-width="auto"
    >
      <h2>注册帐号</h2>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" />
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input
          v-model="registerForm.code"
          :disabled="false"
          style="width: 100px;"
        />
        <img src=""
          alt="验证码图片"
          style="
            cursor: pointer;
            width: 100px;
            margin-left: 24px;
            background-color: var(--base-c-bg-3);
            border-radius: 4px;
          "
        />
      </el-form-item>
      <!-- 提交按钮 -->
      <el-button @click="submitRegisterForm">注册</el-button>
      <!-- 清空按钮 -->
      <el-button @click="clearRegisterForm">重置</el-button>
    </el-form>
  </div>
</template>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 80%;

  .login-form {
    width: 500px;
    /* margin-top: 200px; */
    padding: 0 32px 18px;
    background-color: var(--base-c-bg-1);
    border-radius: 6px;
  }
}

.to-index {
  cursor: pointer;
  padding: 16px 0;
  background-color: var(--base-c-bg-2);
  font-size: 20px;
  &:hover {
    filter: brightness(0.6);
  }
}
</style>

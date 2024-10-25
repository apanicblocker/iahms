<script lang="ts" setup>

const loginForm = ref({
  username: "admin",
  password: "123456",
  rememberMe: false,
  code: "",
  uuid: "",
})
const loginRules = {

}
const submitLoginForm = () => {
  console.log('愉快的提交了登录表单：）')
  console.log('loginForm: ', loginForm.value)
}
const clearLoginForm = () => {
  loginForm.value = {
    username: "admin",
    password: "123456",
    rememberMe: false,
    code: "",
    uuid: "",
  }
}

</script>

<template>
  <div class="login">
    <div class="to-index">
      <router-link to="/">返回首页</router-link>
    </div>
    <el-form class="login-form"
      :model="loginForm"
      :rules="loginRules"
      label-width="auto"
    >
      <h2>欢迎登录IA系统</h2>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="loginForm.password" />
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input
          v-model="loginForm.code"
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
      <el-form-item label=" " prop="rememberMe">
        <el-checkbox
          v-model="loginForm.rememberMe"
          label="记住我"
        />
      </el-form-item>
      <!-- 提交按钮 -->
      <el-button @click="submitLoginForm">提交</el-button>
      <!-- 清空按钮 -->
      <el-button @click="clearLoginForm">重置</el-button>
    </el-form>
  </div>
</template>

<style scoped>
.login {
  .to-index {
    cursor: pointer;
    padding: 16px 0;
    background-color: var(--base-c-bg-1);
    font-size: 20px;
    &:hover {
      filter: brightness(1.1);
    }
  }

  .login-form {
    display: inline-block;
    min-width: 500px;
    margin-top: 200px;
    vertical-align: middle;
    padding: 0 32px 18px;
    background-color: var(--base-c-bg-1);
    border-radius: 6px;
  }
}
</style>

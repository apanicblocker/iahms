import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  // @资源定位符映射
  resolve: {
    alias: {
      '@': '/src'
    }
  }
})

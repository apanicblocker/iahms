import path from 'path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver, VeuiResolver } from 'unplugin-vue-components/resolvers'
import Inspect from 'vite-plugin-inspect'

const pathSrc = path.resolve(__dirname, 'src')

// https://vitejs.dev/config/
export default defineConfig({
  // 映射 @ 资源定位符映射
  resolve: {
    alias: {
      '@': pathSrc
    }
  },

  plugins: [
    vue(),
    Inspect(),
    // 按需自动导入插件（form element-plus）
    AutoImport({
      // 自动导入 Vue 相关函数，如：ref, reactive, toRef 等
      imports: ['vue'],
      eslintrc: {
        enabled: true, // 此处为 true 时，会生成 .eslintrc-auto-import.json 文件，需要添加 eslint 配置支持
        filepath: './.eslintrc-auto-import.json', // 配置文件路径
      },
      // 自动导入 Element Plus 相关函数，如：ElMessage, ElMessageBox... (带样式)
      resolvers: [
        ElementPlusResolver(),
        // 自动导入图标组件
        IconsResolver({
          prefix: 'icon',
        })
      ],
      // 声明插件生成的类型声明文件位置
      dts: path.resolve(pathSrc, 'auto-imports.d.ts'),
    }),
    Components({
      resolvers: [
        // 自动导入 Element Plus 组件
        ElementPlusResolver(),
        // 自动注册图标组件
        IconsResolver({
          enabledCollections: ['ep'],
        }),
      ],
      dts: path.resolve(pathSrc, 'components.d.ts'),
    }),
    Icons({
      // 全局自动导入图标（只安装需要的）
      autoInstall: true,
    }),
  ],
})

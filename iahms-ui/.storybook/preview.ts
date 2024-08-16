import type { Preview } from "@storybook/vue3";
import '@/assets/icons/iconfont/iconfont.js'
import '@/assets/styles/main.scss'

const preview: Preview = {
  parameters: {
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
};

export default preview;

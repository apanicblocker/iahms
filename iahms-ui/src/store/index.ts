import { createStore, createLogger } from 'vuex'
import common from './modules/common'

const debug = process.env.NODE_ENV !== 'production'

const store = createStore({
  modules: {
    common,
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})

export default store;
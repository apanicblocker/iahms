const state = () => ({
  theme: 'light',
  routes: [], // 路由列表
  hotelRoutes: [], // 酒店路由列表
  // 侧边栏状态
  sidebarCollapsed: false, // 侧边栏是否折叠
  defaultOpeneds: {    // 侧边栏默认展开的菜单index列表
    order: [],      // 订单页面
    customer: [],
    report: [],
  }
})

const getters = {
  theme: (state: any) => state.theme,
  routes: (state: any) => state.routes,
  hotelRoutes: (state: any) => state.hotelRoutes,
  sidebarCollapsed: (state: any) => state.sidebarCollapsed,
  defaultOpeneds: (state: any) => (pageName: string) => {
    return state.defaultOpeneds[pageName]
  },
}

const mutations = {
  // 设置主题
  SET_THEME(state: any, theme: string) {
    state.theme = theme
    // 把主题存到localStorage
    localStorage.setItem('theme', theme)
    // 修改#app data-theme属性更改主题
    document.getElementsByTagName('html')[0]?.setAttribute('data-theme', theme)
  },
  // 设置路由列表
  SET_ROUTES(state: any, routes: any) {
    state.routes = routes
  },
  // 设置酒店路由列表
  SET_HOTEL_ROUTES(state: any, routes: any) {
    state.hotelRoutes = routes
  },
  // 设置侧边栏状态
  SET_SIDEBAR_COLLAPSED(state: any, collapsed: boolean) {
    state.sidebarCollapsed = collapsed
  },
  // 设置默认展开的菜单
  SET_DEFAULT_OPENEDS(state: any, data: { pageName: string, defaultOpeneds: string[] }) {
    // console.log("defaultOpeneds", state.defaultOpeneds);
    state.defaultOpeneds[data.pageName] = data.defaultOpeneds
  }
}

const actions = {
  // SET_THEME({ commit }: any, theme: string) {
  //   commit('SET_THEME', theme)
  // },
}

export default {
  state,
  getters,
  mutations,
  actions,
}
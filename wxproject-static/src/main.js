import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';

import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';

// element样式
Vue.use(ElementUI,{size:'small',zIndex:3000});

Vue.config.productionTip = true;

new Vue({
  router,
  render: h => h(App),
  // mounted(){
  //   //要是在刷新是获取浏览器地址 截取对应的路由 当前激活菜单的 index 重新设置即可
  //   :default-active=“activeIndex”，这里的activeIndex 和路由一致都是 entity.name
  //   let start = window.location.href.lastIndexOf('/');
  //   let path = window.location.href.slice(start+1);
  //   this.activeIndex = path;
  // }
}).$mount('#app');

import router from './router'
import Vue from 'vue'
import App from './App'
import loginUser from './loginUser.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios';
// import request from './request/request.js'
// import request from 'request/request.js';//axios实例化后引入request



// Vue.prototype.request = request //放入全局
//在vue脚手架中使用elementui
Vue.use(ElementUI);

//设置全局调用axios的引用名
// Vue.prototype.$axios = Axios
//设置基本的后端访问端口
//Axios.defaults.baseURL = 'http://127.0.0.1:8083'

//阻止显示生产模式的消息。
// 如果没有这行代码，或者设置为true，控制台就会多出这么一段代码。
// You are running Vue in development mode.
// Make sure to turn on production mode when deploying for production.
Vue.config.productionTip = false


new Vue({
  el: '#app',
  router,
  render: h => h(App),
})

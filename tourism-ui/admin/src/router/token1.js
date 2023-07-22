import axios from "axios";
import cookie from 'js-cookie';
//创建默认实列
const instance=axios.create({
    base: process.env.BASE_URL,
    timeout:5000,
});
//拦截器
instance.interceptors.request.use(config=> {
  if (cookie.get('token')) {
    //把获取cookie值放到header里面
    config.headers['token'] = cookie.get('token');
  }
  return config;
},
  error => {
  return Promise.reject(error)
})

//暴露实例出去
export default instance;

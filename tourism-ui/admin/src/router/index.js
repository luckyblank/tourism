import Vue from 'vue'
import Router from 'vue-router'
import routes from './routes'
Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  // scrollBehavior (to, from, savedPosition) {
  //   return { x: 0, y: 0 }
  // }
})

router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token');
  if (to.name !== 'Login' && !token)
    next({ name: 'Login' })
  else next()

})




  
// https://github.com/vuejs/vue-router/issues/2881#issuecomment-520554378
const originalPush = router.push
router.push = function push (location, onResolve, onReject) {
  // if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default router

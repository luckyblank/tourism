import Vue from 'vue'
import Router from 'vue-router'
import hotel from '../components/hotel/hotel.vue'
import homepage from '../components/homepage.vue'
import loginUser from '../loginUser.vue'
import register from '../register.vue'
import hotel_detail from '../components/hotel/hotel_detail.vue'
import scenery from '../components/scenery/scenery.vue'
import foreign from '../components/scenery/foreign.vue'
import domestic from '../components/scenery/domestic.vue'
import scenery_detail from '../components/scenery/scenery_datail.vue'
import order from '../components/scenery/order.vue'
import hotelOrder from '../components/hotel/hotelOrder.vue'
//景区
import scenic from '../components/scenicSpot/scenic.vue'
import scenicDetail from '../components/scenicSpot/scenicDetail.vue'
import scenicOrder from '../components/scenicSpot/scenicOrder.vue'
//订单
import myOrder from '../components/order/order.vue'
import orderDetail from '../components/order/orderDetail.vue'
//保险
import insurance from '../components/insurance/insurance.vue'
import insuranceDetail from '../components/insurance/insuranceDetail.vue'
import insuranceOrder from '../components/insurance/insuranceOrder.vue'
//路线
import route from '../components/route/route.vue'
import routeDetail from '../components/route/routeDetail.vue'
import routeOrder from '../components/route/routeOrder.vue'
//我的保险
import myInsurance from '../components/myInsurance/myInsurance.vue'
import myInsuranceDetail from '../components/myInsurance/myInsuranceDetail.vue'
//攻略
import strategyPut from '../components/strategy/strategyPut.vue'
import strategy from '../components/strategy/strategy.vue'
import strategyDetail from '../components/strategy/strategyDetail.vue'
import Detail from '../components/strategy/Detail.vue'
import UpdateStrategy from '../components/mystrategy/UpdateStrategy.vue'



//留言
import message from '../components/message/message.vue'
import messageDetail from '../components/message/messageDetail.vue'
import Detail2 from '../components/message/Detail.vue'
//我的攻略
import myStrategy from '../components/mystrategy/myStrategy.vue'
//我的收藏
import myCollect from '../components/myCollect/myCollect.vue'
//火车票
import car from '../components/car/car.vue'
import carDetail from '../components/car/carDetail.vue'
import carOrder from '../components/car/carOrder.vue'

//火车票
import notic from '../components/notic/notic.vue'

//updateMaterial
import updatePassword from '../components/info/updatePassword.vue'
import updateMaterial from '../components/info/updateMaterial.vue'
import myInfo from '../components/info/myInfo.vue'

Vue.use(Router)
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path:"/",
      redirect:'/loginUser',
    },
    {
      path:'/homepage',
      component:homepage,
    },
    {
      //周边游分页
      path: '/scenery',
      component:scenery,

    },
    {
      //出境游分页
      path: '/foreign',
      component:foreign,

    },
    {
      //国内游分页
      path: '/domestic',
      component:domestic,

    },
    {
      //景点详情分页
      path: '/scenery_detail',
      component:scenery_detail,

    },
    {
      //景点订单分页
      path: '/order',
      component:order,   
    },
    {
      //酒店订单分页
      path: '/hotelOrder',
      component:hotelOrder,   
    },
    {
      //酒店分页
      path: '/hotel',
      component:hotel,

    },
    {
      //景区详情页
      path: '/hotel_detail',
      component:hotel_detail,

    },
    {
      //景区订单
      path: '/scenicOrder',
      component:scenicOrder,   
    },
    {
      //景区分页
      path: '/scenic',
      component:scenic,

    },

    {
      //景区详情页
      path: '/scenicDetail',
      component:scenicDetail,

    },
    {
      //保险订单
      path: '/insuranceOrder',
      component:insuranceOrder,   
    },
    {
      //保险分页
      path: '/insurance',
      component:insurance,

    },

    {
      //路线详情页
      path: '/routeDetail',
      component:routeDetail,

    },

    {
      //路线下单
      path: '/routeOrder',
      component:routeOrder,   
    },
    {
      //路线分页
      path: '/route',
      component:route,

    },

    {
      //保险详情页
      path: '/insuranceDetail',
      component:insuranceDetail,

    },

    {
      //我的订单
      path: '/myOrder',
      component:myOrder,   
    },
    {
      //订单详情
      path: '/orderDetail',
      component:orderDetail,
    },
    {
      //保险详情页
      path: '/myInsuranceDetail',
      component:myInsuranceDetail,

    },

    {
      //保险列表
      path: '/myInsurance',
      component:myInsurance,   
    },

    {
      //攻略列表
      path: '/strategy',
      component:strategy,
    },
    {
      //攻略详情页
      path: '/strategyDetail',
      component: strategyDetail,

    },

    {
      //发表攻略
      path: '/strategyPut',
      component:strategyPut,   
    },
    
    {
      //发表攻略
      path: '/Detail',
      component:Detail,   
    },

        
    {
      //发表攻略
      path: '/Detail2',
      component:Detail2,   
    },

    {
      //修改攻略
      path: '/updateStrategy',
      component:UpdateStrategy,   
    },

    

    {
      //留言列表
      path: '/message',
      component:message,
    },
    {
      //留言详情页
      path: '/messageDetail',
      component: messageDetail,

    },

    {
      //我的攻略列表
      path: '/myStrategy',
      component:myStrategy,
    },
    {
      //我的收藏列表
      path: '/myCollect',
      component:myCollect,
    },
 
    {
      //火车详情页
      path: '/carDetail',
      component:carDetail,

    },

    {
      //火车下单
      path: '/carOrder',
      component:carOrder,   
    },
    {
      //火车分页
      path: '/car',
      component:car,

    },
    {
      //火车分页
      path: '/notic',
      component:notic,

    },
    {
      //修改密码
      path: '/updatePassword',
      component:updatePassword,

    },
    {
      //修改个人资料
      path: '/updateMaterial',
      component:updateMaterial,

    },
    {
      //修改密码
      path: '/myInfo',
      component:myInfo,

    },



    {
      //登录页
      path:'/loginUser',
      name:'loginuser',
      component:()=>import('@/loginUser')
    },
    {
      //注册页
      path:'/register',
      name:'register',
      component:()=>import('@/register')
    },

  ]
})

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

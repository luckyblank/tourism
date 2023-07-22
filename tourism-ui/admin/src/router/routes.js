import Layout from '../views/Layout.vue';

/**
 * @type {import('vue-router/types/router').RouteConfig[]}
 */
const routes = [
  {
    path: '/',
    redirect:'/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },

  {
    path: '/layout',
    name:"Layout",
    component: Layout,
    meta: { requireAuth: true, title: ' 车票管理' },
    children: [
 
      {
        path: 'carManager',
        name: 'CarManager',
        component: () => import('../views/admin/Car/Manager.vue'),
        meta: { requireAuth: true, title: ' 车票管理' },
      },
      {
        path: 'updateCar',
        name: 'UpdateCar',
        component: () => import('../views/admin/Car/Update.vue'),
        meta: { requireAuth: true, title: '修改车票 ' },
      },
      {
        path: 'addCar',
        name: 'AddCar',
        component: () => import('../views/admin/Car/Add.vue'),
        meta: { requireAuth: true, title: ' 新增车票' },
      },
      {
        path: 'carDetail',
        name: 'CarDetail',
        component: () => import('../views/admin/Car/Detail.vue'),
        meta: { requireAuth: true, title: ' 车票细节' },
      },
      {
        path: 'scenicSpot',
        name: 'ScenicSpot',
        component: () => import('../views/admin/ScenicSpot/Manager.vue'),
        meta: { requireAuth: true, title: ' 景点管理' },
      },
      {
        path: 'updateScenicSpot',
        name: 'UpadteScenicSpot',
        component: () => import('../views/admin/ScenicSpot/Upadte.vue'),
        meta: { requireAuth: true, title: '修改景点 ' },
      },
      {
        path: 'addScenicSpot',
        name: 'AddScenicSpot',
        component: () => import('../views/admin/ScenicSpot/Add.vue'),
        meta: { requireAuth: true, title: ' 新增景点' },
      },
      {
        path: 'scenicSpotDetail',
        name: 'ScenicSpotDetail',
        component: () => import('../views/admin/ScenicSpot/Detail.vue'),
        meta: { requireAuth: true, title: ' 景点细节' },
      },

      {
        path: 'hotel',
        name: 'Hotel',
        component: () => import('../views/admin/Hotel/Manager.vue'),
        meta: { requireAuth: true, title: ' 酒店管理' },
      },
      {
        path: 'updateHotel',
        name: 'UpdateHotel',
        component: () => import('../views/admin/Hotel/Update.vue'),
        meta: { requireAuth: true, title: '修改酒店' },
      },
      {
        path: 'addHotel',
        name: 'AddHotel',
        component: () => import('../views/admin/Hotel/Add.vue'),
        meta: { requireAuth: true, title: ' 新增酒店' },
      },
      {
        path: 'hotelDetail',
        name: 'HotelDetail',
        component: () => import('../views/admin/Hotel/Detail.vue'),
        meta: { requireAuth: true, title: '酒店细节' },
      },
      {
        path: 'strategy',
        name: 'Strategy',
        component: () => import('../views/admin/Strategy/Manager.vue'),
        meta: { requireAuth: true, title: ' 攻略管理' },
      },
      {
        path: 'updateStrategy',
        name: 'UpdateStrategy',
        component: () => import('../views/admin/Strategy/Update.vue'),
        meta: { requireAuth: true, title: '修改攻略' },
      },
      {
        path: 'addStrategy',
        name: 'AddStrategy',
        component: () => import('../views/admin/Strategy/Add.vue'),
        meta: { requireAuth: true, title: ' 新增攻略' },
      },
      {
        path: 'strategyDetail',
        name: 'StrategyDetail',
        component: () => import('../views/admin/Strategy/Detail.vue'),
        meta: { requireAuth: true, title: ' 攻略细节' },
      },
      {
        path: 'travelRoute',
        name: 'TravelRoute',
        component: () => import('../views/admin/travelRoute/Manager.vue'),
        meta: { requireAuth: true, title: ' 路线管理' },
      },
      {
        path: 'updateTravelRoute',
        name: 'UpdateTravelRoute',
        component: () => import('../views/admin/travelRoute/Update.vue'),
        meta: { requireAuth: true, title: '修改路线' },
      },
      {
        path: 'addTravelRoute',
        name: 'AddTravelRoute',
        component: () => import('../views/admin/travelRoute/Add.vue'),
        meta: { requireAuth: true, title: ' 新增路线' },
      },
      {
        path: 'travelRouteDetail',
        name: 'TravelRouteDetail',
        component: () => import('../views/admin/travelRoute/Detail.vue'),
        meta: { requireAuth: true, title: ' 路线细节' },
      },
      {
        path: 'insurance',
        name: 'Insurance',
        component: () => import('../views/admin/Insurance/Manager.vue'),
        meta: { requireAuth: true, title: ' 保险管理' },
      },
      {
        path: 'updateInsurance',
        name: 'UpdateInsurance',
        component: () => import('../views/admin/Insurance/Update.vue'),
        meta: { requireAuth: true, title: '修改保险' },
      },
      {
        path: 'addInsurance',
        name: 'AddInsurance',
        component: () => import('../views/admin/Insurance/Add.vue'),
        meta: { requireAuth: true, title: ' 新增保险' },
      },
      {
        path: 'insuranceDetail',
        name: 'InsuranceDetail',
        component: () => import('../views/admin/Insurance/Detail.vue'),
        meta: { requireAuth: true, title: ' 保险细节' },
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../views/admin/Message/Manager.vue'),
        meta: { requireAuth: true, title: ' 留言管理' },
      },
      {
        path: 'updateMessage',
        name: 'UpdateMessage',
        component: () => import('../views/admin/Message/Update.vue'),
        meta: { requireAuth: true, title: '修改留言' },
      },
      {
        path: 'addMessage',
        name: 'AddMessage',
        component: () => import('../views/admin/Message/Add.vue'),
        meta: { requireAuth: true, title: ' 新增留言' },
      },
      {
        path: 'messageDetail',
        name: 'MessageDetail',
        component: () => import('../views/admin/Message/Detail.vue'),
        meta: { requireAuth: true, title: ' 留言细节' },
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('../views/admin/User/Manager.vue'),
        meta: { requireAuth: true, title: ' 注册用户管理' },
      },
      {
        path: 'updateUser',
        name: 'UpdateUser',
        component: () => import('../views/admin/User/Update.vue'),
        meta: { requireAuth: true, title: '修改用户' },
      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import('../views/admin/User/Add.vue'),
        meta: { requireAuth: true, title: ' 新增用户' },
      },
      {
        path: 'addRegister',
        name: 'AddRegister',
        component: () => import('../views/admin/User/AddRegister.vue'),
        meta: { requireAuth: true, title: ' 新增用户' },
      },
      {
        path: 'userDetail',
        name: 'UserDetail',
        component: () => import('../views/admin/User/Detail.vue'),
        meta: { requireAuth: true, title: ' 用户细节' },
      },

      {
        path: 'admin',
        name: 'Admin',
        component: () => import('../views/admin/admin2/Manager.vue'),
        meta: { requireAuth: true, title: '管理员管理' },
      },
      {
        path: 'updateAdmin',
        name: 'UpdateAdmin',
        component: () => import('../views/admin/admin2/Update.vue'),
        meta: { requireAuth: true, title: '修改管理员' },
      },
      {
        path: 'addAdmin',
        name: 'AddAdmin',
        component: () => import('../views/admin/admin2/Add.vue'),
        meta: { requireAuth: true, title: ' 新增管理员' },
      },
      {
        path: 'adminDetail',
        name: 'AdminDetail',
        component: () => import('../views/admin/admin2/Detail.vue'),
        meta: { requireAuth: true, title: ' 管理细节' },
      },

      {
        path: 'order',
        name: 'Order',
        component: () => import('../views/admin/Order/Manager.vue'),
        meta: { requireAuth: true, title: '订单管理' },
      },
      {
        path: 'updateOrder',
        name: 'UpdateOrder',
        component: () => import('../views/admin/Order/Update.vue'),
        meta: { requireAuth: true, title: '修改订单' },
      },
      
      {
        path: 'orderDetail',
        name: 'OrderDetail',
        component: () => import('../views/admin/Notic/Detail.vue'),
        meta: { requireAuth: true, title: ' 订单细节' },
      },
      {
        path: 'notic',
        name: 'Notic',
        component: () => import('../views/admin/Notic/Manager.vue'),
        meta: { requireAuth: true, title: '订单管理' },
      },
      {
        path: 'updateNotic',
        name: 'UpdateNotic',
        component: () => import('../views/admin/Notic/Update.vue'),
        meta: { requireAuth: true, title: '修改公告' },
      },
      {
        path: 'addNotic',
        name: 'AddNotic',
        component: () => import('../views/admin/Notic/Add.vue'),
        meta: { requireAuth: true, title: '新增公告' },
      },
      {
        path: 'noticDetail',
        name: 'NoticDetail',
        component: () => import('../views/admin/Order/Detail.vue'),
        meta: { requireAuth: true, title: ' 公告细节' },
      },

      {
        path: 'car',
        name: 'Car',
        component: () => import('../views/analysis/Car.vue'),
        meta: { requireAuth: true, title: ' 出行交通分析' },
      },
      {
        path: 'orderanAlysis',
        name: 'OrderanAlysis',
        component: () => import('../views/analysis/Order.vue'),
        meta: { requireAuth: true, title: ' 订单分析' },
      },
      {
        path: 'insurance',
        name: 'insuranceManager',
        component: () => import('../views/analysis/Insurance.vue'),
        meta: { requireAuth: true, title: ' 保险分析' },
      },
      {
        path: 'user',
        name: 'UserManager',
        component: () => import('../views/analysis/User.vue'),
        meta: { requireAuth: true, title: ' 用户分析' },
      },
      {
        path: 'hotel',
        name: 'HotelManager',
        component: () => import('../views/analysis/Hotel.vue'),
        meta: { requireAuth: true, title: ' 酒店分析' },
      },
      {
        path: 'strategy',
        name: 'StrategyManager',
        component: () => import('../views/analysis/Strategy.vue'),
        meta: { requireAuth: true, title: ' 攻略分析' },
      },
      {
        path: 'scient',
        name: 'Scient',
        component: () => import('../views/analysis/Scient.vue'),
        meta: { requireAuth: true, title: ' 景点分析' },
      },
      {
        path: 'travelRoute',
        name: 'TravelRouteManager',
        component: () => import('../views/analysis/TravelRoute.vue'),
        meta: { requireAuth: true, title: ' 路线分析' },
      },
      {
        path: 'homeTravel',
        name: 'HomeTravel',
        component: () => import('../views/analysis/HomeTravel.vue'),
        meta: { requireAuth: true, title: ' 首页' },
      },
      {
        path: 'myInfo',
        name: 'MyInfo',
        component: () => import('../views/admin/admin2/myInfo.vue'),
        meta: { requireAuth: true, title: ' 个人信息' },
      },
      {
        path: 'updateMaterial',
        name: 'UpdateMaterial',
        component: () => import('../views/admin/admin2/updateMaterial.vue'),
        meta: { requireAuth: true, title: ' 首页' },
      },
      {
        path: 'updatePassword',
        name: 'UpdatePassword',
        component: () => import('../views/admin/admin2/updatePassword.vue'),
        meta: { requireAuth: true, title: ' 首页' },
      },
    
      {
        path: 'role',
        name: 'Role',
        component: () => import('../views/admin/Role/Manager.vue'),
        meta: { requireAuth: true, title: '角色管理' },
      },
     

    ],
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/admin/update/register.vue'),
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue'),
  },
  {
    path: '/error',
    name: 'Error',
    component: () => import('../views/Error1.vue'),
  },
  {
    path: '*',
    component: () =>
      import(/* webpackChunkName: 'common' */ '../views/Error.vue'),
  },
];

// const router = new VueRouter({
//   mode: 'history',
//   routes,
// })
// router.beforeEach((to, from, next) => {
//   const token = localStorage.getItem('toekn');

//   if (to.name !== 'Login' && !token)
//     next({ name: 'Login' })
//   else next()

// })




export default routes;

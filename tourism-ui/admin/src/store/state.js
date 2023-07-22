import { storage } from '../utils'

export default {
  /**
   * 页面标题
   */
  title: process.env.VUE_APP_TITLE,

  /**
   * 客户端会话信息
   */
  session: storage.get('session') ?? {},

  /**
   * 侧边导航栏
   */
  sidebar: {
    /**
     * 是否收起边栏
     */
    collapse: storage.get('sidebar_collapse') ?? false,

    /**
     * 侧边菜单
     */
    menus: [
      // {
      //   text: '课程管理',
      //   icon: 's-management',
      //   name: 'Courses'
      // },
      // {
      //   text: '用户管理',
      //   icon: 's-custom',
      //   name: 'Users'
      // },
      {
        text: '首页',
        icon: 's-custom',
        name: 'HomeTravel' 
         
        
      },

      {
        text: '个人中心',
        icon: 's-custom',
        children: [
          { text: '个人信息', name: 'MyInfo' }    ,     
          { text: '修改密码', name: 'UpdatePassword' },  
        ]
      },
      {
        text: '权限管理',
        icon: 's-custom',
        children: [
          
          { text: '角色管理', name: 'Role' }    ,     
           
        ]
      },
       
      {
        text: '用户管理',
        icon: 's-marketing',
        children: [
          
          { text: '管理用户', name: 'User' },
          // { text: '注册管理员', name: 'Register' },

          { text: '管理员', name: 'Admin' }    ,     

        ]
      },
      
      {
        text: '业务管理',
        icon: 's-marketing',
        children: [
          { text: '订单管理', name: 'Order' }
        ]
      },
      {
        text: '内容管理',
        icon: 's-marketing',
        children: [
          { text: '车票管理', name: 'CarManager' },
          { text: '景点管理', name: 'ScenicSpot' },
          { text: '酒店管理', name: 'Hotel' },
          { text: '攻略管理', name: 'Strategy' } , 
          { text: '旅游路线管理', name: 'TravelRoute' },
           { text: '留言管理', name: 'Message' },
          { text: '保险管理', name: 'Insurance' },
          { text: '公告管理', name: 'Notic' } ,



        ]
      },
      {
        text: '数据分析',
        icon: 's-marketing',
        children: [
          { text: '用户分析', name: 'UserManager' },
          { text: '路线分析', name: 'TravelRouteManager' },
          { text: '出行交通分析', name: 'Car' },
          { text: '酒店分析', name: 'HotelManager' },
          { text: '景点分析', name: 'Scient' } , 
          { text: '订单分析', name: 'OrderanAlysis' },
          { text: '攻略分析', name: 'StrategyManager' } ,
          { text: '保险分析', name: 'insuranceManager' }  

        ]
      },
  

    ]
  },

  /**
   * 顶部导航栏
   */
  navbar: {
    breadcrumbs: []
  },

  // ==================== DEMO ====================

  /**
   * 计数器
   */
  count: storage.get('demo_count') ?? 0
}

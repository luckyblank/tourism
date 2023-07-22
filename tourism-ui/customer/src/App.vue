<template>
  <div id="app">
    <el-row>
      <!-- 最顶部的欢迎信息和登录注册按钮-->
      <el-col :span="12">
        <span style="color: red;float: left; margin-left: 30px;font-size: 18px;"
          >你好！欢迎来到星星旅游网！</span
        >

        <el-button
          round
          size="mini"
          style="float: left; color: dodgerblue; background-color: #D3DCE6;"
          @click="login"
          >个人登录</el-button
        >
        <el-button
          round
          size="mini"
          style="float: left; color: dodgerblue; background-color: #D3DCE6;"
          @click="loginOut"
          >退出</el-button
        >
        <el-button
          round
          size="mini"
          style="float: left; color:dodgerblue; background-color: #D3DCE6;"
          @click="register"
          >免费注册</el-button
        >
      </el-col>

      <!-- <el-col :span="12">
        <el-button
          type="text"
          icon="el-icon-mobile-phone"
          size="mini"
          style="float: right; margin-right: 50px;"
          >手机版</el-button
        >
        <el-button
          type="text"
          icon="el-icon-s-custom"
          size="mini"
          style="float: right; margin-right: 20px;"
          >管理我的网站</el-button
        >
        <el-button
          type="text"
          icon="el-icon-office-building"
          size="mini"
          style="float: right; margin-right: 5px;"
          >开店</el-button
        >
      </el-col> -->
    </el-row>

    <!-- 顶部搜索框和图片信息-->
    <el-row>
      <!-- 图片-->
      <el-col :span="5">
        <!-- <el-image
          style="width: 100px; height: 65px;  float: left; margin-left: 20px;"
          :src="url"
          fit="contain"
        >
        </el-image> -->
      </el-col>
      <!-- 搜索框-->
      <el-col
        :span="10"
        style="float: right; margin-top: 15px; margin-right: 20px;"
      >
        <el-input placeholder="请输入关键字搜索" v-model="content" clearable>
          <el-select v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="周边游" value="1"></el-option>
            <el-option label="国内游" value="2"></el-option>
            <el-option label="出境游" value="3"></el-option>
            <el-option label="门票" value="4"></el-option>
            <el-option label="酒店" value="5"></el-option>
          </el-select>
          <el-button
            slot="append"
            icon="el-icon-search"
            style="background-color: deepskyblue; color:white;"
            >查询</el-button
          >
        </el-input>
      </el-col>
    </el-row>

    <!-- 导航菜单-->
    <el-container>
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          router
        >
          <el-menu-item index="/homepage">首页</el-menu-item>
          <el-menu-item index="/car">火车票 </el-menu-item>
          <el-menu-item index="/route">旅游路线</el-menu-item>
          <el-menu-item index="/hotel">酒店</el-menu-item>
          <el-menu-item index="/scenic">景区</el-menu-item>
          <el-menu-item index="/insurance">保险</el-menu-item>

          <el-submenu index="78008">
            <template slot="title">旅游攻略</template>
            <el-menu-item index="/strategy">攻略</el-menu-item>
            <el-menu-item index="/myStrategy">我的攻略</el-menu-item>
          </el-submenu>

          <el-submenu index="788">
            <template slot="title">个人信息</template>
            <el-menu-item index="/myInfo">我的资料</el-menu-item>
            <el-menu-item index="/myOrder">我的订单</el-menu-item>
            <el-menu-item index="/myInsurance">我的保险</el-menu-item>
            <el-menu-item index="/updatePassword">修改密码</el-menu-item>
          </el-submenu>
          <el-menu-item index="/message">去留言</el-menu-item>
          <el-menu-item index="/notic">公告</el-menu-item>
          <el-menu-item index="/myCollect">我的收藏</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "App",
  data() {
    return {
      activeIndex: "/homepage",
      activeIndex: "/scenery",
      activeIndex: "/domestic",
      activeIndex: "/foreign",
      activeIndex: "/hotel",

      url: "https://i.postimg.cc/J7KjNt96/title.png",
      content: "",
      select: "",
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key);
    },
    login() {
      console.log("用户跳转至登录页面");
      this.$router.push({ path: "/loginUser" });
    },
    loginOut() {
      console.log("退出登录");
      window.sessionStorage.clear();
      this.$router.push("/loginUser");
    },
    register() {
      console.log("用户跳转至注册页面");
      this.$router.push({ path: "/register" });
    },
    insurance() {
      this.$router.push({ path: "/scenic" });
    },
  },
};
</script>

<style>
#app {
  width: 1080px;
  text-align: center;
  margin: 0 auto;
  margin-top: 20px;
}
</style>

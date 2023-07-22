<template>
  <el-header class="navbar" height="auto">
    <el-button
      class="hamburger"
      type="text"
      :icon="sidebar.collapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"
      @click="toggleCollapse"
    ></el-button>
    <!-- <div class="img2">
      <button class="button" @click="logout1">退出</button>
      <img class="image1" src="../views/admin/img/dog1.jpg" />
  
    </div> -->
    <el-breadcrumb separator="/" replace>
      <el-breadcrumb-item :to="{ name: 'Home' }">Home</el-breadcrumb-item>
      <el-breadcrumb-item
        v-for="item in navbar.breadcrumbs"
        :key="item.name"
        :to="item"
        >{{ item.text || item.name }}</el-breadcrumb-item
      >
    </el-breadcrumb>
    <!-- <span style="color: blue; margin-left: 4px" @click="logout1">退出</span> -->
    <el-dropdown @command="handleCommand" >
      <img class="image1 avatar"  src="../views/admin/img/dog1.jpg" />
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="logout" divided ><i class="el-icon-s-logout"></i>登出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-header>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "Navbar",

  computed: mapGetters({
    navbar: "navbar",
    sidebar: "sidebar",
    session: "session",
  }),

  created() {
    this.$store.dispatch("getCurrentUser");
  },

  methods: {
    ...mapActions({
      toggleCollapse: "toggleSidebarCollapse",
    }),
    async handleCommand(command) {
      window.sessionStorage.clear();
      this.$router.push("/");
      // switch (command) {
      //   case "logout":
      //     await this.$store.dispatch("deleteToken");
      //     this.$router.replace({ name: "Login" });
      //     break;
      // }
    },
    logout1() {
      // location.reload()
      // localStorage.clear();
      window.sessionStorage.clear();
      this.$router.push("/");
    },
  },
};
</script>

<style lang="scss">
.navbar {
  position: sticky;
  top: 0;
  z-index: 100;
  display: flex;
  align-items: center;
  padding: 0 !important;
  background-color: #fff;

  .hamburger {
    margin-right: 10px;
    padding: 15px;
    font-size: 20px;
    border: 0;
    border-radius: 0;

    &:hover {
      background-color: rgba(0, 0, 0, 0.1);
    }
  }

  .el-dropdown {
    margin-left: auto;
  }

  .avatar {
    display: block;
    margin: 10px;
    width: 30px;
    height: 30px;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 50%;
    box-sizing: border-box;
  }
  .image1 {
    width: 30px;
    height: 30px;
    border-radius: 200px;
  }
  .img2 {
    margin-left: 650px;
  }
}
</style>

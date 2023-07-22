<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form
          :model="loginForm"
          label-width="100px"
          :rules="rules"
          ref="loginForm"
        >
          <el-form-item label="账号" prop="username">
            <el-input
              style="width: 200px"
              type="text"
              v-model="loginForm.username"
              autocomplete="off"
              size="small"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              style="width: 200px"
              type="password"
              v-model="loginForm.password"
              show-password
              autocomplete="off"
              size="small"
              @keyup.enter.native="confirm"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="confirm"
              :disabled="confirm_disabled"
              >确 定</el-button
            >
          </el-form-item>

          <!-- 底部按键-->
          <!-- <el-form-item>
            <el-button type="text" size="small" style="float: left"
              >忘记密码</el-button
            >
            <el-button
              type="text"
              @click="register"
              size="small"
              style="float: right"
              >立即注册</el-button
            >
          </el-form-item> -->
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { ApiService } from "@/services";
export default {
  name: "Login",
  data() {
    return {
      confirm_disabled: false,
      loginForm: {
        username: "root",
        password: "root",
      },
      rules: {
        no: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    //跳转至注册页面
    register() {
      console.log("用户跳转至注册页面");
      //  this.$router.push({ path: "/register" });

      // this.$router.push({ name: "Register" });

      //  this.$router.replace("Register");

      this.$router.replace("/register");
    },

    confirm() {
      this.confirm_disabled = true;

      //valid成功为true，失败为false
      //去后台验证用户名密码
      ApiService.post("/admin/login", {
        ...this.loginForm,
      }).then((res) => {
        console.log(res);
        // console.log(res.data);
        if (res.data.code == 200) {
          //
          let data = res.data.data;
          //localStorage.setItem("token", data.access_token);
          sessionStorage.setItem("token", data.token);
          sessionStorage.setItem("id", data.adminid);

          //跳转到主页
          // this.$router.push("/layout/homeTravel");
          this.$router.replace("/layout/homeTravel");
        } else {
          this.confirm_disabled = false;
          alert("校验失败，用户名或密码错误！");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #b3c0d1;
}
.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: #fff;
  border-radius: 5%;
}
.login-title {
  margin: 20px 0;
  text-align: center;
}
.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>

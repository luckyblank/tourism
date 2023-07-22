<template>
  <div class="login-body">
    <div class="login-body-inner">
      <div class="login-body-inner-title">管理端登录</div>
      <div class="login-body-inner-item">
        <div>
          <div class="mobileIcon"></div>
          <input type="text" v-model="loginForm.username" autocomplete="off" placeholder="请输入账号">
          <p class="login-body-inner-p"></p>
        </div>
      </div>
      <div class="login-body-inner-item">
        <div>
          <div class="passwordIcon"></div>
          <input type="password" v-model="loginForm.password" autocomplete="off" placeholder="请输入密码">
          <p class="login-body-inner-p"></p>
        </div>
      </div>
      <div class="login-body-inner-item">
        <a class="login-body-inner-submit" @click="confirm" :disabled="confirm_disabled">登录</a>
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
      if(this.loginForm.username == "" || this.loginForm.password == ""){
        this.$message.warning('请完整填写账号和密码！');
        return false;
      }

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
          // alert("校验失败，用户名或密码错误！");
          this.$message.error('校验失败，账号或密码错误！');
          return false;
        }
      });
    },
  },
};
</script>
<style scoped>
/*注意语法格式，不能缺少 ; */
/*也要注意引入的css路径是否正确*/
@import '../assets/css/login.style.css';
</style>

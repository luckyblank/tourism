<template>
  <div>
    <el-row>
      <el-col>
        <!-- 分界线-->
        <el-divider>
          <span style=" font-size: 25px; font-weight: bold;"
            >秃游网修改密码</span
          >
        </el-divider>

        <el-form :model="ruleForm" ref="ruleForm" class="login-box">
          <el-form-item prop="password">
            <el-input
              prefix-icon="el-icon-unlock"
              type="password"
              show-password
              placeholder="请输入手机号码"
              v-model="ruleForm.password"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button
              type="primary"
              @click="confirm"
              :disabled="confirm_disabled"
              >确定</el-button
            >
          </el-form-item>

          <el-form-item>
            <el-button
              type="text"
              size="small"
              style="float: left; "
              @click="bach"
              >返回</el-button
            >
            <el-button
              type="text"
              @click="register"
              size="small"
              style="float: right;"
              >立即注册</el-button
            >
          </el-form-item>
        </el-form>
        <span style="color: red; size: 20px;">
          注意保管密码！！下次不给你找回来呵呵</span
        >
        <br />
        <span style="color: black; size: 15px;"> 密码：</span>
        <span style="color: black; size: 15px;"> {{ password }}</span>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import { ApiService } from "@/services";
import newAxios from "@/utils/axios.js";

export default {
  name: "loginUser",
  data() {
    return {
      ruleForm: {},
      password: "",
      confirm_disabled: false,
    };
  },
  methods: {
    //跳转至注册页面
    register() {
      console.log("用户跳转至注册页面");
      this.$router.push({ path: "/register" });
    },

    //跳转上一界面
    bach() {
      this.$router.back();
    },

    confirm() {
      this.confirm_disabled = true;
      newAxios
        .post("/admin/findPassword", {
          ...this.ruleForm,
        })
        .then((res) => {
          if (res.data.code == 200) {
            // this.$router.replace("/loginUser");
            this.password = res.data.data;
          } else {
            this.$message.error("旧密码输入错误！！");
            return false;
          }
        });
    },
  },
};
</script>

<style scoped>
.login-box {
  border: 1px solid #dcdfe6;
  width: 350px;
  margin: 0 auto;
  margin-top: 100px;
  padding: 50px 50px 10px 50px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}
</style>

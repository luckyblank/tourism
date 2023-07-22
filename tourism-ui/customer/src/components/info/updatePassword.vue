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
              placeholder="请输入旧密码"
              v-model="ruleForm.password"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>

          <el-form-item prop="newPassword">
            <el-input
              type="password"
              show-password
              prefix-icon="el-icon-unlock"
              placeholder="请输入新密码"
              v-model="ruleForm.newPassword"
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

          <!-- <el-form-item></el-form-item> -->

          <!-- <el-form-item>
            <el-input placeholder="请输入验证码" v-model="ruleForm.code" style="width: 120px;"></el-input>
            <img :src=ruleForm.url />
            <el-button type="text"  @click="newCode">点击刷新</el-button>
          </el-form-item> -->

          <!--设置登录按钮-->
          <!-- <el-form-item>
            <el-button
              type="primary"
              style="width: 250px;"
              @click="submitForm('ruleForm')"
              >登录</el-button
            >
          </el-form-item> -->

          <!-- 底部按键-->
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
      ruleForm: {
        newPassword: "",
        password: "",
      },

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
        .post("/admin/updatePassword", {
          ...this.ruleForm,
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.$router.replace("/loginUser");
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

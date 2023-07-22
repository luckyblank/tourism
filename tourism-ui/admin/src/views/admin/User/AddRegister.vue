<template>
  <el-card class="form-container" shadow="never" style="width: 600px">
    <el-form
      :model="homeAdvertise"
      prop="userChangeForm"
      ref="userChangeForm"
      label-width="150px"
      size="small"
      :rules="rules"
      style="width: 500px"
    >
      <!-- 分界线-->
      <el-divider>
        <span style="font-size: 25px; font-weight: bold">新增用户</span>
      </el-divider>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="homeAdvertise.username"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="homeAdvertise.password"
          class="input-width"
          type="password"
          show-password
        ></el-input>
      </el-form-item>

      <el-form-item label="真实名" prop="name">
        <el-input v-model="homeAdvertise.name" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="身份证" prop="icCode">
        <el-input v-model="homeAdvertise.icCode" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="手机号码" prop="linkTel">
        <el-input
          v-model="homeAdvertise.linkTel"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="qq邮箱" prop="email">
        <el-input v-model="homeAdvertise.email" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="省份">
        <el-select v-model="homeAdvertise.province">
          <el-option
            v-for="item in selectList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上传头像">
        <el-upload
          action="http://localhost:8083/file/uploadUser"
          :on-success="handle_success"
        >
          <!-- 再次提醒，:on-success="函数名"，函数名后面不要跟括号 -->
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip"></div>
        </el-upload>
      </el-form-item>
      <div style="display: flex">
        <el-form-item>
          <el-button type="primary" @click="handleSave()" round>确定</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="toHomepage()" type="warning" round>取消</el-button>
        </el-form-item>
      </div>
    </el-form>
  </el-card>
</template>

<script>
import newAxios from "@/utils/axios.js";
export default {
  name: "HotelDetail",
  data() {
    //表单校验规则
    const rules = {
      username: [
        {
          required: true,
          message: "请输入1-20位用户名",
          trigger: "blur,change",
        },
        {
          min: 1,
          max: 20,
          message: "长度在 1到 20个字符",
          trigger: "blur,change",
        },
      ],
      password: [
        {
          required: true,
          message: "请输入4到 20位密码",
          trigger: "change",
        },
        {
          min: 4,
          max: 20,
          message: "长度在 6到 20 个字符",
          trigger: "blur",
        },
      ],
      name: [
        {
          required: true,
          message: "请输入3到 20位名字",
          trigger: "change",
        },
        {
          min: 2,
          max: 20,
          message: "长度在 2到 20 个名字",
          trigger: "blur",
        },
      ],
      icCode: [
        {
          required: true,
          message: "请输入18位身份证",
          trigger: "change",
        },
        {
          min: 18,
          max: 18,
          message: "长度18字符",
          trigger: "blur",
        },
      ],

      linkTel: [
        {
          required: true,
          message: "请输入11位手机号码",
          trigger: "blur,change",
        },
        {
          min: 11,
          max: 11,
          message: "长度11字符",
          trigger: "blur,change",
        },
      ],

      email: [
        {
          required: true,
          message: "请输入qq邮箱",
          trigger: "blur,change",
        },
        {
          min: 15,
          max: 17,
          message: "长度15-17字符",
          trigger: "blur,change",
        },
      ],
    };

    return {
      rules,
      userChangeForm: {},
      homeAdvertise: {},

      dialogOfUpload: false,
      fileList: [],

      selectList: [],
      user: {},
    };
  },
  //组件传参

  //钩子函数
  created() {
    this.findProvince();
    this.handleSave();
  },
  methods: {
    toHomepage() {
      this.$router.back();
    },

    //上传图片
    handle_success(res) {
      return (this.homeAdvertise.icon = res.data);
    },
    findProvince() {
      newAxios
        .get("/admin/findProvinceAll")
        .then((res) => {
          // console.log(res.data);
          this.selectList = res.data.map((item) => {
            console.log(item);
            return { id: item.province, title: item.addr };
          });
        })
        .catch((err) => {
          this.$message("修改数据处理失败");
        });
    },

    //1.保存用户·信息
    handleSave() {
      //1.校验表单是否正确
      this.$refs.userChangeForm.validate((valid) => {
        //2.判断效验是否通过
        if (valid) {
          // 通过校验着请求后端
          newAxios
            .post("/admin/saveOrUpdateUser", {
              ...this.homeAdvertise,
            })
            .then((res) => {
              //  this.handle_success();
              this.$message.success("新增用户成功");
              //返回登录
              this.$router.back();
            })
            .catch((err) => {
              this.$message("数据处理失败");
            });
        } else {
          this.$message({
            message: "请检查数据是否正确",
            type: "warning",
          });
        }
      });
    },
  },
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

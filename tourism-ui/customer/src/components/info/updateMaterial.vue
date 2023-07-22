<template>
  <el-card class="form-container" shadow="never" style="width: 600px;">
    <el-form
      :model="homeAdvertise"
      prop="userChangeForm"
      ref="userChangeForm"
      label-width="100px"
      size="small"
      style="width: 480px;"
    >
      <!-- 分界线-->
      <el-divider>
        <span style=" font-size: 25px; font-weight: bold;">个人信息修改</span>
      </el-divider>

      <el-form-item label="真实名">
        <el-input v-model="homeAdvertise.name" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="手机号码">
        <el-input
          v-model="homeAdvertise.linkTel"
          class="input-width"
        ></el-input>
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
      <div style="display: flex;">
        <el-form-item>
          <span style="color: dodgerblue;" @click="handleSave()" round
            >确定</span
          >
        </el-form-item>
        <el-form-item style="margin-left: 170px;">
          <span
            style="color: dodgerblue;"
            @click="toHomepage()"
            type="warning"
            round
            >取消</span
          >
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
          message: "请输入6到 20位密码",
          trigger: "change",
        },
        {
          min: 6,
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
    // this.handleSave();
    this.findAdminById();
  },
  methods: {
    toHomepage() {
      this.$router.push({ path: "/homepage" });
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

    findAdminById() {
      newAxios
        .get("/admin/findAdminById", {
          params: {
            id: parseInt(this.$route.query.userId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.homeAdvertise = res.data;
          }
        })
        .catch((err) => {
          this.$message = "正在加载ing...有事请留言";
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
            .post("/admin/updateUser", {
              ...this.homeAdvertise,
            })
            .then((res) => {
              console.log(res.data.code);
              this.$message.success("修改成功");
              //返回登录
              // this.$router.replace("/loginUser");
              this.$router.back();
            })
            .catch((err) => {
              this.$message("修改失败，请留言");
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

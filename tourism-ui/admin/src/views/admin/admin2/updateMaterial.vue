<template>
  <el-card class="form-container" shadow="never" style="width: 600px">
    <el-form
      :model="homeAdvertise"
      prop="userChangeForm"
      ref="userChangeForm"
      label-width="150px"
      size="small"
      style="width: 500px"
    >
      <!-- 分界线-->
      <el-divider>
        <span style="font-size: 25px; font-weight: bold">个人信息修改</span>
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
          action="http://localhost:8083/file/upload"
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
import { ApiService } from "@/services";
export default {
  name: "HotelDetail",
  data() {
    return {
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
    this.homeAdvertise.id = parseInt(this.$route.query.id);
    this.findAdminById();
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
      ApiService.get("/admin/findProvinceAll")
        .then((res) => {
          this.selectList = res.data.map((item) => {
            console.log(item);
            return { id: item.province, title: item.addr };
          });
        })
        .catch((err) => {
          this.$message("修改数据处理失败");
        });
    },

    //回显用户信息

    findAdminById() {
      ApiService.get("/admin/findAdmin")
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
      // 通过校验着请求后端
      ApiService.post("/admin/updateAdmin", {
        ...this.homeAdvertise,
      })
        .then((res) => {
          console.log(res.data);
          if (res.data.code == 200) {
            this.$message.success("修改成功");
            this.$router.back();
          }
        })
        .catch((err) => {
          this.$message.error("修改失败");
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

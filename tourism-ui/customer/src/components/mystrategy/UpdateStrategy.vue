<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="上传图片">
        <el-upload
          action="http://localhost:8083/file/uploadUser"
          :on-success="handle_success"
        >
          <!-- 再次提醒，:on-success="函数名"，函数名后面不要跟括号 -->
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件，且不超过500kb
          </div>
        </el-upload>
      </el-form-item>

      <el-form-item label="攻略标题" prop="title">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="推荐星级">
        <div class="block">
          <el-rate v-model="homeAdvertise.rating"></el-rate>
        </div>
      </el-form-item>

      <el-form-item label="攻略内容" prop="summary">
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入攻略内容"
          v-model="homeAdvertise.summary"
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleSave()">确定</el-button>
        <el-button type="warning" @click="message()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  name: "HotelDetail",
  data() {
    //表单校验规则
    const rules = {
      title: [
        { required: true, message: "请输入攻略标题", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 2 到 20个字符",
          trigger: "blur,change",
        },
      ],
      summary: [
        {
          required: true,
          message: "请输入5 到 4000 攻略",
          trigger: "change",
        },
        {
          min: 5,
          max: 4000,
          message: "长度在 5 到 100 个字符",
          trigger: "blur",
        },
      ],

      rating: [
        { required: true, message: "请输入星级", trigger: "blur,change" },
        {
          min: 1,
          max: 6,
          message: "长度在 1 到 6 个字符",
          trigger: "blur,change",
        },
      ],
    };

    return {
      rules,
      homeAdvertise: {},
      dialogOfUpload: false,
      fileList: [],
    };
  },

  //钩子函数
  created() {
    this.findStrategyById();
    this.homeAdvertise.id = parseInt(this.$route.query.strategyId);
  },
  methods: {
    //上传图片
    handle_success(res) {
      console.log(res.data);
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },
    handleAvatarSuccess(res) {
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    message() {
      this.$router.back();
    },

    //1.保存广告信息
    handleSave() {
      //表单校验
      newAxios
        .post("/strategy/saveOrUpdateStrategy", {
          ...this.homeAdvertise,
        })
        .then((res) => {
          //  this.handle_success();
          console.log(this.homeAdvertise);
          //返回上一页面
          this.$router.back();
        })
        .catch((err) => {
          this.$message("数据处理失败");
        });
    },
    //1.保存广告信息
    findStrategyById() {
      //表单校验
      newAxios
        .get("/strategy/findStrategyById", {
          params: {
            id: parseInt(this.$route.query.strategyId),
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.homeAdvertise = res.data.data;
          //返回上一页面
        })
        .catch((err) => {
          this.$message("数据处理失败");
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

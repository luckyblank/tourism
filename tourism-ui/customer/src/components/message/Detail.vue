<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="留言" prop="content">
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入留言内容"
          v-model="homeAdvertise.content"
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
      content: [
        {
          required: true,
          message: "请输入1 到 1000 留言",
          trigger: "change",
        },
        {
          min: 1,
          max: 1000,
          message: "长度在 1 到 1000个字符",
          trigger: "blur",
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
  created() {},
  methods: {
    //1.保存信息
    handleSave() {
      //表单校验
      newAxios
        .post("/message/addMessage", {
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
    //发表攻略
    message() {
      this.$router.push({
        path: "/message",
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

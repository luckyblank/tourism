<template>
  <div>
    <el-upload
      action="http://localhost:8080/ssm-web/course/courseUpload"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="getUrl"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible" size="tiny">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
    };
  },
  props: {
    uploadUrl: {
      type: String,
    },

    getUrl: {
      type: Function,
    },
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    successUpload(res, file) {
      console(file);
      getUrl(file);
      //获取图片信息 传递给父组件
    },
  },
};
</script>
<style scoped></style>

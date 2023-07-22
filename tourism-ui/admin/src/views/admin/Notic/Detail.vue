<template>
  <el-card class="form-container" shadow="never">
    <el-form prop="homeAdvertise" ref="form" label-width="150px" size="small">
      <el-form-item label="公告内容" prop="content">
        <el-input
          type="textarea"
          :rows="2"
          v-model="homeAdvertise.content"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="公告标题" prop="titile">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="状态">
        <el-select v-model="homeAdvertise.state">
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleSave()">确定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { ApiService } from "@/services";
export default {
  name: "OrderDetail",
  data() {
    return {
      homeAdvertise: {},
      typeOptions: [
        {
          value: 0,
          label: "注销",
        },
        {
          value: 1,
          label: "发布",
        },
        {
          value: 2,
          label: "待发布",
        },
      ],
      dialogOfUpload: false,
      fileList: [],
    };
  },
  //组件传参
  props: {
    isEdit: {
      type: Boolean,
      default: false,
    },
  },
  //钩子函数
  created() {
    //判断是添加还是修改
    if (this.isEdit) {
      //修改
      const id = this.$route.query.id;
      this.loadOrder(id);
    } else {
      //新增
    }
  },
  methods: {
    //1.保存订单信息
    handleSave() {
      //表单校验
      ApiService.post("/notic/saveOrUpdateNotic", {
        ...this.homeAdvertise,
      })
        .then((res) => {
          console.log(this.homeAdvertise);
          //返回上一页面
          this.$router.back();
        })
        .catch((err) => {
          this.$message("数据处理失败");
        });
    },
    //2.回显订单信息
    loadOrder(id) {
      return ApiService.get("/notic/getByNoticById", id)
        .then((res) => {
          // Object.assign(this.homeAdvertise, res.data.data);
          this.homeAdvertise = res.data.data;
          // this.homeAdvertise.id = id;
        })
        .catch((err) => {
          this.$message("修改数据处理失败");
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

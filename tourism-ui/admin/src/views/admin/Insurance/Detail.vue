<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="保险标题" prop="title">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="homeAdvertise.addTime"
          type="datetime"
          placeholder="添加时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="价格" prop="price">
        <el-input v-model="homeAdvertise.price" class="input-width"></el-input>
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

      <el-form-item label="保险公司">
        <el-select v-model="homeAdvertise.insuranceCompany">
          <el-option
            v-for="item in InsuranceCompany"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="保险类型">
        <el-select v-model="homeAdvertise.type">
          <el-option
            v-for="item in typeInsurance"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

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
      <el-form-item label="保险" prop="resume">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入保险内容"
          v-model="homeAdvertise.resume"
        >
        </el-input>
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
  name: "InsuranceDetail",
  data() {
    //表单校验规则
    const rules = {
      title: [
        { required: true, message: "请输入保险标题", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 2 到 20个字符",
          trigger: "blur,change",
        },
      ],
      resume: [
        { required: true, message: "请输入保险内容", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 2 到 600个字符",
          trigger: "blur,change",
        },
      ],
      price: [
        { required: true, message: "请输入保险内容", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 2 到 1000个字符",
          trigger: "blur,change",
        },
      ],
    };

    return {
      rules,
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
      InsuranceCompany: [
        {
          value: 0,
          label: "人寿保险",
        },
        {
          value: 1,
          label: "健康平安",
        },
        {
          value: 2,
          label: "天天飘保险",
        },
        {
          value: 3,
          label: "一生无忧",
        },
      ],
      typeInsurance: [
        {
          value: 0,
          label: "全身险",
        },
        {
          value: 1,
          label: "酒店保险",
        },
        {
          value: 2,
          label: "景区保险",
        },
        {
          value: 3,
          label: "出行保险",
        },
        {
          value: 4,
          label: "路线保险",
        },
      ],

      dialogOfUpload: false,
      fileList: [],
      headers: {
        "Content-Type": "multipart/form-data",
      },
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
    this.homeAdvertise.addUserId = JSON.parse(
      sessionStorage.getItem("admin_id")
    );
    //判断是添加还是修改
    if (this.isEdit) {
      //修改
      const id = this.$route.query.id;
      this.loadInsurace(id);
      console.log(id);
    } else {
      //新增
      this.homeAdvertise.addUserId = sessionStorage.getItem("id");
    }
  },
  methods: {
    //上传图片
    handle_success(res) {
      console.log(res.data);
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },

    //1.保存广告信息
    handleSave() {
      //表单校验
      ApiService.post("/insurance/saveOrUpdateInsurance", {
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
    //2.回显广告信息
    loadInsurace(id) {
      return ApiService.get("/insurance/findInsuranceById", id)
        .then((res) => {
          // Object.assign(this.homeAdvertise, res.data.data);
          this.homeAdvertise = res.data.data;
          this.homeAdvertise.id = id;
          this.homeAdvertise.modifyUserId = sessionStorage.getItem("id");
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

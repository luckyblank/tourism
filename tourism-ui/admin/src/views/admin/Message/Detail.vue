<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="留言标题" prop="title">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="homeAdvertise.addTime"
          type="datetime"
          placeholder="添加时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="homeAdvertise.modifyTime"
          type="datetime"
          placeholder="修改时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
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

      <el-form-item label="攻略内容" prop="summary">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入攻略内容"
          v-model="homeAdvertise.summary"
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
    let admin_id = JSON.parse(sessionStorage.getItem("admin_id"));
    this.homeAdvertise.addUserId = JSON.parse(
      sessionStorage.getItem("admin_id")
    );
    console.log(admin_id);
    //判断是添加还是修改
    if (this.isEdit) {
      //alter("修改啊啊啊啊");
      //修改
      const id = this.$route.query.id;
      this.loadSpace(id);
      console.log(id);
    } else {
      //新增
      //alert("新增");
      //  this.homeAdvertise = {};
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
      ApiService.post("/strategy/saveOrUpdateStrategy", {
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
    loadSpace(id) {
      return ApiService.get("/message/findMessageById", id)
        .then((res) => {
          // Object.assign(this.homeAdvertise, res.data.data);
          this.homeAdvertise = res.data.data;
          this.homeAdvertise.id = id;
          this.homeAdvertise.modifyUserId = admin_id;
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

<template>
  <el-card class="form-container" shadow="never">
    <el-form
      :model="homeAdvertise"
      prop="userChangeForm"
      ref="userChangeForm"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="新增时间">
        <el-date-picker
          v-model="homeAdvertise.addTime"
          type="datetime"
          placeholder="添加时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="真实姓名" prop="name">
        <el-input v-model="homeAdvertise.name" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="手机号码" prop="linkTel">
        <el-input
          v-model="homeAdvertise.linkTel"
          class="input-width"
        ></el-input>
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

      <el-form-item label="上传图片">
        <el-upload
          action="http://localhost:8083/file/upload"
          :on-success="handle_success"
        >
          <!-- 再次提醒，:on-success="函数名"，函数名后面不要跟括号 -->
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件，且不超过500kb
          </div>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="success" @click="handleSave()">确定</el-button>
        <el-button type="primary" @click="back()">取消</el-button>
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
      homeAdvertise: {
        linkTel: "18777475545",
      },
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
      userId: "",
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
    //获取当前登录人id
    //判断是添加还是修改
    if (this.isEdit) {
      //修改
      const id = this.$route.query.id;
      this.loadSpace(id);
    } else {
      this.homeAdvertise.addUserId = sessionStorage.getItem("id");
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
      //1.校验表单是否正确

      this.$refs.userChangeForm.validate((valid) => {
        //2.判断效验是否通过
        if (valid) {
          // 通过校验着请求后端
          ApiService.post("/admin/saveOrUpdateAdmin", {
            ...this.homeAdvertise,
          })
            .then((res) => {
              // if (res.state == 200) {

              // }

              console.log(this.res);
              //返回上一页面
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
      //表单校验
    },
    //2.回显广告信息
    loadSpace(id) {
      return ApiService.get("/admin/findAdminById", id)
        .then((res) => {
          this.homeAdvertise = res.data.data;
          this.homeAdvertise.id = id;
          this.homeAdvertise.modifyUserId = sessionStorage.getItem("id");
        })
        .catch((err) => {
          this.$message("修改数据处理失败");
        });
    },
    back() {
      this.$router.back();
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

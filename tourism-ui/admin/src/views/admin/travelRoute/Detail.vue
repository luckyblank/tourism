<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="路线标题" prop="title">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="出发地">
        <el-cascader
          size="small"
          style="width: 130px"
          :options="options"
          v-model="selectedOptions"
          @change="handleChange"
          :show-all-levels="false"
        >
        </el-cascader>
      </el-form-item>

      <el-form-item label="目的地">
        <el-cascader
          size="small"
          style="width: 130px"
          :options="options"
          v-model="selectedOptions2"
          @change="handleChange2"
          :show-all-levels="false"
        >
        </el-cascader>
      </el-form-item>

      <el-form-item label="开始时间">
        <el-date-picker
          v-model="homeAdvertise.startTime"
          type="datetime"
          placeholder="结束时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="结束时间">
        <el-date-picker
          v-model="homeAdvertise.endTime"
          type="datetime"
          placeholder="结束时间"
          value-format="yyyy-MM-dd HH:mm:ss "
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="天数" prop="day">
        <el-input v-model="homeAdvertise.day" class="input-width"></el-input>
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

      <el-form-item label="介绍">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入路线内容"
          v-model="homeAdvertise.intro"
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
import {
  provinceAndCityData,
  regionData,
  provinceAndCityDataPlus,
  regionDataPlus,
  CodeToText,
  TextToCode,
} from "element-china-area-data";
export default {
  name: "TravelRouteDetail",
  data() {
    //表单校验规则
    const rules = {
      title: [
        { required: true, message: "请输入路线标题", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 2 到 20个字符",
          trigger: "blur,change",
        },
      ],
      startSite: [
        {
          required: true,
          message: "请输入1到 25开始地",
          trigger: "change",
        },
        {
          min: 1,
          max: 25,
          message: "长度在 5 到 100 个字符",
          trigger: "blur",
        },
      ],
      endSite: [
        {
          required: true,
          message: "请输入1到 25目的地",
          trigger: "change",
        },
        {
          min: 1,
          max: 25,
          message: "长度在 5 到 100 个字符",
          trigger: "blur",
        },
      ],

      day: [
        { required: true, message: "请输入天数", trigger: "blur,change" },
        {
          min: 1,
          max: 3,
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
      options: provinceAndCityDataPlus,
      selectedOptions: [],
      startSite: "",
      options2: provinceAndCityDataPlus,
      selectedOptions2: [],
      endSite: "",
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
      this.loadRoute(id);
    } else {
      //新增
    }
  },
  methods: {
    //上传图片
    handle_success(res) {
      console.log(res.data);
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },

    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.homeAdvertise.startSite = loc;
    },
    handleChange2() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions2.length; i++) {
        loc += CodeToText[this.selectedOptions2[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.homeAdvertise.endSite = loc;
    },

    //1.保存广告信息
    handleSave() {
      //表单校验
      ApiService.post("/travelRoute/saveOrUpdateRoutel", {
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
    loadRoute(id) {
      return ApiService.get("/travelRoute/findById", id)
        .then((res) => {
          // Object.assign(this.homeAdvertise, res.data.data);
          this.homeAdvertise = res.data.data;
          this.homeAdvertise.id = id;
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

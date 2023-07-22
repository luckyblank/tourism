<template>
  <el-card class="form-container" shadow="never">
    <el-form prop="homeAdvertise" ref="form" label-width="150px" size="small">
      <el-form-item label="景点名">
        <el-input
          v-model="homeAdvertise.spotName"
          class="input-width"
        ></el-input>
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
      <el-form-item label="开放时间">
        <el-input
          v-model="homeAdvertise.openTime"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="票价">
        <el-input
          v-model="homeAdvertise.ticketsMessage"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="景区星级">
        <div class="block">
          <el-rate v-model="homeAdvertise.spotStar"></el-rate>
        </div>
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

      <el-form-item label="景点地址">
        <el-cascader
          size="small"
          style="width: 180px"
          :options="options"
          v-model="selectedOptions"
          @change="handleChange"
        >
        </el-cascader>
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
      <el-form-item label="请输入景点描述">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入景点描述"
          v-model="homeAdvertise.spotIntro"
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
  name: "ScenicSpotDetail",
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
      options: regionDataPlus,
      selectedOptions: [],
      spotAddress: "",
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
      this.loadSencicSpot(id);
      console.log(id);
    } else {
      //新增
      this.homeAdvertise.addUserId = sessionStorage.getItem("id");
      //  this.homeAdvertise = {};
    }
  },
  methods: {
    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.homeAdvertise.spotAddress = loc;
    },

    //上传图片
    handle_success(res) {
      console.log(res.data);
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },

    //1.保存景点信息
    handleSave() {
      //表单校验
      ApiService.post("/scenic/saveOrUpdateScenicSpot", {
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
    loadSencicSpot(id) {
      return ApiService.get("/scenic/findById", id)
        .then((res) => {
          console.log(res.data);
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

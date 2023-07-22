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
      <el-form-item label="标题" prop="title">
        <el-input v-model="homeAdvertise.title" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="集中地" prop="gatherPlace">
        <el-input
          v-model="homeAdvertise.gatherPlace"
          class="input-width"
        ></el-input>
      </el-form-item>
      <!-- <el-form-item label="出发地" prop="startPlace">
        <el-input
          v-model="homeAdvertise.startPlace"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="目的地" prop="endPlace">
        <el-input
          v-model="homeAdvertise.endPlace"
          class="input-width"
        ></el-input>
      </el-form-item> -->

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

      <el-form-item label="经停站" prop="intermediateStop">
        <el-input
          v-model="homeAdvertise.intermediateStop"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="换乘站" prop="interchangeStation">
        <el-input
          v-model="homeAdvertise.interchangeStation"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="出发日期">
        <el-date-picker
          v-model="homeAdvertise.startDate"
          type="datetime"
          placeholder="出发日期"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="开始时间" prop="timeRange">
        <el-input
          v-model="homeAdvertise.timeRange"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="结束时间" prop="timeRange">
        <el-input
          v-model="homeAdvertise.timeEnd"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="车次" prop="trainNumber">
        <el-input
          v-model="homeAdvertise.trainNumber"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="换乘时间" prop="intermediateTime">
        <el-input
          v-model="homeAdvertise.intermediateTime"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="预计时间" prop="needTime">
        <el-input
          v-model="homeAdvertise.needTime"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="价格" prop="needTime">
        <el-input v-model="homeAdvertise.price" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="类型">
        <el-select v-model="homeAdvertise.type">
          <el-option
            v-for="item in optionsCar"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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

      <el-form-item label="内容">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="homeAdvertise.remark"
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
  name: "carDetail",
  data() {
    //表单校验规则
    const rules = {
      title: [
        {
          required: true,
          message: "请输入位标题",
          trigger: "blur,change",
        },
        {
          min: 1,
          max: 10,
          message: "长度在 1到 20个字符",
          trigger: "blur,change",
        },
      ],
      startPlace: [
        {
          required: true,
          message: "请输入出发地",
          trigger: "change",
        },
        {
          min: 2,
          max: 10,
          message: "长度在 2到 10个字符",
          trigger: "blur",
        },
      ],

      intermediateStop: [
        {
          required: true,
          message: "请输入经停站",
          trigger: "change",
        },
        {
          min: 2,
          max: 60,
          message: "长度在 2到 60个字符",
          trigger: "blur",
        },
      ],

      interchangeStation: [
        {
          required: true,
          message: "请输入换乘站",
          trigger: "change",
        },
        {
          min: 2,
          max: 10,
          message: "长度在 2到 10个字符",
          trigger: "blur",
        },
      ],
      intermediateTime: [
        {
          required: true,
          message: "请输入换乘时间",
          trigger: "change",
        },
        {
          min: 1,
          max: 10,
          message: "长度在 1到 10个字符",
          trigger: "blur",
        },
      ],

      gatherPlace: [
        {
          required: true,
          message: "请输入集中地",
          trigger: "change",
        },
        {
          min: 2,
          max: 10,
          message: "长度在 2到 10个字符",
          trigger: "blur",
        },
      ],
      endPlace: [
        {
          required: true,
          message: "请输入目的地",
          trigger: "change",
        },
        {
          min: 2,
          max: 10,
          message: "长度在 2到 10个字符",
          trigger: "blur",
        },
      ],
      timeRange: [
        {
          required: true,
          message: "请输入开点时间段",
          trigger: "change",
        },
        {
          min: 1,
          max: 20,
          message: "长度在 1到 20 个",
          trigger: "blur",
        },
      ],

      needTime: [
        {
          required: true,
          message: "请输入开点时间段",
          trigger: "change",
        },
        {
          min: 1,
          max: 10,
          message: "长度在 1到 10",
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
      homeAdvertise: {
        linkTel: "18777475545",
      },
      userChangeForm: {},
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

      optionsCar: [
        {
          value: 1,
          label: "高铁",
        },
        {
          value: 0,
          label: "火车",
        },
      ],
      options: provinceAndCityDataPlus,
      selectedOptions: [],
      startPlace: "",
      options2: provinceAndCityDataPlus,
      selectedOptions2: [],
      endPlace: "",
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
      const id = this.$route.query.id;
      this.loadCar(id);
    } else {
      //新增
      this.homeAdvertise.addUserId = sessionStorage.getItem("id");
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
    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.homeAdvertise.startPlace = loc;
    },
    handleChange2() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions2.length; i++) {
        loc += CodeToText[this.selectedOptions2[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.homeAdvertise.endPlace = loc;
    },

    //1.保存广告信息
    handleSave() {
      //1.校验表单是否正确

      this.$refs.userChangeForm.validate((valid) => {
        //2.判断效验是否通过
        if (valid) {
          //表单校验
          ApiService.post("/car/saveOrUpdateCar", {
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
        } else {
          this.$message({
            message: "请检查数据是否正确",
            type: "warning",
          });
        }
      });
    },

    //2.回显广告信息
    loadCar(id) {
      return ApiService.get("/car/findCarById", id)
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

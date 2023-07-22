<template>
  <el-card class="form-container" shadow="never">
    <el-form
      prop="homeAdvertise"
      ref="form"
      label-width="150px"
      size="small"
      :rules="rules"
    >
      <el-form-item label="酒店名" prop="hotelName">
        <el-input
          v-model="homeAdvertise.hotelName"
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
      <el-form-item label="价格" prop="price">
        <el-input v-model="homeAdvertise.price" class="input-width"></el-input>
      </el-form-item>

      <el-form-item label="酒店星级">
        <div class="block">
          <el-rate v-model="homeAdvertise.hotelStar"></el-rate>
        </div>
      </el-form-item>

      <el-form-item label="酒店号码" prop="linkPhone">
        <el-input
          v-model="homeAdvertise.linkPhone"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="地址">
        <el-cascader
          size="small"
          :options="options"
          v-model="selectedOptions"
          @change="handleChange"
        >
        </el-cascader>
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
      <el-form-item label="类型">
        <el-select v-model="homeAdvertise.type">
          <el-option
            v-for="item in typeHotel"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="附近景区">
        <el-select v-model="homeAdvertise.scenicSpotId">
          <el-option
            v-for="item in selectList"
            :key="item.id"
            :label="item.title"
            :value="item.id"
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

      <el-form-item label="酒店描述" prop="hotelIntro">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入酒店描述"
          v-model="homeAdvertise.hotelIntro"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="房型信息" prop="roomTypeInformation">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入房型信息描述"
          v-model="homeAdvertise.roomTypeInformation"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="设施信息" prop="facilityInformation">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入设施信息描述"
          v-model="homeAdvertise.facilityInformation"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="订房须知" prop="bookingInstructions">
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入订房须知"
          v-model="homeAdvertise.bookingInstructions"
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
  name: "HotelDetail",
  data() {
    //表单校验规则
    const rules = {
      hotelName: [
        { required: true, message: "请输入酒店", trigger: "blur,change" },
        {
          min: 2,
          max: 20,
          message: "长度在 1 到 6 个字符",
          trigger: "blur,change",
        },
      ],
      hotelIntro: [
        {
          required: true,
          message: "请输入5 到 1000 酒店简介",
          trigger: "change",
        },
        {
          min: 5,
          max: 1000,
          message: "长度在 5 到 1000 个字符",
          trigger: "blur",
        },
      ],

      linkPhone: [
        {
          required: true,
          message: "请输入11位手机号码",
          trigger: "change",
        },
        { min: 11, max: 11, message: "长度11字符", trigger: "blur" },
      ],
      price: [
        { required: true, message: "请输入价格", trigger: "blur,change" },
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
      typeHotel: [
        {
          value: 0,
          label: "民宿",
        },
        {
          value: 1,
          label: "酒店",
        },
        {
          value: 2,
          label: "公寓",
        },
      ],
      dialogOfUpload: false,
      fileList: [],
      selectList: [],
      options: regionDataPlus,
      selectedOptions: [],
      address: "",
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
    this.findSpot();
    //判断是添加还是修改
    if (this.isEdit) {
      //修改
      const id = this.$route.query.id;
      this.loadHotel(id);
    } else {
      this.homeAdvertise.addUserId = sessionStorage.getItem("id");
      //新增
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
      this.homeAdvertise.address = loc;
    },

    //上传图片
    handle_success(res) {
      console.log(res.data);
      this.$message.success("图片上传成功");
      return (this.homeAdvertise.imgUrl = res.data);
    },

    //1.保存酒店信息
    handleSave() {
      //表单校验

      ApiService.post("/hotel/saveOrUpdateHotel", {
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
    //2.回显酒店信息
    loadHotel(id) {
      return ApiService.get("/hotel/findHotelById", id)
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

    //查找景区列表
    findSpot() {
      ApiService.get("/scenic/getSpotHotel")
        .then((res) => {
          this.selectList = res.data.map((item) => {
            console.log(item);
            return { id: item.spotId, title: item.spotName };
          });
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

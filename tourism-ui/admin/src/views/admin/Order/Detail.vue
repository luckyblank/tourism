<template>
  <el-card class="form-container" shadow="never">
    <el-form prop="homeAdvertise" ref="form" label-width="150px" size="small">
      <el-form-item label="订单人">
        <el-input
          v-model="homeAdvertise.userName"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="订单名" prop="productName">
        <el-input
          v-model="homeAdvertise.productName"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="费用" prop="fee">
        <el-input v-model="homeAdvertise.fee" class="input-width"></el-input>
      </el-form-item>

      <!-- <el-form-item label="price" prop="name">
        <el-input v-model="homeAdvertise.name" class="input-width"></el-input>
      </el-form-item> -->

      <el-form-item label="联系方式" prop="linkTel">
        <el-input
          v-model="homeAdvertise.linkTel"
          class="input-width"
        ></el-input>
      </el-form-item>
      <el-form-item label="人数" prop="peopleCount">
        <el-input
          v-model="homeAdvertise.peopleCount"
          class="input-width"
        ></el-input>
      </el-form-item>

      <el-form-item label="类型">
        <el-select v-model="homeAdvertise.productType">
          <el-option
            v-for="item in options"
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
          label: "未支付",
        },
        {
          value: 1,
          label: "已支付",
        },
        {
          value: 2,
          label: "已取消",
        },
        {
          value: 3,
          label: "退款中",
        },
        {
          value: 4,
          label: "已退款",
        },
      ],
      dialogOfUpload: false,
      fileList: [],
      headers: {
        "Content-Type": "multipart/form-data",
      },
      options: [
        {
          value: 0,
          label: "车票",
        },
        {
          value: 1,
          label: "酒店",
        },
        {
          value: 2,
          label: "路线",
        },
        {
          value: 3,
          label: "景点",
        },
        {
          value: 4,
          label: "保险",
        },
      ],
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
      //alter("修改啊啊啊啊");
      //修改
      const id = this.$route.query.id;
      this.loadOrder(id);
    } else {
      //新增
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

    //1.保存订单信息
    handleSave() {
      //表单校验
      ApiService.post("/order/updateOrder", {
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
    //2.回显订单信息
    loadOrder(id) {
      return ApiService.get("/order/findOrderById", id)
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

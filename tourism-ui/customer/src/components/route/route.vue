<template>
  <div>
    <!-- 路线顶部轮播图 -->
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in imgs" :key="item.src">
        <el-image style="width: 100%; height: 100%; " :src="item" :fit="cover">
        </el-image>
      </el-carousel-item>
    </el-carousel>

    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">路线预订</span>
    </el-divider>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" size="small" label="低于">
        <el-input
          v-model="filter.price"
          type="search"
          size="small"
          placeholder="低于价格"
        />
      </el-form-item>

      <el-form-item label="出发地">
        <el-cascader
          size="small"
          style="width: 130px;"
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
          style="width: 130px;"
          :options="options"
          v-model="selectedOptions2"
          @change="handleChange2"
          :show-all-levels="false"
        >
        </el-cascader>
      </el-form-item>

      <el-form-item>
        <el-button
          @click="filterquery"
          size="small"
          type="primary"
          icon="el-icon-search"
          >查询</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button
          @click="ReForm"
          type="info"
          size="small"
          class="el-icon-delete"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 路线介绍表格-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column header-align="center" align="center" label="图片">
        <template slot-scope="scope">
          <el-image
            :src="require('@/assets/images/' + scope.row.imgUrl)"
            style="width: 100px; height: 100px"
          ></el-image>
        </template>
      </el-table-column>

      <el-table-column label="景区信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">{{
            scope.row.title
          }}</span
          ><br />
          <span style="font-size: 20px; font-weight: bold; color: burlywood;">
            时间：{{ scope.row.startTime }}<span>到</span
            >{{ scope.row.endTime }}</span
          ><br />
          <span>开始地：{{ scope.row.startSite }}</span
          ><br />
          <span>结束地：{{ scope.row.endSite }}</span
          ><br />
          <span>天数：{{ scope.row.day }}</span
          ><br />
          <span>简介：{{ scope.row.intro }}</span>
        </template>
      </el-table-column>

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 写span标签接收价格数据并拼接信息-->
          <span
            style="display: block;
          font-weight: bold; color: red;
          font-size: 17px;margin-bottom: 30px;"
            >￥{{ scope.row.price }}起</span
          >
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="warning"
            @click="routeDetail(scope.row.id)"
            >查看详情</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>
<script>
import newAxios from "../../utils/axios";
import {
  provinceAndCityData,
  regionData,
  provinceAndCityDataPlus,
  regionDataPlus,
  CodeToText,
  TextToCode,
} from "element-china-area-data";
export default {
  name: "Route",

  data() {
    return {
      imgs: [
        require("../../assets/images/route/1.png"),
        require("../../assets/images/route/2.png"),
        require("../../assets/images/route/3.png"),
        require("../../assets/images/route/4.jpg"),
      ],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      options: provinceAndCityDataPlus,
      selectedOptions: [],
      startSite: "",
      options2: provinceAndCityDataPlus,
      selectedOptions2: [],
      endSite: "",
    };
  },
  created() {
    //获取路线列表数据
    this.loadSpot();
  },
  methods: {
    //路线详情页跳转的方法
    routeDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/routeDetail",
        query: {
          routeId: id,
        },
      });
    },

    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.startSite = loc;
    },
    handleChange2() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions2.length; i++) {
        loc += CodeToText[this.selectedOptions2[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.endSite = loc;
    },

    //获取路线的数据列表以及分页信息
    loadSpot() {
      newAxios
        .post("/travelRoute/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          endSite: this.endSite,
          startSite: this.startSite,
          price: this.filter.price,
          state: 1,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
            this.total = res.total;
          } else {
            alert("获取数据失败");
          }
        });
    },
    //条件分页查询
    filterquery() {
      this.loadSpot();
    },
    //重置查询
    ReForm() {
      this.filter.price = null;
      this.startSite = "";
      this.endSite = "";
      this.selectedOptions = "";
      this.selectedOptions2 = "";
      this.loadSpot();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadSpot();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadSpot();
    },
  },
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>

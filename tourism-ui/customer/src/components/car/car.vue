<template>
  <div>
    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">车票预订</span>
    </el-divider>
    <el-radio v-model="radio" label="1" style="margin-right: 720px;"
      >单程</el-radio
    >

    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
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

      <el-form-item> </el-form-item>

      <el-form-item label="动车类型">
        <el-select
          v-model="filter.type"
          size="small"
          style="width: 110px;"
          @change="filterquery"
        >
          <el-option
            v-for="item in typeCar"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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
          size="small"
          type="info"
          class="el-icon-delete"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 酒店介绍表格-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="动车信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">{{
            scope.row.timeRange
          }}</span>
          <span
            style="font-size: 6px; font-weight: bold; color:grey; margin-left: 50px;"
            >{{ scope.row.needTime }}</span
          >
          <span
            style="font-size: 23px; font-weight: bold; color:black;margin-left: 70px;"
            >{{ scope.row.timeEnd }}</span
          >
          <p></p>
          <span style="font-size: 17px; font-weight: bold; color:black;">{{
            scope.row.startPlace
          }}</span>
          <span
            style="font-size: 6px; font-weight: bold; color:grey; margin-left: 50px;"
            >{{ scope.row.trainNumber }}</span
          >
          <i class="el-icon-date"></i>
          <span
            style="font-size: 17px; font-weight: bold; color:black;margin-left: 64px;"
            >{{ scope.row.endPlace }}</span
          >
        </template>
      </el-table-column>

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 写span标签接收价格数据并拼接信息-->

          <span
            style="
           font-weight: bold; color: red;
          font-size: 20px; margin-right: 4px; "
            >￥{{ scope.row.price }}</span
          >
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="warning"
            @click="carDetail(scope.row.id)"
            >订票</el-button
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
  name: "hotel",

  data() {
    return {
      imgs: [
        require("../../assets/images/sceintSpot/1.jpg"),
        require("../../assets/images/sceintSpot/2.jpg"),
        require("../../assets/images/sceintSpot/3.jpg"),
        require("../../assets/images/sceintSpot/6.jpg"),
      ],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      radio: "1",
      startCit: "",
      endCity: "",
      startTime: "",
      typeCar: [
        {
          value: 0,
          label: "火车",
        },
        {
          value: 1,
          label: "高铁",
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
  created() {
    //获取酒店列表数据
    this.loadCar();
  },
  methods: {
    //酒店详情页跳转的方法
    carDetail(id) {
      this.$router.push({
        path: "/carDetail",
        query: {
          car_id: id,
        },
      });
    },

    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.startPlace = loc;
      this.loadCar();
    },
    handleChange2() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions2.length; i++) {
        loc += CodeToText[this.selectedOptions2[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.endPlace = loc;
      this.loadCar();
    },

    //获取酒店的数据列表以及分页信息
    loadCar() {
      console.log("获取酒店的数据列表以及分页信息"); //这个怎么都没输出啊
      newAxios
        .post("/car/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          // startPlace: this.filter.startPlace,
          startPlace: this.startPlace,
          endPlace: this.endPlace,
          //endPlace: this.filter.endPlace,
          type: this.filter.type,
          state: 1,
        })
        .then((res) => res.data)
        .then((res) => {
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
      this.loadCar();
    },
    //重置查询
    ReForm() {
      this.filter.type = null;
      this.endPlace = "";
      this.startPlace = "";
      this.selectedOptions = "";

      this.selectedOptions2 = "";

      this.loadCar();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadCar();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadCar();
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
p.groove {
  border-style: hidden;
  height: 60px;
  background-color: rgb(242, 238, 238);
  width: 380px;
  border-radius: 5px;
}
p.right {
  border-style: hidden;
  height: 60px;
  background-color: rgb(242, 238, 238);
  width: 380px;
  margin-left: 6px;
  border-radius: 5px;
}
.car {
  display: flex;
}
</style>

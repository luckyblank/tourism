<template>
  <div>
    <!-- 酒店顶部轮播图 -->
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in imgs" :key="item.src">
        <el-image style="width: 100%; height: 100%; " :src="item" fit="cover">
        </el-image>
      </el-carousel-item>
    </el-carousel>

    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">酒店预订</span>
    </el-divider>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item label="酒店区域">
        <el-cascader
          size="small"
          :options="options"
          v-model="selectedOptions"
          @change="handleChange"
        >
        </el-cascader>
      </el-form-item>
      <el-form-item class="input-title" label="酒店名">
        <el-input
          v-model="filter.hotelName"
          type="search"
          placeholder="请输入酒店名"
          size="small"
          style="width: 160px;"
          @change="filterquery"
        />
      </el-form-item>

      <el-form-item class="input-title" label="低于">
        <el-input
          v-model="filter.price"
          type="search"
          placeholder="低于价格"
          style="width: 100px;"
          size="small"
          @change="filterquery"
        />
      </el-form-item>

      <el-form-item label="住宿类型">
        <el-select
          v-model="filter.type"
          style="width: 100px;"
          size="small"
          @change="filterquery"
        >
          <el-option
            v-for="item in typeHotel"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="酒店星级">
        <el-select
          v-model="filter.hotelStar"
          style="width: 100px;"
          size="small"
          @change="filterquery"
        >
          <el-option
            v-for="item in typeStar"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          @click="filterquery"
          icon="el-icon-search"
          size="small"
          >搜索</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm" size="small" type="info">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 酒店介绍表格-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column header-align="center" align="center" label="图片">
        <template slot-scope="scope">
          <el-image
            :src="require('@/assets/images/' + scope.row.imgUrl)"
            style="width: 100px; height: 100px"
          ></el-image>
        </template>
      </el-table-column>

      <el-table-column label="酒店信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">{{
            scope.row.hotelName
          }}</span
          ><br />
          <span style="font-size: 20px; font-weight: bold; color: orange;"
            >{{ scope.row.hotelStar }}星级</span
          ><br />
          <span>地址：{{ scope.row.address }}</span>
          <div class="wrap">
            <span> 简介：</span>
            <span class="content text-ellipsis">
              {{ scope.row.hotelIntro }}
            </span>
          </div>
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
            @click="hotel_detail(scope.row)"
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
//二选一
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
        require("../../assets/images/hotel/1.jpg"),
        require("../../assets/images/hotel/2.jpg"),
        require("../../assets/images/hotel/3.jpg"),
      ],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
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

      typeStar: [
        {
          value: 1,
          label: "一星",
        },
        {
          value: 2,
          label: "二星",
        },
        {
          value: 3,
          label: "三星",
        },
        {
          value: 4,
          label: "四星",
        },
        {
          value: 5,
          label: "五星",
        },
      ],
      options: regionDataPlus,
      selectedOptions: [],
      address: "",
    };
  },
  created() {
    //获取酒店列表数据
    this.loadHotel();
  },
  methods: {
    //酒店详情页跳转的方法
    hotel_detail(row) {
      this.$router.push({
        path: "/hotel_detail",
        query: {
          scenicSpotId: parseInt(row.scenicSpotId),
          hotelid: row.id,
        },
      });
    },
    handleChange() {
      var loc = "";
      for (let i = 1; i < this.selectedOptions.length; i++) {
        loc += CodeToText[this.selectedOptions[i]];
      }
      console.log(loc); //打印区域码所对应的属性值即中文地址
      this.address = loc;
      this.loadHotel();
    },

    //获取酒店的数据列表以及分页信息
    loadHotel() {
      newAxios
        .post("/hotel/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          hotelName: this.filter.hotelName,
          hotelStar: this.filter.hotelStar,
          price: this.filter.price,
          scenicSpotId: this.filter.scenicSpotId,
          type: this.filter.type,
          state: 1,
          address: this.address,
        })
        .then((res) => {
          console.log(res.data.data);
          if (res.data.code == 200) {
            this.tableData = res.data.data;

            this.total = res.total;
          } else {
            this.$message = "没有该酒店";
          }
        });
    },

    //条件分页查询
    filterquery() {
      this.loadHotel();
    },
    //重置查询
    ReForm() {
      this.filter.hotelName = "";
      this.filter.hotelStar = null;
      this.filter.type = null;
      this.filter.price = null;
      this.filter.scenicSpotId = null;
      this.address = "";
      this.selectedOptions = "";

      this.loadHotel();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadHotel();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadHotel();
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
.wrap {
  width: 500px;
  height: 100px;
}
.content {
  width: 100%;
}
.text-ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  text-indent: 2em;
}
</style>

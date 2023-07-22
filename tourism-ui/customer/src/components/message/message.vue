<template>
  <div>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <div style="display: flex;">
        <el-badge :value="count" class="item">
          <el-button size="small">已回复</el-button>
        </el-badge>
        <span
          style="font-size: 18px; color:rgb(88, 141, 206); margin: 7px;"
          @click="publish"
          >去留言</span
        >

        <el-form-item label="回复状态">
          <el-select v-model="filter.state" size="mini" style="width: 80px;">
            <el-option
              v-for="item in typeState"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="filterquery" size="mini" type="primary"
            >搜索</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button @click="ReForm" size="mini">重置</el-button>
        </el-form-item>
      </div>
    </el-form>

    <!-- 订单介绍表格-->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column label="我的留言" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
            {{ scope.row.userName }}</span
          ><br />
          <span>{{ scope.row.addTime }}</span>
          <br />
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="primary"
            @click="orderDetail(scope.row)"
            style="margin-right : 20xp;"
            >详情</el-button
          >
          <span
            v-if="scope.row.state == 0"
            style="color: red;margin-left: 16xp;"
            >未回复</span
          >
          <span v-else style="color: green;margin-left: 16xp;">已回复</span>
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
export default {
  name: "hotel",
  data() {
    return {
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      loading: false,
      count: 0,
      id: 0,

      typeState: [
        {
          value: 0,
          label: "未回复",
        },
        {
          value: 1,
          label: "已回复",
        },
      ],
    };
  },
  created() {
    //获取留言列表数据
    this.loadMessage();
    this.stateReply();
  },
  methods: {
    //留言详情页跳转的方法
    orderDetail(row) {
      this.$router.push({
        path: "/messageDetail",
        query: {
          messageId: row.id,
          content: row.content,
          addTime: row.addTime,
        },
      });
    },

    //发表攻略
    publish() {
      this.$router.push({
        path: "/Detail2",
      });
    },

    //获取留言的数据列表以及分页信息
    loadMessage() {
      newAxios
        .post("/message/listMessage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          state: this.filter.state,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
            this.id = res.data.id;
            this.total = res.total;
            this.content = res.content;
            this.addTime = res.addTime;
          } else {
            this.$message({
              message: "没有待回复信息",
              type: "warning",
            });
          }
        });
    },

    //获取留言的数据列表以及分页信息
    stateReply() {
      newAxios
        .get("/message/stateReply")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            console.log(res.data);

            this.count = res.data;
          } else {
            this.$message.error("请先登录噢亲！！");
          }
        });
    },

    //条件分页查询
    filterquery() {
      this.loadMessage();
    },
    //重置查询
    ReForm() {
      this.filter.state = null;
      this.loadMessage();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadMessage();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadMessage();
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

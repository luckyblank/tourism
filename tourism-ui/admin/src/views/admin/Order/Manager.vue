<template>
  <div class="app-container">
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="用户名">
        <el-input
          v-model="filter.userName"
          type="search"
          placeholder="用户名"
        />
      </el-form-item>

      <el-form-item label="订单类型">
        <el-select v-model="filter.productType">
          <el-option
            v-for="item in productOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>
    </el-form>

    <div class="table-container">
      <el-table
        ref="homeAdvertiseTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        borderloading
      >
        <el-table-column label="编号" align="center" width="80">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>

        <el-table-column label="下单用户" align="center" width="90">
          <template slot-scope="scope">{{ scope.row.userName }}</template>
        </el-table-column>

        <el-table-column label="订单名" align="center" width="190">
          <template slot-scope="scope">{{ scope.row.productName }}</template>
        </el-table-column>

        <el-table-column label="费用" align="center" width="90">
          <template slot-scope="scope">{{ scope.row.fee }}</template>
        </el-table-column>

        <el-table-column label="订单类型" align="center" width="110">
          <template slot-scope="scope">
            <span v-if="scope.row.productType == 0">车票</span>
            <span v-else-if="scope.row.productType == 1">酒店</span>
            <span v-else-if="scope.row.productType == 2">路线</span>
            <span v-else-if="scope.row.productType == 3">景点</span>
            <span v-else>保险</span>
          </template>
        </el-table-column>

        <el-table-column label="支付" align="center" width="90">
          <template slot-scope="scope">
            <span v-if="scope.row.state == 0">未支付</span>
            <span style="color: green" v-else-if="scope.row.state == 1"
              >已支付</span
            >
            <span style="color: red" v-else-if="scope.row.state == 2"
              >已取消</span
            >
            <span style="color: chocolate" v-else-if="scope.row.state == 3"
              >退款中</span
            >
            <span style="color: royalblue" v-else>已退款</span>
          </template>
        </el-table-column>

        <el-table-column
          label="特殊要求"
          align="center"
          width="160"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">{{ scope.row.requirement }}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="230" :data="list1">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            >
            <el-button
              size="mini"
              type="primary"
              @click="handleUpdate(scope.row)"
              >编辑</el-button
            >
            <el-button size="mini" type="primary" @click="handle(scope.row)"
              >改状态</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <el-input disabled v-model="list1.id" style="display: none"></el-input>
        <el-select v-model="list1.state">
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpdateStatus()"
            >确 定</el-button
          >
        </span>
      </el-dialog>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[8, 16, 24, 32]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { ApiService } from "@/services";
export default {
  name: "OrderManager",
  data() {
    return {
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
      productOptions: [
        {
          value: 0,
          label: "车票",
        },
        {
          value: 1,
          label: "景点",
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
      filter: { endPlace: "", endPlace: "" },
      list: [],
      list1: { state: 1 },
      listLoading: false,
      tableData: [],
      pageSize: 8,
      pageNum: 1,
      total: 0,

      typeMap: {}, //保存广告位对象信息
      typeId: [],
      dialogVisible: false,
    };
  },
  created() {
    //获取订单列表数据
    this.loadOrder();
  },
  methods: {
    handle(row) {
      this.dialogVisible = true;
      this.list1.id = row.id;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((res) => {
          done();
        })
        .catch((err) => {});
    },
    loadOrder() {
      ApiService.post("/order/listPage", {
        currentPage: this.pageNum,
        pageSize: this.pageSize,

        userName: this.filter.userName,
        productType: this.filter.productType,
      })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.total);
          if (res.code == 200) {
            this.list = res.data;
            this.total = res.total;
            this.list1 = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },

    //2.条件分页查询
    filterquery() {
      this.loadOrder();
    },
    //3.重置
    ReForm() {
      this.filter.userName = "";
      this.filter.productType = null;
      this.loadOrder();
    },

    //4.修改状态
    handleUpdateStatus() {
      ApiService.post("/order/UpdateStates1", {
        id: this.list1.id,
        state: this.list1.state,
      })
        .then((res) => {
          this.loadOrder();
          this.dialogVisible = false;
        })
        .catch((err) => {
          this.$message = "修改状态失败";
        });
    },
    //5.修改
    handleUpdate(row) {
      alert("修改");
      this.$router.push({
        //path: "/layout/updateOrder",
        name: "UpdateOrder",
        query: { id: row.id },
      });
    },

    //7.删除
    delete01(row) {
      this.$confirm("你确定要修改订单吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        ApiService.get("/order/deleteById", {
          params: {
            id: row.id,
          },
        })
          .then((res) => {
            this.loadOrder();
            console.log(res);
          })
          .catch((err) => {
            this.$message = "删除失败";
          });
      });
    },

    dateFormat: function (row, column) {
      var date = row[column.property];

      if (date == undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD ");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadOrder();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadOrder();
    },
  },
};
</script>

<style></style>

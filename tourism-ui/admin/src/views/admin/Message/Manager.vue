<template>
  <div class="app-container">
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="用户名">
        <el-input
          v-model="filter.userName"
          type="search"
          placeholder="请输入用户名"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>

      <!-- <el-form-item class="btn-add">
        <el-button type="primary" icon="el-icon-plus" @click="messageAdd"
          >新增留言</el-button
        >
      </el-form-item> -->
    </el-form>

    <div class="table-container">
      <el-table
        ref="homeAdvertiseTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
      >
        <el-table-column label="编号" align="center" width="80">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>

        <el-table-column label="用户名" align="center" width="100">
          <template slot-scope="scope">{{ scope.row.userName }}</template>
        </el-table-column>

        <el-table-column label="真实姓名" align="center" width="100">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>

        <el-table-column
          label="留言内容"
          align="center"
          width="335"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">{{ scope.row.content }}</template>
        </el-table-column>

        <el-table-column
          label="留言时间"
          align="center"
          prop="addTime"
          :formatter="dateFormat"
          width="120"
        >
        </el-table-column>

        <el-table-column label="状态" align="center" width="90">
          <template slot-scope="scope">
            <span v-if="scope.row.state == 0" style="color: red">待回复</span>
            <span v-else-if="scope.row.state == 1" style="color: green"
              >已回复</span
            >
            <span v-else-if="scope.row.state == 2">回复内容</span>
            <span v-else>待发布</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" :data="list1">
          <template slot-scope="scope">
            <el-button
              style="width: 90px"
              size="mini"
              type="primary "
              v-if="scope.row.addUserId != null"
              @click="delete01(scope.row)"
              >撤回回复</el-button
            >

            <!-- <el-button
              size="mini"
              type="primary"
              @click="handleUpdate(scope.row)"
              >编辑</el-button
            > -->

            <el-button
              style="width: 90px"
              size="mini"
              type="primary"
              v-if="scope.row.addUserId == null"
              @click="reply(scope.row)"
              >回复</el-button
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
      <!-- 回复信息 -->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible2"
        width="30%"
        :before-close="handleClose"
      >
        <el-input disabled v-model="list1.id" style="display: none"> </el-input>

        <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入回复内容"
          v-model="content"
        >
        </el-input>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="replyMessage()">确 定</el-button>
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
  name: "Message",
  data() {
    return {
      typeOptions: [
        {
          value: 0,
          label: "待回复",
        },
        {
          value: 1,
          label: "已回复",
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
      dialogVisible2: false,
      content: "",
    };
  },
  created() {
    //获取广告列表数据
    this.loadMessage();
  },
  methods: {
    handle(row) {
      this.dialogVisible = true;
      this.list1.id = row.id;
    },
    reply(row) {
      this.dialogVisible2 = true;
      this.list1.id = row.id;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((res) => {
          done();
        })
        .catch((err) => {});
    },
    loadMessage() {
      ApiService.post("/message/listPage", {
        currentPage: this.pageNum,
        pageSize: this.pageSize,
        userName: this.filter.userName,
      })
        .then((res) => res.data)
        .then((res) => {
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
      this.loadMessage();
    },
    //3.重置
    ReForm() {
      this.filter.userName = "";
      this.loadMessage();
    },

    //4.修改状态
    handleUpdateStatus() {
      ApiService.post("/message/UpdateStates1", {
        id: this.list1.id,
        state: this.list1.state,
      })
        .then((res) => {
          this.loadMessage();
          this.dialogVisible = false;
        })
        .catch((err) => {
          this.$message = "修改状态失败";
        });
    },
    //4.回复留言
    replyMessage() {
      ApiService.post("message/reply", {
        pid: this.list1.id,
        content: this.content,
      })
        .then((res) => {
          console.log(res.data);
          if (res.data.code == 200) {
            this.loadMessage();
            this.dialogVisible2 = false;
            this.content = "";
          }
        })
        .catch((err) => {
          this.$message = "回复留言失败";
        });
    },

    //5.修改
    // handleUpdate(row) {
    //   alert("修改");
    //   this.$router.push({
    //     path: "updateMessage",
    //     query: { id: row.id },
    //   });
    // },

    //6.新增
    messageAdd() {
      this.$router.push({ name: "AddMessage", path: "addMessage" });
    },
    // //7.删除
    delete01(row) {
      this.$confirm("你确定要撤回回复吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        ApiService.post("/message/deleteById", {
          id: row.id,
        })
          .then((res) => {
            this.loadMessage();
          })
          .catch((err) => {
            this.$message = "删除留言失败";
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

<style></style>

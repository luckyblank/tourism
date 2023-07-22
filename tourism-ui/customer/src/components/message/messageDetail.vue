<template>
  <div>
    <el-button
      size="small"
      type="warning"
      @click="toMessage()"
      style="margin-right: 900px;"
      >返回</el-button
    >

    <el-row>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix" style="">
              <span style="font-size: 20px; color:dodgerblue;">留言:</span>
              <span style=" color:black; font-size: 20px;">{{ content }}</span>
              <span style="font-size: 1px;">{{ addTime }}</span>
            </div>
            <div
              v-for="(reply, j) in comments"
              :key="j"
              class="author-title"
              style="display: flex;flex-direction: column;align-items: flex-start;"
            >
              <div
                class="info"
                style="display:flex;align-items: center
            ;"
              >
                <div
                  class="user"
                  style="font-size: 20px; color:dodgerblue;margin-right: 10px;"
                >
                  {{ reply.userName }}
                </div>
                <div class="content reply">
                  <span v-if="reply.userName != null">:</span
                  >{{ reply.content }}
                </div>
              </div>
              <span style="font-size: 1px;color: #aaa;">{{
                reply.addTime
              }}</span>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  data() {
    return {
      id: 0,
      addTime: "",
      content: "",
      comments: [{}],
    };
  },
  created() {
    this.myrefresh();
    this.content = this.$route.query.content;
    this.addTime = this.$route.query.addTime;
  },
  methods: {
    toMessage() {
      this.$router.push({
        path: "/message",
      });
    },

    myrefresh() {
      newAxios
        .get("/message/listReply", {
          params: {
            pid: parseInt(this.$route.query.messageId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            // let list = resp.data;
            console.log(res);
            this.comments = res.data;
          } else {
            this.$message({
              type: "success",
              message: "正在排队等客服小哥等回复中...",
            });
          }
        });
    },
  },
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.grid-content {
  display: flex;
}
</style>

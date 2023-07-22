<template>
  <div>
    <el-row>
      <div style="margin-right: 800px;">
        <div class="collect">
          <i
            type="primary"
            class="el-icon-arrow-left"
            @click="toStrategy()"
            style=" margin-left: 40px;color:blue; "
            >返回
          </i>

          <i
            type="primary"
            class="el-icon-arrow-left"
            @click="toCollect()"
            v-if="collect === 1"
            style=" margin-left: 20px;color:pink; "
            >返回我的收藏
          </i>
        </div>

        <el-table-column width="620">
          <el-row>
            <el-image
              :src="require('@/assets/images/' + userInfo.icon)"
              style=" width: 50px; height: 50px; border-radius: 100px;"
            ></el-image>

            <span
              style="font-size: 23px; font-weight: bold; color:dodgerblue; margin-bottom: 10px;"
            >
              {{ userInfo.userName }}</span
            >

            <span
              style="font-size: 1px;  margin-left: 10px; margin-top: 0px;  margin-bottom: 0px;"
              >{{ userInfo.addTime }}</span
            >
          </el-row>
        </el-table-column>
      </div>

      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 100%;">
            <div slot="header" class="clearfix">
              <span
                style="font-size: 23px; font-weight: bold; color:dodgerblue;"
              >
                {{ userInfo.title }}</span
              ><br />
            </div>
            <div style="">
              <span style="">推荐:</span>
              <el-rate
                v-model="userInfo.rating"
                disabled
                text-color="#ff9900"
                score-template="{value}"
              >
              </el-rate>
            </div>

            类别:
            <span v-if="userInfo.productType == 0">出行</span>
            <span v-if="userInfo.productType == 1">酒店</span>
            <span v-if="userInfo.productType == 2">路线</span>
            <span v-if="userInfo.productType == 3">景点</span>

            <div>
              <div class="text02Body">
                {{ userInfo.summary }}
              </div>

              <el-table-column
                header-align="center"
                align="center"
                label="图片"
              >
                <el-image
                  :src="require('@/assets/images/' + userInfo.imgUrl)"
                  style="width: 300px; height: 300px;margin-right: 600px;margin-top: 30px;"
                ></el-image>
              </el-table-column>
            </div>

            <span>
              <img
                v-if="userInfo.isCollect"
                style=" width: 25px;height: 25px ; margin-left: 8px;"
                src="../../assets/images/icon/收藏-选中.png"
                @click="collected"
              />
              <img
                v-else
                style=" width: 25px;height: 25px ; margin-left: 8px;"
                src="../../assets/images/icon/收藏-选中 (1).png"
                @click="collected"
              />
              {{ userInfo.collects }}</span
            >

            <span style="margin-bottom:3px;margin-left: 12px;">
              <img
                v-if="userInfo.isLike"
                style=" width: 25px; height: 25px;"
                src="../../assets/images/icon/点赞.png"
                @click="liked"
              />
              <img
                v-else
                style=" width: 25px; height: 25px;"
                src="../../assets/images/icon/点赞 (1).png"
                @click="liked"
              />
              {{ userInfo.liked }}</span
            ><br />
          </el-card>
        </div>
      </el-col>
    </el-row>

    <div class="comment">
      <div>
        <div @click="inputFocus" class="my-reply">
          <el-avatar class="header-img" :size="40" :src="avatar"></el-avatar>
          <div class="reply-info">
            <div
              tabindex="0"
              contenteditable="true"
              id="replyInput"
              spellcheck="false"
              placeholder="输入评论..."
              class="reply-input"
              @focus="showReplyBtn"
              @input="onDivInput($event)"
            ></div>
          </div>

          <div class="reply-btn-box" v-show="btnShow">
            <el-button
              class="reply-btn"
              size="medium"
              @click="sendComment"
              type="primary"
              >发表评论</el-button
            >
          </div>
        </div>

        <div
          v-for="(item, i) in comments"
          :key="i"
          class="author-title reply-father"
        >
          <el-table-column width="620">
            <el-row>
              <el-image
                :src="require('@/assets/images/' + item.icon)"
                style=" width: 40px; height: 40px; border-radius: 100px;"
              ></el-image>
              <span style="font-size: 20px; color:dodgerblue;">
                {{ item.username }}</span
              >
              <span
                style="font-size: 1px;  margin-left: 5px; margin-top: 0px;  margin-bottom: 0px;"
                >:</span
              >

              <span style="font-size: 20px; margin-left: 2px;">{{
                item.content
              }}</span>
              <br />

              <span
                style="font-size: 1px;  margin-left: 30px; margin-top: 20px;  margin-bottom: 0px;"
                >{{ item.createTime }}</span
              >

              <br />

              <el-button type="text" @click="dialogVisible = true"
                >回复</el-button
              >

              <el-button
                type="text"
                @click="delete1(item.id, item.pid)"
                style="color: brown;"
                >删除</el-button
              >
            </el-row>
          </el-table-column>
          <!-- 子级评论列表 -->

          <div
            v-for="(reply, j) in item.commentVo"
            :key="j"
            class="author-title"
          >
            <el-table-column width="620">
              <el-row>
                <el-image
                  :src="require('@/assets/images/' + reply.icon)"
                  style=" width: 40px; height: 40px; border-radius: 100px;"
                ></el-image>
                <span style="font-size: 20px; color:dodgerblue;">
                  {{ reply.username }}</span
                >
                <br />
                <span
                  style="font-size: 1px;  margin-left: 30px; margin-top: 20px;  margin-bottom: 0px;"
                  >{{ reply.createTime }}</span
                >
                <br />
                回复<span style="font-size: 20px; color:dodgerblue;">
                  @{{ reply.toUsername }}:
                </span>
                <span class="reply"> {{ reply.content }}</span>
                <br />
                <el-button
                  type="text"
                  @click="dialogVisible = true"
                  style="margin-left: 2px;"
                  >回复</el-button
                >
                <el-button
                  type="text"
                  @click="delete1(reply.id, reply.pid)"
                  style="color: brown;"
                  >删除</el-button
                >
              </el-row>
            </el-table-column>
          </div>
          <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose"
          >
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入回复"
              v-model="tocomtext"
            >
            </el-input>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="sendCommentReply(item.id)"
                >确 定</el-button
              >
            </span>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  data() {
    return {
      tableData: [],
      placeholder: "", // 回复者名称
      btnShow: false,
      index: "0",
      replyComment: "", // 评论输入内容
      username: "jaychou", // 登录的用户名
      avatar: "https://s1.ax1x.com/2022/06/10/Xc9lUf.png", // 登录用户头像
      userId: "", // 登录用户id
      parentName: "", // 回复的对象（父评论）用户名
      parentId: 0, // 父id
      itemId: "6666", // 文章等等id
      userInfo: {},
      dialogVisible: false,
      comments: [{}],
      tocomtext: "",
      collect: "",
    };
  },
  created() {
    //获取攻略列表数据
    this.loadStrategy();
    //获取评论列表
    this.myrefresh();
    this.collect = parseInt(this.$route.query.collect);
  },
  methods: {
    liked() {
      newAxios
        .get("/strategy/likeStrategy", {
          params: {
            id: parseInt(this.$route.query.strategyId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.loadStrategy();
          } else {
            alert("点赞失败");
          }
        });
    },

    collected() {
      newAxios
        .get("/strategy/collectStrategy", {
          params: {
            id: parseInt(this.$route.query.strategyId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.loadStrategy();
          } else {
            alert("收藏失败");
          }
        });
    },

    toStrategy() {
      this.$router.push({
        path: "/strategy",
      });
    },
    toCollect() {
      this.$router.push({
        path: "/myCollect",
      });
    },

    //获取信息
    loadStrategy() {
      console.log("获取攻略信息");
      newAxios
        .get("/strategy/findStrategyById", {
          params: {
            id: parseInt(this.$route.query.strategyId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.userInfo = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },

    myrefresh() {
      newAxios
        .get("/comment/getCommentAll", {
          params: {
            foreignId: parseInt(this.$route.query.strategyId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            // let list = resp.data;
            console.log(res.data);
            this.comments = res.data;
          }
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "查看失败！请留言联系客服",
          });
        });
    },

    inputFocus() {
      var replyInput = document.getElementById("replyInput");
      replyInput.style.padding = "8px 8px";
      replyInput.style.border = "2px solid #409EFF";
      replyInput.focus();
    },
    showReplyBtn() {
      this.btnShow = true;
    },

    sendComment() {
      // 父评论
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: "warning",
          message: "评论不能为空",
        });
      } else {
        newAxios
          .post("/comment/publisher", {
            foreignId: parseInt(this.$route.query.strategyId),
            content: this.replyComment,
          })
          .then((res) => res.data)
          .then((res) => {
            if (res.code == 200) {
              // let list = resp.data;
              console.log(res);
              this.$message.success("发表评论成功!");
              document.getElementById("replyInput").innerHTML = "";
              this.replyComment = "";
              this.myrefresh();
            } else {
              this.$message.error("评论失败，请稍后重试！");
            }
          });
      }
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((res) => {
          done();
        })
        .catch((err) => {});
    },

    sendCommentReply(id) {
      // 子回复提交
      newAxios
        .post("/comment/comments", {
          foreignId: parseInt(this.$route.query.strategyId),
          content: this.tocomtext,
          pid: id,
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            // let list = resp.data;
            console.log(res);
            this.$message.success("评论成功!");
            this.dialogVisible = false;
            this.tocomtext = "";
            this.myrefresh();
          } else {
            this.$message.error("评论失败，请稍后重试！");
            this.dialogVisible = false;
          }
        });
    },
    delete1(id, pid) {
      console.log(id, pid);
      newAxios
        .post("/comment/detete", {
          id: id,
          pid: pid,
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            console.log(res);
            this.$message.success("删除成功!");

            this.myrefresh();
          } else {
            this.$message.error("你没有权限删除他发表的评论！");
          }
        });
    },
    onDivInput: function(e) {
      this.replyComment = e.target.innerHTML;
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
.collect {
  display: flex;
  align-items: center;
}
.text02Body {
  /* float: left; */
  text-align: left;
  font-size: 18px;
  text-indent: 2em;
  padding: 10px 10px;
  text-align: justify;
}
.comment {
  display: flex;
}
.reply-btn-box {
  display: inline-block;
}
.reply-info {
  width: 500px;
}
</style>

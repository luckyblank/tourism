<template>
  <div>
    <el-row>
      <el-col :span="17">
        <div class="block">
          <el-carousel trigger="click" height="405px">
            <el-carousel-item v-for="item in 1" :key="item">
              <el-image @click="" style="width:100%;height: 100%; " :src="scenery_picurl" :fit="fill">
              </el-image>
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>

      <el-col :span="6" style="text-align: left; margin-left: 20px;">
     <!--   <h1>行程描述:{{scenery_content}}</h1> -->
        <span style="font-size: 21px; font-weight: bold; color:dodgerblue;">{{scenery_content}}</span><br><br>
        <el-rate v-model="value5" disabled show-score text-color="#ff9900" score-template="{value}">
        </el-rate><br>
       <span style="font-size: 20px; font-weight: bold; color: orange;">出发城市：{{scenery_startcity}}</span><br><br>
       <span style="font-size: 20px; font-weight: bold; color: orange;">行程类型：{{scenery_type}}</span><br><br>
        <span style="font-size: 20px; font-weight: bold; color: orange;">行程介绍：</span><br><br><br><br><br><br>
       <el-button
         size="medium"
         type="warning"
         @click="scenery(scenery_id)">立即预订</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12" style="margin-top: 20px;">
        <div class="grid-content bg-purple">

          <div class="block">

            <el-date-picker v-model="value55" type="datetimerange" range-separator="至" start-placeholder="开始日期"
              end-placeholder="结束日期" @change="getscenerydate">
            </el-date-picker>
            <span class="demonstration">共{{datacalc}}天{{scenery_price}}元</span>

          </div>

        </div>
      </el-col>
    </el-row>
    <el-row style="width: 100%; display: block; float: left; margin-top: 30px;margin-bottom: 30px;">


    </el-row>
    <el-row>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; font-size: 25px; color: green;">行程介绍</span>

            </div>
            <div>
              <span style="font-weight: bold; font-size: 15px; color: green;">行程路线</span><br>
              <p>{{scenery_content}}</p><br><br>
              <p>{{scenery_directory}}</p>
            </div>
          </el-card>

        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-light">

          <el-card class="box-card" style="min-height: 700px;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; font-size: 25px; color: green;">周边景点</span>

            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{'列表内容 ' + o }}
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>


  </div>

</template>

<script>
  export default {
    data() {
      return {
        value5: 3.7,
        value55: '',
        scenery_content: '',
        scenery_startcity: '',
        scenery_picurl: '',
        scenery_type: '',
        scenery_price: '0',
        scenery_directory: '',
        datacalc: ' ',
      }

    },
    created() {
      this.getSceneryData();
    },
    methods: {
      //提交订单页跳转的方法
        scenery(id){
          console.log("id:"+id);
          this.$router.push({path:'/order',query:{
            sceneryid:id,
          }});

        },
      getscenerydate() {
        let date = new Date(this.value55[0])
        let y = date.getFullYear()
        let m = date.getMonth() + 1
        m = m < 10 ? ('0' + m) : m
        let d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        const time = y + '-' + m + '-' + d;
        date = new Date(time);
        this.datacalc = date.getTime();
        console.log(this.datacalc);
        date = new Date(this.value55[1]);
        y = date.getFullYear()
        m = date.getMonth() + 1
        m = m < 10 ? ('0' + m) : m
        d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        const time1 = y + '-' + m + '-' + d;
        date = new Date(time1);
        this.datacalc = (date.getTime() - this.datacalc) / 86400000;
        if (this.datacalc == 0) {
          this.datacalc++;
        }
        this.scenery_price = (this.scenery_price) * (this.datacalc)
        console.log(this.datacalc);
      },

      getSceneryData() {
        console.log("11111")
        console.log(this.$route.query.sceneryid);
        this.$axios.get("/scenery/findScenerydetail", {
          params: {
            "scenery_id": this.$route.query.sceneryid,

          }
        }).then(res => {
          console.log("result")
          console.log(res.data)
          this.scenery_content = res.data.scenery_content;
          this.scenery_picurl = res.data.scenery_picurl;
          this.scenery_type = res.data.scenery_type;
          this.scenery_startcity = res.data.scenery_startcity;
          this.scenery_price = res.data.scenery_price;
          this.scenery_directory = res.data.scenery_directory;
        })

      }
    }
  }
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
    clear: both
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

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

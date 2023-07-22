<template>
  <div style="width: 1080px; margin: 0 auto;">
<el-table
      :data="tData"
      style="width: 100%"
      height="270">
      <el-table-column
        fixed
        prop="text"
        label="出发城市"
        width="100"
        >
      </el-table-column>
      <el-table-column
        prop="name"
        label="  泉州 "
        width="120">
        <el-tag>不限</el-tag>
      </el-table-column>
      <el-table-column
        prop="province"
        label=""
        width="840"
        >
      </el-table-column>
     </el-table>
  <!-- 分界线-->
  <el-divider>
     <span style=" font-size: 25px; font-weight: bold;">泉州出境旅游</span>
  </el-divider>

<!-- 景点介绍表格-->
   <el-table  :data="tableData" style="width: 100%">
      <el-table-column
        label="景点图片"
        width="280">
        <template slot-scope="scope">
         <img :src="scope.row.scenery_picurl" alt="无法加载图片" style="width: 260px; height: 170px;" />
        </template>
      </el-table-column>

      <el-table-column
        label="景点信息"
        width="620">
        <template slot-scope="scope">

              <span style="font-size: 21px; font-weight: bold; color:dodgerblue;">{{scope.row.scenery_content}}</span><br><br>
              <span style="font-size: 20px; font-weight: bold; color: orange;">出发城市：{{ scope.row.scenery_startcity}}</span><br><br>
              <span >景点类型：{{ scope.row.scenery_type}}</span><br><br>
              <span >行程介绍：{{ scope.row.scenery_directory}}</span>

        </template>
      </el-table-column>

     <!-- <el-table-column label="价格" width="100" style="font-weight: bold; color: red;" prop="hotel_price">
      </el-table-column> -->

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 写span标签接收价格数据并拼接信息-->
          <span style="display: block; font-weight: bold; color: red;">￥{{ scope.row.scenery_price}}起</span><br>
           <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="warning"
            @click="scenery_detail(scope.row.scenery_id)">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--实现：静态分页-->
      <el-pagination
       background
       layout="prev, pager, next"
       :total="totals"
       :current-page="page"
       :page-size="4"
       @current-change="getList"
       >
      </el-pagination>
</div>

</template>

<script>
    export default{
        name:"foreign",

        data(){
          return{
              imgs:[],
              tableData:[],
              totalPage:0,
              totals:0,
              page:1,
              tData:[{
                    text:'游玩天数',
                    name:'不限',
                    province:'7天     ，8天       ，9天       '
              },{
                text:'热门国家',
                name:'不限',
                province:'塞尔维亚，英国，美国，法国，俄罗斯，'
              },{
                text:'热门景点',
                name:'不限',
                province:'贝尔格莱德，诺维萨德，泽蒙，尼古拉特斯拉博物馆，查查克，阿达~新甘丽佳岛'
              },{
                text:'热门主题',
                name:'不限',
                province:''
              }]
          }
        },
        methods:{
          //景点详情页跳转的方法
                      scenery_detail(id){
                        console.log("id:"+id);
                        this.$router.push({path:'/scenery_detail',query:{
                          sceneryid:id,
                        }});
          
                      },
            getList(page=1,rows=5){
              this.page=page

              //发送请求获取景点信息
              this.$axios.get("/scenery/sceneryFindPage",{
                params: {
                  "page":page,
                  "rows":rows
                }
              }
              ).then(res=>{
                this.tableData = res.data.sceneryList;
                this.totalPage = res.data.totalPage;
                this.totals = res.data.totals;
                this.page = res.data.page;
              })
            }


        },
        created() {
          this.getList()

        }
  }
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

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

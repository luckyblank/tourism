<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"> </detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "updateOrder",
  title: "修改订单",
  components: {
    Detail,
  },
};
</script>

<style></style>

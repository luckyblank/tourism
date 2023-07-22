<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"> </detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "updateCar",
  title: "修改广告位",
  components: {
    Detail
  }
};
</script>

<style></style>

<template>
  <!-- 主键传参 -->

  <detail :isEdit="false"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "AddHotel",
  title: "添加酒店",
  components: {
    Detail,
  },
};
</script>

<style></style>

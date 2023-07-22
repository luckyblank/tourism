<template>
  <!-- 主键传参 -->

  <detail :isEdit="false"> </detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "AddCar",
  title: "添加出行",
  components: {
    Detail,
  },
};
</script>

<style></style>

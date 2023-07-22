<template>
  <!-- 主键传参 -->

  <detail :isEdit="false"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "AddTravelRoute",
  title: "添加路线",
  components: {
    Detail,
  },
};
</script>

<style></style>

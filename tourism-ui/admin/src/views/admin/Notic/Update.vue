<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"> </detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "updateNotic",
  title: "修改公告",
  components: {
    Detail,
  },
};
</script>

<style></style>

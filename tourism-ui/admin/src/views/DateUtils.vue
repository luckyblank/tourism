<template>
  <div style="padding: 40px; font-size: 20px; background-color: bisque">
    <div style="margin: 6px; font-size: 26px">
      马上就要周末了，坚持活力上班哦
    </div>
    <span class="time" id="time">
      时间：<span class="date">{{ nowTime }}</span>
    </span>
  </div>
</template>

<script>
export default {
  name: "DateUtils",
  props: ["s"],
  data() {
    return {
      nowTime: "",
      week: [
        "星期天",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ],
    };
  },
  mounted() {
    this.dateTime();
  },
  methods: {
    dateTime() {
      this.timeFormate();
      setTimeout(() => {
        this.dateTime();
      }, 1000);
    },
    timeFormate() {
      const newtime = new Date();
      this.nowTime =
        this.getIncrease(newtime.getFullYear(), 4) +
        "年" +
        this.getIncrease(newtime.getMonth() + 1, 2) +
        "月" +
        this.getIncrease(newtime.getDate(), 2) +
        "日 " +
        this.week[newtime.getDay()];
    },
    getIncrease(num, digit) {
      var increase = "";
      for (var i = 0; i < digit; i++) {
        increase += "0";
      }
      return (increase + num).slice(-digit);
    },
  },
};
</script>

<style scoped>
@keyframes shark {
  0%,
  100% {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
}
</style>

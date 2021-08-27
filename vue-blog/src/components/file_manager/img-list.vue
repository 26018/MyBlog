<template>
  <div v-show="showThisPage">
    <p>This is imgList</p>
    <div v-for="img in imgList">
      <a :href=" img.url " target="_blank">
        <img :src=" img.url " :alt=" img.name ">
      </a>
    </div>
  </div>
</template>

<script>
import $ from "jquery"

export default {
  name: "img-list",
  data() {
    return {
      imgList: [],
      showThisPage: false,
    };
  },
  created: function () {
    let that = this;
    $.ajax({
      url: "/api/getAllImage",
      dataType: "json",
      success: function (data) {
        console.log(data)
        that.imgList = data;
        that.showThisPage = true;
      }, error: function (err) {
        console.log(err)
      }
    })
  }
}
</script>

<style scoped>
img {
  width: 100px;
  height: 100px;
  border: 2px cornflowerblue solid;
  margin: 10px;
  float: left;
  border-radius: 10%;
}
</style>

<template>
  <div id="preViewBlog" v-show="!showNotice">

    <div v-for="blog in blogsInfo">
      <blog-box :blog="blog"></blog-box>
    </div>

  </div>
</template>

<script>
import $ from 'jquery'
import BlogBox from "./blogBox";

export default {
  name: "preViewBlog",
  components: {BlogBox},
  data() {
    return {
      blogsInfo: [],
      tagName: "",
      showNotice: true,
    };
  },

  mounted() {
    this.tagName = this.$route.query.tagName;
    let that = this;
    $.ajax({
      url: "/api/blog/getBlogsByTagName",
      data:{tagName: that.tagName},
      success: function (data) {
        that.blogsInfo = data;
        that.showNotice = false;
        // console.log(that.blogsInfo[0].title)
      },
      error: function () {

      }
    })
  }
}
</script>

<style scoped>
* {
  margin: 0 auto;
  padding: 0;
}

#preViewBlog {
  /*border: 1px red solid;*/
  width: 100%;
  height: 93%;
}
</style>

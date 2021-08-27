<template>
  <div>
    <div style="">
      <input @input="search" type="text" placeholder="Please type what you wanna search" v-model="searchContent"></input>
    </div>
    <div >
      <div v-for="blog in Blogs">
        <blog-box :blog="blog"></blog-box>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import BlogBox from "./blogBox";
export default {
  name: "searchBlog",
  components: {BlogBox},
  data() {
    return {
      searchContent: "",
      Blogs: [],
    }
  },
  methods: {
    search() {
      if (this.searchContent.trim().length === 0) {
        this.Blogs = [];
        return false;
      }
      let that = this;
      $.ajax({
        url: "/api/blog/search",
        data:{searchContent: that.searchContent},
        success: function (data) {
          that.Blogs = data;
          if (that.searchContent.length === 0) {
            that.Blogs = [];
          }
        },
        error: function () {
          that.$message({
            type: 'error',
            message:"发生错误..",
          })
        }
      })
    }
  },
}
</script>

<style scoped>
input {
  width: 400px;
  height: 40px;
  border-radius: 25px;
  border: 4px #009966 solid;
  background-color: #333333;
  color: white;
  font-size: 18px;
  outline: none;
  text-align: center;
  font-family: Consolas;
}
</style>

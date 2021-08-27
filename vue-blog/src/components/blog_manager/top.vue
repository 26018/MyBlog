<template>
  <div>
    <div style="position: fixed;z-index: 999; float: right;height: 52px;width: 100%">
      <ul>
        <li style="float: left;user-select: none" @click="slideDownContent()" @mouseleave="mouseLve">
          <a>Blog目录</a>
        </li>
        <div class="contentText" id="contentText" v-show="isShow" @mouseenter="mouseEnt" @mouseleave="mouseLve">
          <div v-for="blog in blogsInfo" style="height: auto;width: auto">
            <router-link :to="{name:'showBlog',query:{blog:JSON.stringify(blog)}}" class="contentLi">{{ blog.title }}
              <spam class="el-icon-collection-tag" style="float: right">{{ blog.tags }}</spam>
            </router-link>
          </div>
        </div>
        <li><a href="/readMe">说明</a></li>
        <li><a href="/imgIndex">图床</a></li>
        <li><a href="/timeLine">TimeLine</a></li>
        <li><a href="/getAllTags">Tags</a></li>
        <li><a href="/searchBlog">搜索</a></li>
        <li><a href="/write">写作</a></li>
        <li><a href="/">首页</a></li>
      </ul>
    </div>
  </div>
</template>
<script>
import $ from 'jquery'
export default {
  name: "top",
  data() {
    return {
      isShow: false,
      blogsInfo: [],
    }
  },
  methods: {
    slideDownContent() {
      let that = this;
      $.ajax({
        url: "/api/blog/getAllBlog",
        dataType: "json",
        async: false,
        success: function (data) {
          for (let i = 0; i < data.length; i++) {
            let parse = JSON.parse(data[i]);
            $.ajax({
              url: "/api/blog/getBlogsTag",
              data: {blogId: parse.id},
              async: false,
              success: function (data) {
                parse['tags'] = data;
              },
              error: function () {
                console.log("添加Tag失败");
              }
            })
            that.blogsInfo[i] = parse;
          }
          that.isShow = !that.isShow;
        },
        error: function (err) {
          alert("出错了...")
        }
      })
      console.log(this.isShow);
    },
    mouseLve() {
      this.isShow = false;
    },
    mouseEnt() {
      this.isShow = true;
    }
  },
}
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}

ul {
  list-style-type: none;
  height: 52px;
  width: 100%;
  font-family: Consolas;
  position: relative;
}

li {
  float: right;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.contentText {
  width: auto;
  position: absolute;
  height: auto;
  top: 90%;
  background-color: #CCCCCC;
}

.contentText .contentLi {
  display: inline-block;
  padding: 12px 20px;
  font-size: 16px;
  color: #009966;
  background-color: #CCCCCC;
  width: 100%;
  /*border: 1px seagreen solid;*/
  float: left;
}

</style>

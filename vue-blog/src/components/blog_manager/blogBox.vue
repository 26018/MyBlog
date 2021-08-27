<template>
<div>
    <div class="blogBox" v-if="this.blog != null">
      <router-link :to="{name:'showBlog',query:{blog:JSON.stringify(this.blog)}}">
        <div class="blog">
          <h3>{{ blog.title }}</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ blog.content.replace(/#/g,"") }}</p><br>
          <span class="el-icon-collection-tag" style="float: left;margin-left: 30px;color: #009966;font-size: 14px"></span>
        </div>
      </router-link>
    </div>
</div>
</template>

<script>
export default {
  name: "blogBox",
  props:{
    blog:{
      type: Object
    }
  },
}
</script>

<style scoped>
.blogBox {
  margin: 0 auto;
  margin-top: 20px;
  border: 3px #009966 solid;
  width: 700px;
  height: 200px;
  border-radius: 10px;
  overflow: hidden;
  color: #009966 !important;
}

.blog {
  overflow: hidden;
  float: left;
  width: 700px;
  height: 200px;
}

h3 {
  text-align: center;
  /*float: left;*/
  height: 80px;
  line-height: 500%;
  color: #009966 !important;
}

p {
  /*border:1px red solid;*/
  width: 90%;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  word-wrap: break-word;
  white-space: normal !important;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  line-height: 20px;
  overflow: hidden;
  color: #009966 !important;
  margin: 0 auto;
  /*border: 1px sandybrown solid;*/
}
img {
  float: right;
  width: 400px;
  height: 200px;
}

</style>

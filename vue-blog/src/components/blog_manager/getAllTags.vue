<template>
  <div>

    <div class="box">
      <div v-for="i in Tags" class="ex">
        <router-link :to="{name:'preViewBlog',query:{tagName: i}}" class="ax" :style="{'font-size': 20+'px'}">
          <div style="font-family: Consolas">#{{i}}</div>
        </router-link>
      </div>
    </div>

  </div>
</template>

<script>
import  $ from 'jquery'
export default {
  name: "getAllTags",
  data(){
    return{
      Tags: []
    }
  },
  mounted() {
    let that = this;
    $.ajax({
      url: "/api/blog/getAllBlogsTag",
      success: function (data) {
        that.Tags = data;
      }, error: function () {
      }
    })
  }
}
</script>
<style scoped>
.box {
  /*border: 1px red solid;*/
  width: 100%;
  height: 100%;
  display: inline-block;
}
.ex{
  display: inline-block;
  /*border: 1px white solid;*/
  margin:10px;
  margin-top: 200px;
  width: auto;
}

.ax {
  float: left;
  width: auto;
  height: 60px;
  /*border: 1px darkgoldenrod solid;*/
  margin: 0;
  padding: 0;
}

a {
  float: left;
  line-height: 60px;
  color: #009966;
  width: auto;
}
</style>

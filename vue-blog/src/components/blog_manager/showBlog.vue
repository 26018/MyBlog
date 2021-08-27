<template>
  <div id="showBlog" style="position: fixed;width: 100%;height: 100%" v-show="status">
    <div style="position:relative; width: 100%;height: 100%">
      <div style="margin: 0 auto;position: absolute; left: 50%;transform: translateX(-50%)">
        <h2>{{ blog.title }}</h2>
        <el-button type="text" @click="open(blog.id)" class="blogInfo" title="删除">
          <i class="el-icon-delete"></i>
        </el-button>
        <router-link :to="{name:'write',query:{modifyBlog:JSON.stringify(blog)}}" title="修改"><i
          class="el-icon-edit blogInfo"></i></router-link>
        <div class="blogInfo">时间：{{ timestampToTime(blog.createDate) }} </div>

        <div v-for="tag in blog.tags">
          <router-link :to="{name:'preViewBlog',query:{tagName: tag}}" class="ax" :style="{'font-size': 16+'px'}">
            <div class="blogInfo" @click="getTagBlog">{{[tag]}}</div>
          </router-link>
        </div>

        <div class="blogInfo">作者：{{ blog.author }} 标签：</div>
        <br><br>
        <mavon-editor
          class="editSty"
          :value="blog.content"
          :subfield="false"
          :defaultOpen="'preview'"
          :toolbarsFlag="true"
          :editable="false"
          :scrollStyle="true"
          :ishljs="true"
        />
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: "showBlog",
  data() {
    return {
      blog: {},
      status: false,
    };
  },
  watch: {
    //路由变化
    $route(to, from) {
      this.blog = JSON.parse(this.$route.query.blog);
    }
  },
  methods: {
    timestampToTime(cjsj) {
      var date = new Date(cjsj) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + '-'
      var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      var D = date.getDate() + ' '
      return Y + M + D
    },
    open(id) {
      console.log("real:"+id);
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let that = this;
        $.ajax({
          url: "/api/blog/delete",
          method: 'post',
          data: {blogId: id},
          success: function (data) {
            console.log(id)
            console.log(data);
            if (data === 'success') {
              that.$router.push({name: 'index'})
              that.$message({
                type: 'success',
                message: '删除成功!'
              });
            } else {
              that.$message({
                type: 'info',
                message: '出现错误！'
              });
            }
          },
          error: function () {
            this.$message({
              type: 'info',
              message: '出现错误！'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    getTagBlog(){

    },
  },
  created() {
    this.blog = JSON.parse(this.$route.query.blog);
    let that = this;
    $.ajax({
      url: "/api/blog/getBlogsTag",
      data: {blogId: that.blog.id},
      success: function (data) {
        that.blog['tags'] = data;
        that.status = true;
      },
      error: function () {
        console.log("添加Tag失败");
      }
    })
  }
}

</script>

<style scoped>
#showBlog {
  font-family: Consolas;
}

div {
  margin: 0 auto;
}

h2 {
  float: left;
  color: white;
  display: inline;
  font-family: Consolas;
}

.blogInfo {
  padding: 0;
  text-align: center;
  color: white;
  margin-left: 20px;
  float: right;
}

.editSty {
  height: 85vh;
  width: 80vw;
  margin: 0 auto;
}
</style>

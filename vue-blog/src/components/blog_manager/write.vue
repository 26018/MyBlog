<template xmlns="http://www.w3.org/1999/html">
  <div id="write">
    <div class="blogInfo">
      标题：<input @input="saveBlogForTemp" id="title" type="text" v-model="title">
      作者：<input @input="saveBlogForTemp" id="author" type="text" v-model="author">
      标签：<input @input="saveBlogForTemp"  id="tags" type="text" v-model="tags"
                @focus="mouseIn()"  @blur.capture="mouseOut()">

      <div v-if="searchTagResult.length != 0" v-show="boxVisiable" style="
          overflow: hidden;
          height: 30px;
          width: 150px;
          margin-left: 60%;
          line-height: 30px;
          color: #009966;
          /*border: 1px yellow solid;*/
          ">可选择的Tag
      </div>

      <div v-for="ta in searchTagResult" style="
        margin-left: 60%;
        width: 150px;
        color: #993333;
">
        <div v-show="boxVisiable" style="
          overflow: hidden;
          height: 30px;
          line-height: 30px;
          /*border: 1px yellow solid;*/
          " @click="chooseTag(ta)" >{{ ta }}
        </div>
      </div>

    </div>
    <br>
    <div @input="saveBlogForTemp">
      <mavon-editor class="mavon-editor" v-model="content" @save="saveBlog" @imgAdd="imgAdd" ref="md"></mavon-editor>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: "write",
  data() {
    return {
      title: "",
      author: "",
      // userId: "",
      tags: "",
      content: "",
      saveMode: "", //saveBlog || updateBlog
      willBeModifiedBlog: {},
      searchTagResult: [],
      boxVisiable: false,
      lastUpdate: ""
    };
  },
  methods: {
    saveBlog() {
      if (this.title.trim() === "") {
        this.$message({
          type: 'error',
          message: "请填写标题！"
        })
        return
      }
      if (this.author.trim() === "") {
        this.$message({
          type: 'error',
          message: "请填写作者！"
        })
        return
      }
      if (this.tags.trim() === "") {
        this.$message({
          type: 'error',
          message: "请填写标签！"
        })
        return
      }
      if (this.content.trim() === "") {
        this.$message({
          type: 'error',
          message: "请填写正文！"
        })
        return
      }

      let that = this;
      console.log("mode:" + this.saveMode);
      let url = "";
      let params = {};
      if (this.saveMode === "saveBlog") {
        url = "/api/blog/save";
        params = {
          'title': that.title.trim(),
          'author': that.author.trim(),
          'tags': that.tags.trim(),
          'content': that.content.trim(),
        }
      }
      else if (this.saveMode === "updateBlog") {
        url = "/api/blog/update";
        that.willBeModifiedBlog.title = that.title.trim(),
          that.willBeModifiedBlog.author = that.author.trim(),
          that.willBeModifiedBlog.tags = that.tags.trim(),
          that.willBeModifiedBlog.content = that.content.trim(),
          params = that.willBeModifiedBlog;
      }
      let s = JSON.stringify(params);

      console.log("lastUpdate:"+that.lastUpdate)
      if (that.lastUpdate.toString().length === 0) {
        that.lastUpdate = Date.now();
        console.log(that.lastUpdate)
      }else if ((Date.now()-that.lastUpdate) < 60000){
        that.$message({
          type: "error",
          roundButton:true,
          showClose:true,
          message: "一分钟内不可重复发布blog！"
        })
        return false;
      }else {
        that.lastUpdate = Date.now();
      }
      $.ajax({
        url: url,
        method: "post",
        data: {'blog': s},
        success: function (data) {
          if (data === "success") {
            localStorage.removeItem('blog')
            that.$message({
              type: 'success',
              message: "保存成功！"
            })
          } else {
            that.$message({
              type: 'error',
              message: "出现错误,请稍后重试！"
            })
          }
        },
        error: function () {
          that.$message({
            type: 'error',
            message: "保存失败！"
          })
        }
      });
    },
    saveBlogForTemp() {
      let that = this;
      console.log("tags:" + this.tags)
      $.ajax({
        url: "/api/blog/getAllTagNameLikeInput",
        data: {tags: that.tags},
        success: function (data) {
          let j = 0;
          that.searchTagResult = [];
          if (data != null) {
            for (let i in data) {
              if (data[i] !== null) {
                that.searchTagResult[j++] = data[i];
              }
            }
          }
        },
        error: function () {

        }
      })

      let blog = {
        'title': this.title,
        'author': this.author,
        'tags': this.tags,
        'content': this.content
      };
      localStorage.setItem('blog', JSON.stringify(blog));
    },
    imgAdd(pos, $file) {
      let that = this;
      console.log("UploadFile..")
      // 第一步.将图片上传到服务器.
      let formdata = new FormData();
      formdata.append('file', $file);
      $.ajax({
        url: "/api/uploadFile",
        method: "post",
        data: formdata,
        contentType: false,
        processData: false,
        success: function (data) {
          that.$refs.md.$img2Url(pos, data);
        }, error: function (err) {
        }
      })
    },
    mouseOut() {
      let that = this
      setTimeout(function () {
        console.log("out...")
        that.boxVisiable = false;
      }, 500);
    },
    mouseIn() {
      console.log("in...")
      this.boxVisiable = true;
    },
    chooseTag(data){
      console.log("chooseTag:"+data)
      this.tags = data;
    }

  },
  mounted() {
    let that = this
    $.ajax({
      url: "/api/blog/getAllTagNameLikeInput",
      data: {tags: that.tags},
      success: function (data) {
        let j = 0;
        that.searchTagResult = [];
        if (data != null) {
          for (let i in data) {
            if (data[i] !== null) {
              that.searchTagResult[j++] = data[i];
            }
          }
        }
      },
      error: function () {
      }
    })

    //编辑blog
    let modifyBlog = this.$route.query.modifyBlog;
    if (modifyBlog != null) {
      console.log("编辑模式...")
      modifyBlog = JSON.parse(modifyBlog);
      this.willBeModifiedBlog = modifyBlog;
      this.saveMode = "updateBlog";
      this.content = modifyBlog.content;
      this.title = modifyBlog.title;
      this.author = modifyBlog.author;
      this.tags = modifyBlog.tags;
      return;
    } else
      this.saveMode = "saveBlog";

    console.log(this.saveMode)
    //存在blog缓存
    let blogCache = localStorage.getItem('blog');
    if (blogCache != null) {
      blogCache = JSON.parse(blogCache)
      this.content = blogCache.content;
      this.title = blogCache.title;
      this.author = blogCache.author;
      this.tags = blogCache.tags;
    }
  }
}
</script>

<style scoped>
#write {
  height: 100vh;
  width: 100%;
  text-align: center;
  margin: 0 auto;
}

input {
  border-top: 0;
  border-left: 0;
  border-right: 0;
  border-bottom: 2px green solid;
  background-color: #333333;
  outline: none;
  font-size: 16px;
  color: white;
  width: 150px;
}

.mavon-editor {
  height: 85vh;
  width: 85vw;
  margin: 0 auto;
}

.blogInfo {
  text-align: center;
  color: white;
  margin-left: 20px;
}
</style>

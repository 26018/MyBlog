<template>
  <div style="position: fixed;width: 100%;height: 100%">
    <div style="position:relative; width: 100%;height: 100%">
      <div style="margin: 0 auto;position: absolute; left: 50%;transform: translateX(-50%)">
        <br><br>
        <mavon-editor class="editSty" :editable="false" :value="content"></mavon-editor>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "aboutMe",
  data(){
    return {
      content: "# 说明:\n" +
        "- 此博客用于记录学习中的小知识,\n" +
        "- 采用MarkDown语法记录文字\n" +
        "- 有空会尽量更新.\n" +
        "- 更多MarkDown语法详情请参见 [MarkDown语法](https://markdown.com.cn/basic-syntax/)\n"
    };
  }
}
</script>

<style scoped>
div{
  margin:0 auto;
}
.editSty {
  height: 80vh;
  width: 80vw;
  margin: 0 auto;
}
</style>

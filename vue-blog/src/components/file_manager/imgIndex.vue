<template>
  <div class="top">

      <router-link :to="{name:'imgList'}">图片列表</router-link>

      <router-link :to="{name: 'uploadFile'}">图片上传</router-link>

  </div>
</template>
<script>
export default {

  name: 'imgIndex',
}
</script>

<style scoped>
.top {
  /*border: 1px red solid;*/
  width: 100%;
  height: 90%;
  text-align: center;
}

a {
  padding: 20px;
  margin: 30px;
  border: 2px blueviolet solid;
  border-radius: 20px;
  line-height: 300px;
  font-size: 20px;
  text-decoration: none;
}
</style>

<template>
<div>
  <p>this is upload_file</p>

  <form enctype="multipart/form-data" action="/api/uploadFile" method="post">
    <input type="file" name="file" required="true">
    <input type="submit" value="上传" >
  </form>

</div>
</template>

<script>

export default {
  name: "upload-file",
}
</script>

<style scoped>

</style>

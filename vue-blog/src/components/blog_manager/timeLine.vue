<template>
  <div class="container">
    <header class="mainHeader">
      <div>
        <span class="timeYear">2021</span>
        <div class="listHeader">
          <span class="time">03-18</span>
          <span class="listP">跨域请求</span>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  name: "timeLine"
}
</script>

<style scoped>

.container {

}

.listHeader, .timeYear {
  display: block;
  width: 30%;
  margin: 30px auto 0;
  height: 70px;
  line-height: 70px;
  border-bottom: grey 1px dashed;
  position: relative;
  text-align: left;
}

.listHeader:before, .timeYear:before {
  content: '';
  width: 10px;
  height: 10px;
  border-radius: 100%;
  background-color: white;
  position: absolute;
  left: 0;
  top: 44%;
}

.listHeader:before {
  width: 8px;
  height: 8px;
}


.time, .timeYear {
  color: white;
  padding: 0 20px;
}

.timeYear {
  display: block;
  font-size: 20px;
  font-weight: 900;
}

.listP {
  color: white;
  font-size: 18px;
}
</style>

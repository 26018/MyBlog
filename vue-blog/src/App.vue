<template>
  <div id="app">
    <top></top>
    <div class="ContentPage">
      <transition mode="out-in">
        <router-view class="content"></router-view>
      </transition >
    </div>
  </div>
</template>

<script>
import top from './components/blog_manager/top'
import blogBox from "./components/blog_manager/blogBox";

export default {
  name: 'App',
  components: {top,blogBox}
}
</script>

<style>

.v-enter, .v-leave-to {
  opacity: 0;
  transform: translateX(140px);
}
.v-enter-active,.v-leave-active {
  transition: all 0.5s ease;
}

* {
  padding: 0;
  margin: 0;
}
.ContentPage{
  margin: 0 auto;
  overflow: hidden;
  width: 100%;
  height: 100%;
  padding: 0;
  /* border: 1px red solid; */
}
#app {
  height: 100vh;
  width: 100%;
  background-color: #333333;
}
::-webkit-scrollbar {
  width: 0;
  height: 0;
}
.content {
  text-align: center;
  margin: 0 auto;
  width: 100%;
  height: 100vh;
  z-index: 888;
  overflow: scroll;
  position: relative;
  top: 52px;
}
</style>

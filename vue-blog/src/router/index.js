import Vue from 'vue'
import Router from 'vue-router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import write from "../components/blog_manager/write";
import readMe from "../components/blog_manager/readMe";
import preViewBlog from "../components/blog_manager/preViewBlog";
import showBlog from "../components/blog_manager/showBlog";
import searchBlog from "../components/blog_manager/searchBlog";
import getAllTags from "../components/blog_manager/getAllTags";
import index from '../components/blog_manager/index'

//file_manager
import imgIndex from "../components/file_manager/imgIndex";
import img_list from "../components/file_manager/img-list";
import upload_file from "../components/file_manager/upload-file";
import error_page from "../components/file_manager/err_page";
import timeLine from "../components/blog_manager/timeLine";


Vue.use(Router);
Vue.use(ElementUI);
Vue.use(mavonEditor);

export default new Router({

  mode: "history",
  routes: [
    {path: '/', name: 'index', component: index},
    {path: '/preViewBlog', name: 'preViewBlog', component: preViewBlog},
    {path: '/write', name: 'write', component: write},
    {path: '/readMe', name: 'readMe', component: readMe},
    {path: '/showBlog', name: 'showBlog', component: showBlog},
    {path: '/searchBlog', name: 'searchBlog', component: searchBlog},
    {path: '/getAllTags', name: 'getAllTags', component: getAllTags},
    {path: '/timeLine', name: 'timeLine', component: timeLine},

    //img-manager

    {path: '/imgIndex', name: 'imgIndex', component: imgIndex},
    {path: '/imgList', name: 'imgList', component: img_list},
    {path: '/uploadFile', name: 'uploadFile', component: upload_file},
    {path: '*', name: 'error', component: error_page},


  ]
})

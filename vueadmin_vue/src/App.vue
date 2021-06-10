<template>
      <div id="app">
            <router-view/>
      </div>
</template>

<!--刷新浏览器，从session读取界面存储的 tab数据-->
<!--从router中 控制，更好理解一些-->
<!--在这里处理，更节省加载次数，-->
<!--在router里面，每次更换当前路由，都需要重新加载 -- ！-->
<!--好像又没啥区别啊，还不如放进router拦截器中-->
<script>
      // export default {
      //       name: 'App',
      //       watch: {
      //             $route(to,from){
      //                   // 处理用户 刷新时 保存editTabs中的内容 值sessionStorage中
      //                   if(to.path != '/login'){
      //                         var editableTabsValue = sessionStorage.getItem('editableTabsValue')
      //                         var editableTabs = JSON.parse(sessionStorage.getItem('editableTabs'));
      //                         //logout之后，不走这里
      //                         if(editableTabs != null){
      //                               // console.log('router watch route 从session中获取 tabs数组');
      //                               this.$store.commit('setEditableTabsValue',editableTabsValue)
      //                               this.$store.commit('setEditableTabs',editableTabs)
      //                         }
      //                   }
      //             }
      //       }
      // }
</script>
<style>
      html, body, #app {

            font-family:
                "Source Sans Pro",
            "Helvetica Neue",
            Arial, sans-serif,
            'Hiragino Sans GB',
            'WenQuanYi Micro Hei',
            'Microsoft Yahei';
            height: 100%;
            padding: 0;
            margin: 0;
            background: #f8f8f8;
            font-size: 13px;
      }
</style>

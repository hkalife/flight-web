<template>
  <div id="app">
    
    <HelloWorld />
    <!--<Aviao />-->
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
//import Aviao from './components/Aviao.vue'

export default {
  name: 'app',
  components: {
    HelloWorld
    //Aviao
  }
}
</script>

<style>
body {
  background-color: #026F58;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

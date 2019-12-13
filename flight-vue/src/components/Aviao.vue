<template>
  <div id="main" class="main col-12">
    <h1>{{ msg }}</h1>

    <!--<div v-for="(aviao, index) in avioes" :key="index">
      {{id}} . {{fabricante}} - {{prefixo}}
    </div>-->

    <div>
      <div v-for="(aviao, index) in avioes" :key="index">
        <h2>{{ aviao.id }}</h2>
        <h2>{{ aviao.fabricante }}</h2>
        <h2>{{ aviao.prefixo }}</h2>
      </div>
    </div>

  </div>
</template>

<script>
//import AviaoApi from '../aviaoApi.js';

export default {
  name: 'Aviao',
  data: () => {
    return {
      avioes: []
    }
  },
  methods: {
  },
  created () {
    this.$http.get('http://localhost:8080/flight-backend/webresources/aviao/findAll').then(function(data){
      this.avioes = data.slice(0, 10);
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

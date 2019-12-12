<template>
  <div id="main" class="main col-12">
    <h1>{{ msg }}</h1>
    
    <div id="secaoRequisicao">
      <p>Digite o código do avião: </p>
      <input type="text" placeholder="ex: 1" v-on:blur="buscar()" v-model="idParaBuscar">
    </div>
    <div id="resultado">
      <div v-if="requisicaoOk" id="secaoResultado">
        <p>Fabricante: {{ fabricante }}</p>
        <p>Prefixo: {{ prefixo }}</p>
      </div>
    </div>
    <h2 id="avisoErro" v-if="algoErrado">Houve algo de errado. Reveja o ID digitado.</h2>

    
  </div>
</template>

<script>
import AviaoApi from '../aviaoApi.js';

export default {
  name: 'HelloWorld',
  data: () => {
    return {
      msg: 'Avião',
      anterior: '0',
      id: '',
      idParaBuscar: '',
      fabricante: '',
      prefixo: '',
      avisoAnterior: false,
      requisicaoOk: false,
      algoErrado: false
    }
  },
  methods: {
    async buscar() {
      if (this.anterior !== this.idParaBuscar) {
        const api = new AviaoApi()
        const response = await api.buscar( this.idParaBuscar )
        this.id = response.id
        this.fabricante = response.fabricante
        this.prefixo = response.prefixo
        if (response !== null || response !== undefined) {
          if (this.fabricante !== undefined) {
            this.requisicaoOk = true
            this.algoErrado = false
          }
          else {
            this.algoErrado = true
            this.requisicaoOk = false
          }
        }
      }
      else {
        this.avisoAnterior = true
      }
      this.anterior = this.idParaBuscar
    }
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

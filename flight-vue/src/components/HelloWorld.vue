<template>
  <div id="main" class="main col-12">
    
    <div v-if="menuPrincipal" id="menuPrincipal">
      <img id="airportLogo" src="../assets/airport.png">
      <h1 id="titulo">{{ msg }}</h1>
      <p id="subtitulo">Por favor, escolha o que deseja consultar</p>
      <div id="linhaBotoes">
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="paginaAvioes()">Aviões</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="paginaTripulantes()">Tripulantes</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="paginaVoos()">Voos</button>
      </div>
    </div>

    <div v-if="menuAviao" id="menuAviao">
      <h1 id="titulo">Aviões</h1>
      <p id="subtitulo">Opções</p>
      <div id="linhaBotoes">
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="voltarMenuPrincipal()">Voltar</button>
      </div>
    </div>

    <div v-if="menuTripulantes" id="menuTripulantes">
      <h1 id="titulo">Tripulantes</h1>
      <p id="subtitulo">Opções</p>
      <div id="linhaBotoes">
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="voltarMenuPrincipal()">Voltar</button>
      </div>
    </div>

    <div v-if="menuVoos" id="menuVoos">
      <h1 id="titulo">Voos</h1>
      <p id="subtitulo">Opções</p>
      <div id="linhaBotoes">
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="voltarMenuPrincipal()">Voltar</button>
      </div>
    </div>
    
    <!--<div id="secaoRequisicao">
      <p>Digite o código do avião: </p>
      <input type="text" placeholder="ex: 1" v-on:blur="buscar()" v-model="idParaBuscar">
    </div>
    <div id="resultado">
      <div v-if="requisicaoOk" id="secaoResultado">
        <p>Fabricante: {{ fabricante }}</p>
        <p>Prefixo: {{ prefixo }}</p>
      </div>
    </div>
    <h2 id="avisoErro" v-if="algoErrado">Houve algo de errado. Reveja o ID digitado.</h2>-->

  </div>
</template>

<script>
import AviaoApi from '../aviaoApi.js';

export default {
  name: 'HelloWorld',
  data: () => {
    return {
      msg: 'Flight Web',
      anterior: '0',
      id: '',
      idParaBuscar: '',
      fabricante: '',
      prefixo: '',
      avioes: '',
      avisoAnterior: false,
      requisicaoOk: false,
      algoErrado: false,
      menuPrincipal: true,
      menuAviao: false,
      menuTripulantes: false,
      menuVoos: false
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
    },
    voltarMenuPrincipal() {
      this.menuPrincipal = true
      this.menuAviao = false
      this.menuTripulantes = false
      this.menuVoos = false
    },
    paginaAvioes () {
      this.menuPrincipal = false
      this.menuAviao = true
      this.menuTripulantes = false
      this.menuVoos = false
    },
    paginaTripulantes () {
      this.menuPrincipal = false
      this.menuAviao = false
      this.menuTripulantes = true
      this.menuVoos = false
    },
    paginaVoos () {
      this.menuPrincipal = false
      this.menuAviao = false
      this.menuTripulantes = false
      this.menuVoos = true
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
#airportLogo {
  width: 10%;
}
#botaoEntidade {
  width: 10%;
  margin-right: 10%;
  margin-left: 10%;
  background-color: #026F58;
  padding-right: 1rem;
  padding-left: 1rem;
}
#linhaBotoes {
  padding-top: 1%;
}
#main {
  padding-bottom: 2.5%;
  padding-top: 2.5%;
  border-radius: 0.5rem;
  background-color:#FFFFFF;
}
#titulo, #subtitulo {
  padding-top: 1%;
}
</style>

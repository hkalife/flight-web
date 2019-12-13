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

    <div v-if="menuEntidade" id="menuAviao">
      <img id="airportLogo" src="../assets/airport.png">
      <h1 id="titulo">{{ nomeEntidadeTela }}</h1>
      <p id="subtitulo">Selecione a opção desejada</p>
      <div id="linhaBotoes">
        <button id="botaoVoltar" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="voltarMenuPrincipal()">Voltar</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="irTelaOperacao('C')">Criar Novo</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="irTelaOperacao('R')">Consultar</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="irTelaOperacao('U')">Editar</button>
        <button id="botaoEntidade" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="irTelaOperacao('D')">Remover</button>
      </div>
    </div>

    <div v-if="menuOperacao" id="menuOperacao">
      <h1 id="titulo">{{ nomeOperacaoCrud }} {{ nomeEntidadeTela }}</h1>

      <div v-if="utilizacaoCreate">
        <p>Utilização Create.</p>
      </div>

      <div v-if="utilizacaoRead">
        
        <div v-if="nomeEntidadeTela === 'Aviões'" id="readAviao">
          <p>Digite o código a ser retornado </p>
          <input type="text" placeholder="ex: 1" v-on:blur="readAviao()" v-model="idParaBuscar">
          <div id="resultadoAviao" style="padding-top: 2%;">
            <div v-if="requisicaoOk" id="secaoResultado">
              <p>Fabricante: {{ fabricante }}</p>
              <p>Prefixo: {{ prefixo }}</p>
            </div>
          </div>
          <h2 id="avisoErro" v-if="algoErrado">Houve algo de errado. Reveja o ID digitado.</h2>
        </div>

        <div v-if="nomeEntidadeTela === 'Tripulantes'" id="readTripulante">
          <p>Digite o código a ser retornado </p>
          <input type="text" placeholder="ex: 1" v-on:blur="readTripulante()" v-model="idParaBuscar">
          <div id="resultadoTripulante" style="padding-top: 2%;">
            <div v-if="requisicaoOk" id="secaoResultado">
              <p>Nome: {{ nomeTripulante }}</p>
              <p>Email: {{ emailTripulante }}</p>
              <p>Voos: {{voosTripulante}} </p>
            </div>
          </div>
          <h2 id="avisoErro" v-if="algoErrado">Houve algo de errado. Reveja o ID digitado.</h2>
        </div>

      </div>

      <div v-if="utilizacaoUpdate">
        <p>Utilização Update.</p>
      </div>

      <div v-if="utilizacaoDelete">
        <p>Utilização Delete.</p>
      </div>

      <div id="linhaBotoes">
        <button id="botaoVoltar" type="button" class="col-sm-12 btn btn-outline-light" v-on:click="voltarMenuPrincipal()">Voltar</button>
      </div>
    </div>

    <!--<div v-if="menuTripulantes" id="menuTripulantes">
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
    </div>-->
    
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
import TripulanteApi from '../tripulanteApi.js';

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
      menuEntidade: false,
      menuOperacao: false,
      nomeEntidadeTela: '',
      nomeOperacaoCrud: '',
      operacao: '',
      utilizacaoCreate: false,
      utilizacaoRead: false,
      utilizacaoUpdate: false,
      utilizacaoDelete: false,
      idTripulante: '',
      nomeTripulante: '',
      emailTripulante: '',
      voosTripulante: {}
    }
  },
  methods: {

    async readAviao() {
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
    async readTripulante() {
      if (this.anterior !== this.idParaBuscar) {
        const api = new TripulanteApi()
        const response = await api.buscar( this.idParaBuscar )
        this.idTripulante = response.id
        this.nomeTripulante = response.nome
        this.emailTripulante = response.email
        this.voosTripulante = response.voos
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
      this.menuEntidade = false
      this.menuOperacao = false

      this.nomeEntidadeTela = 'Flight Web'
      this.idParaBuscar = ''
      this.requisicaoOk = false
    },
    paginaAvioes () {
      this.menuPrincipal = false
      this.nomeEntidadeTela = 'Aviões'
      this.menuEntidade = true
    },
    paginaTripulantes () {
      this.menuPrincipal = false
      this.nomeEntidadeTela = 'Tripulantes'
      this.menuEntidade = true
    },
    paginaVoos () {
      this.menuPrincipal = false
      this.nomeEntidadeTela = 'Voos'
      this.menuEntidade = true
    },
    irTelaOperacao (operacao) {
      this.menuPrincipal = false
      this.menuEntidade = false
      this.menuOperacao = true

      if (operacao === 'C') {
        this.nomeOperacaoCrud = 'Create'
        this.utilizacaoCreate = true

        this.utilizacaoRead = false
        this.utilizacaoUpdate = false
        this.utilizacaoDelete = false
      }
      if (operacao === 'R') {
        this.nomeOperacaoCrud = 'Read'
        this.utilizacaoRead = true

        this.utilizacaoCreate = false
        this.utilizacaoUpdate = false
        this.utilizacaoDelete = false
      }
      if (operacao === 'U') {
        this.nomeOperacaoCrud = 'Update'
        this.utilizacaoUpdate = true

        this.utilizacaoRead = false
        this.utilizacaoCreate = false
        this.utilizacaoDelete = false
      }
      if (operacao === 'D') {
        this.nomeOperacaoCrud = 'Delete'
        this.utilizacaoDelete = true

        this.utilizacaoRead = false
        this.utilizacaoUpdate = false
        this.utilizacaoCreate = false
      }
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
  margin-right: 5%;
  margin-left: 5%;
  background-color: #026F58;
  padding-right: 1rem;
  padding-left: 1rem;
}
#botaoVoltar {
  width: 10%;
  margin-right: 5%;
  margin-left: 5%;
  background-color: #ff0000;
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

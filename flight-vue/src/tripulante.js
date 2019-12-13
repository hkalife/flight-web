export default class Tripulante {
    constructor( jsonVindoDaApi ) {
      this.id = jsonVindoDaApi.id
      this.email = jsonVindoDaApi.email
      this.nome = jsonVindoDaApi.nome
      this.voos = jsonVindoDaApi.vooCollection
    }
}
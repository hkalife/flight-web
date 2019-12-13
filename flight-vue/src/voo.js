export default class Voo {
    constructor( jsonVindoDaApi ) {
      this.id = jsonVindoDaApi.id
      this.destino = jsonVindoDaApi.destino
      this.origem = jsonVindoDaApi.origem
      this.tripulantes = jsonVindoDaApi.tripulanteCollection
      this.aviao = jsonVindoDaApi.aviaoId
    }
}
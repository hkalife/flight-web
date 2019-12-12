export default class Aviao {
    constructor( jsonVindoDaApi ) {
      this.id = jsonVindoDaApi.id
      this.fabricante = jsonVindoDaApi.fabricante
      this.prefixo = jsonVindoDaApi.prefixo
    }
}
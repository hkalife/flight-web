import Aviao from './aviao.js'

export default class AviaoApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/aviao"
    }

    //CREATE
  
    //READ
    async buscar( id ) {
      let urlAviao = `${ this.url }/find/${ id }`
      
      return new Promise( resolve => {
        fetch(urlAviao, {
            "method": "GET"
        })
        .then( j => j.json() )
        .then( a => {
            const aviao = new Aviao(a)
            resolve(aviao)
        })
      })
    }

    //UPDATE

    //DELETE
}
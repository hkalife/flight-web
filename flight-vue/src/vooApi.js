import Voo from './voo.js'

export default class VooApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/voo"
    }

    //CREATE
  
    //READ

    async buscar( id ) {
      let urlVoo = `${ this.url }/find/${ id }`
      
      return new Promise( resolve => {
        fetch(urlVoo, {
            "method": "GET"
        })
        .then( j => j.json() )
        .then( v => {
            const voo = new Voo(v)
            resolve(voo)
        })
      })
    }    

    //UPDATE

    //DELETE
}
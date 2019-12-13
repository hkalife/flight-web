import Voo from './voo.js'

export default class VooApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/voo"
    }

    //CREATE
    async cadastrar(voo) {
        let urlVoo = `${ this.url }/create`        
          return new Promise( resolve => {
              fetch(urlVoo, {
                  "method": "POST",
                  "body": JSON.stringify(voo),
                  "headers": {
                    'Content-Type': 'application/json'
                  }
              })
              .then( j => j.json() )
              .then( t => {
                  resolve(t)
              })
          })
    }
  
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

    async remover( id ) {
        let urlVoo = `${ this.url }/remove/${ id }`
        
        return new Promise( resolve => {
          fetch(urlVoo, {
              "method": "DELETE"
          })
          .then( j => j.json() )
          .then( v => {
              const voo = new Voo(v)
              resolve(voo)
          })
        })
      }
}
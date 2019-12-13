import Tripulante from './tripulante.js'

export default class TripulanteApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/tripulante"
    }

    //CREATE
    async cadastrar(tripulante) {
        let urlTripulante = `${ this.url }/create`        
          return new Promise( resolve => {
              fetch(urlTripulante, {
                  "method": "POST",
                  "body": JSON.stringify(tripulante),
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
      let urlTripulante = `${ this.url }/find/${ id }`
      
      return new Promise( resolve => {
        fetch(urlTripulante, {
            "method": "GET"
        })
        .then( j => j.json() )
        .then( t => {
            const tripulante = new Tripulante(t)
            resolve(tripulante)
        })
      })
    }

    //UPDATE

    //DELETE
    async remover( id ) {
        let urlTripulante = `${ this.url }/remove/${ id }`
        
        return new Promise( resolve => {
          fetch(urlTripulante, {
              "method": "DELETE"
          })
          .then( j => j.json() )
          .then( t => {
              const tripulante = new Tripulante(t)
              resolve(tripulante)
          })
        })
      }
}
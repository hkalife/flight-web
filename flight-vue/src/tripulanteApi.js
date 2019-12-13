import Tripulante from './tripulante.js'

export default class TripulanteApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/tripulante"
    }

    //CREATE
  
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
}
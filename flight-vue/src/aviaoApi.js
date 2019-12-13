import Aviao from './aviao.js'

export default class AviaoApi {
    constructor() {
      this.url = "http://localhost:8080/flight-backend/webresources/aviao"
    }

    //CREATE
    async cadastrar(aviao) {
      let urlAviao = `${ this.url }/create`

      //var body = '[{"fabricante":"' + aviao.fabricante + '", "prefixo": "' + aviao.prefixo + '"}]'
      //var obj = JSON.parse(body)
      
        return new Promise( resolve => {
            fetch(urlAviao, {
                "method": "POST",
                "body": JSON.stringify(aviao),
                "headers": {
                  'Content-Type': 'application/json'
                  //'Content-Type': 'application/x-www-form-urlencoded',
                  //'Authorization': 'Basic dGVzdGp3dGNsaWVudGlkOlhZN2ttem9OemwxMDA='
                }
            })
            .then( j => j.json() )
            .then( a => {
                resolve(a)
            })
        })
    }
  
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
    async remover( id ) {
        let urlAviao = `${ this.url }/remove/${ id }`
        
        return new Promise( resolve => {
          fetch(urlAviao, {
              "method": "DELETE"
          })
          .then( j => j.json() )
          .then( a => {
              const aviao = new Aviao(a)
              resolve(aviao)
          })
        })
      }
}
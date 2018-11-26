/** @typedef {import("vue-resource/types/vue_resource").$resource} $resource  */

export default class TelehoneService {
  /**
   * @param {$resource} resource
   */
  constructor (resource) {
    this._userResource = resource('user{/idUser}/telephone{/idTelephone}')
    this._resource = resource('telephone{/id}')
  }

  findAll (user) {
    return this._userResource
      .query( { idUser:user.id } )
      .then(res => res.json())
  }

  save(telephone) {
  	return this._resource.save( telephone ).then( res => res.json() );
  }

  remove(user) {
  	return this._resource.delete({id:user.id}).then( res => res );
  }
}

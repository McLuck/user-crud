/** @typedef {import("vue-resource/types/vue_resource").$resource} $resource  */

export default class UserActivityService {
  /**
   * @param {$resource} resource
   */
  constructor (resource) {
    this._resource = resource('user-activity{/id}')
  }

  findAll (user) {
    return this._resource
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

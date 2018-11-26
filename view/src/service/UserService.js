/** @typedef {import("vue-resource/types/vue_resource").$resource} $resource  */

export default class UserService {
  /**
   * @param {$resource} resource
   */
  constructor (resource) {
    this._resource = resource('user{/id}')
  }

  findAll () {
    return this._resource
      .query()
      .then(res => res.json())
  }

  save(user) {
  	if( user.id ) {
  		return this._resource.update(user).then( res => res.json() );
  	} else {
  		return this._resource.save(user).then( res => res.json() );
  	}
  }

  remove(user) {
  	return this._resource.delete({id:user.id}).then( res => res );
  }
}

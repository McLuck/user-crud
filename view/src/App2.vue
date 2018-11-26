<template>
	<div>
		<md-toolbar class="md-primary">
			<h3 class="md-title">CRUD de usuário</h3>
		</md-toolbar>
		<div>
			<md-dialog :md-active.sync="showDialog" v-if="selected">
				<md-dialog-title>Cadastro de {{ selected.name }}</md-dialog-title>
				<md-card class="md-layout-item md-size-450 md-small-size-450">
					<md-field>
						<label>Nome do usuário</label>
						<md-input v-model="selected.name"></md-input>
						<span class="md-helper-text">Este deve ser o nome completo do usuário</span>
						<md-button class="md-icon-button" @click="saveUser()">
							<md-icon>save</md-icon>
						</md-button>
					</md-field>

					<div>
						<div class="md-layout">
							<div class="md-layout-item">
								<md-field>
									<label>Email</label>
									<md-input v-model="inputEmail.address"></md-input>
									<span class="md-helper-text">Digite um email válido</span>
								</md-field>
							</div>
							<div class="md-layout-item">
								<md-field>
									<label>Apelido</label>
									<md-input v-model="inputEmail.alias"></md-input>
									<span class="md-helper-text">Crie um apelido pra este endereço</span>
								</md-field>
							</div>
							<md-button class="md-icon-button" @click="addEmail">
								<md-icon>add</md-icon>
							</md-button>
						</div>
						<md-table>
							<md-table-row>
								<md-table-head>apelido</md-table-head>
								<md-table-head>endereço</md-table-head>
								<md-table-head>ativado em</md-table-head>
								<md-table-head>último envio em</md-table-head>
								<md-table-head> - </md-table-head>
							</md-table-row>
							<md-table-row v-for="email in selected.emails">
								<md-table-cell>{{ email.alias }}</md-table-cell>
								<md-table-cell>{{ email.address }}</md-table-cell>
								<md-table-cell>{{ email.activationDate }}</md-table-cell>
								<md-table-cell>{{ email.lastSentValidationDate }}</md-table-cell>
								<md-table-cell>
									<md-button class="md-icon-button" @click="removeEmail(email)">
										<md-icon>remove</md-icon>
									</md-button>
								</md-table-cell>
							</md-table-row>
						</md-table>
					</div>
					<div>
						<div class="md-layout">
							<div class="md-layout-item">
								<md-field>
									<label>Telefone</label>
									<md-input v-model="inputTelephone.number"></md-input>
									<span class="md-helper-text">Informe o seu telefone</span>					      
								</md-field>
							</div>
							<md-button class="md-icon-button" @click="addTelephone">
								<md-icon>add</md-icon>
							</md-button>
						</div>
						<md-table>
							<md-table-row>
								<md-table-head>número</md-table-head>
								<md-table-head>cadastrado em</md-table-head>
								<md-table-head>último contato</md-table-head>
								<md-table-head> - </md-table-head>
							</md-table-row>
							<md-table-row v-for="tel in selected.telephones">
								<md-table-cell>{{ tel.number }}</md-table-cell>
								<md-table-cell>{{ tel.insertionDate }}</md-table-cell>
								<md-table-cell>{{ tel.lastContact }}</md-table-cell>
								<md-table-cell>
									<md-button class="md-icon-button" @click="removeTelephone(tel)">
										<md-icon>remove</md-icon>
									</md-button>
								</md-table-cell>
							</md-table-row>
						</md-table>
					</div>

				</md-card>
			</md-dialog>
			<md-table md-card v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header @md-selected="onSelect">
				<md-table-toolbar>
					<div class="md-toolbar-section-start">
						<h1 class="md-title">Usuários</h1>
						<md-button class="md-primary md-raised" @click="newUser">Criar um novo usuário</md-button>
					</div>
					<md-field md-clearable class="md-toolbar-section-end">
						<md-input placeholder="Busque pelo nome..." v-model="search" @input="searchOnTable" />
					</md-field>
				</md-table-toolbar>

				<md-table-empty-state
				md-label="Nenhum usuário encontrado"
				:md-description="`Nenhum usuário encontrado com o nome '${search}'. Tente buscar usando outros parâmetros ou crie um novo usuário`">
				<md-button class="md-primary md-raised" @click="newUser">Criar um novo usuário</md-button>
			</md-table-empty-state>

			<md-table-row slot="md-table-row" slot-scope="{ item }" md-selectable="single">
				<md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
				<md-table-cell md-label="Nome" md-sort-by="name">{{ item.name }}</md-table-cell>
				<md-table-cell md-label="Emails" md-sort-by="email">{{ sintrgifyEmails(item.emails) }}</md-table-cell>
				<md-table-cell md-label="Telefones" md-sort-by="gender">{{ sintrgifyTelephones(item.telephones) }}</md-table-cell>
				<md-table-cell md-label="Criado em" md-sort-by="title">{{ item.insertionDate }}</md-table-cell>
				<md-table-cell>
						<md-button class="md-icon-button" @click="editRegister(item)" alt="Editar" title="Editar">
							<md-icon>edit</md-icon>
						</md-button>
						<md-button class="md-icon-button" @click="removeRegister(item)"  alt="Apagar" title="Apagar">
							<md-icon>remove</md-icon>
						</md-button>
						<md-button class="md-icon-button" @click="loadUserActivitys(item)" alt="Atividades do usuário" title="Atividades do usuário">
							<md-icon>timeline</md-icon>
						</md-button>
				</md-table-cell>
			</md-table-row>
		</md-table>
	</div>
	<div>
    <md-list class="md-triple-line" v-if="activitys">
    	<div>
	      <md-list-item v-for="act in activitys">
	        <div class="md-list-item-text">
	        	<span>{{ act.insertionDate }}</span>
	        	<p>{{ act.operationDescription }}</p>
	        </div>

	        <div class="md-list-item-text">
	          <p>{{ act.originDetails }}</p>
	        </div>
	        <md-button class="md-icon-button md-list-action" v-if="act.changes">
	          <md-list v-for="change in act.changes">
				      <md-list-item>
				      	<span>{{ change.attribute }}</span>
				      	<p>{{ change.oldValue }} => {{ change.newValue }}</p>
				    </md-list-item>
				    </md-list>
	        </md-button>
	      </md-list-item>
	      <md-divider class="md-inset"></md-divider>    		
    	</div>
    </md-list>
  </div>
</div>
</template>

<style lang="scss" scoped>
.md-field {
	max-width: 300px;
}
.md-table + .md-table {
	margin-top: 16px
}
.md-dialog {
	padding: 5px;
	min-height: 450px;
	min-width: 450px;
}
.md-input {
	width: 100%
}
</style>

<script>
	import UserService from './service/UserService'
	import TelephoneService from './service/TelehoneService'
	var userService;
	var telephoneService;

	const toLower = text => {
		return text.toString().toLowerCase()
	}
	const searchByName = (items, term) => {
		if (term) {
			return items.filter(item => toLower(item.name).includes(toLower(term)))
		}

		return items
	}
	export default {
		name: 'TableSearch',
		data: () => ({
			inputEmail: {
				alias:'',
				address:''
			},
			inputTelephone: {
				number:''
			},
			inputAthentication: {
				authenticationId:'',
				authenticationKey: '',
				type: {
					code: 'EMAIL_PWD'
				}
			},
			search: null,
			selected: {},
			showDialog: false,
			searched: [],
			users: [],
			activitys:[]
		}),
		created () {
			telephoneService = new TelephoneService(this.$resource)
			userService = new UserService(this.$resource)
			userService.findAll()
			.then(res => { 
				this.users = res; 
				this.searched = this.users; 
			}, err => console.log(err));
		},
		methods: {
			loadList(){
				userService.findAll()
				.then(res => { 
					this.users = res;
					this.searched = this.users; 
				 }, err => console.log(err));
			},
			newUser () {
				this.selected = {
					name: '',
					emails: [],
					telephones: [],
					authentications: []
				};
				this.activitys = [];
				this.showDialog = true;
			},
			searchOnTable () {
				this.searched = searchByName(this.users, this.search)
			},
			onSelect (item) {
				if( item ) {
					this.selected = item
				}
			},
			sintrgifyEmails( emails ) {
				let strValue = '';
				if ( emails ) {
					emails.forEach( e => { strValue = strValue + ' ' + e.address });
				}
				return strValue;
			},
			sintrgifyTelephones( telephones ) {
				let strValue = '';
				if ( telephones ) {
					telephones.forEach( e => { strValue = strValue + ' ' + e.number });
				}
				return strValue;
			},
			addEmail() {
				if( this.inputEmail && this.inputEmail.address ) {
					let founded = this.selected.emails.filter( e => e.address === this.inputEmail.address);
					if( ! founded.length ) {
						this.selected.emails.push( this.inputEmail );
					}
					this.inputEmail = { alias:'', address:'' }
				}
			},
			removeEmail(email) {
				if( this.selected ) {
					this.selected.emails = this.selected.emails.filter( e=>e.address!==email.address);
				}
			},
			addTelephone() {
				if( this.selected && this.inputTelephone && this.inputTelephone.number ) {
					let founded = this.selected.telephones.filter( e => e.number === this.inputTelephone.number);
					if( ! founded.length ) {
						this.inputTelephone.user = {id: this.selected.id};
						telephoneService.save(this.inputTelephone)
						.then(res=> {
							this.selected.telephones.push(res);
							this.inputTelephone = { alias:'', number:'' }
						}, e=> console.log(e))
					}
				}
			},
			removeTelephone(tel) {
				if( this.selected ) {
					telephoneService.remove(tel)
					.then(res=> {
							this.selected.telephones = this.selected.telephones.filter( e=>e.number!==tel.number);
						}, e=> console.log(e))
					this.selected.telephones = this.selected.telephones.filter( e=>e.number!==tel.number);
				}
			},
			saveUser() {
				if( this.selected && this.selected.name ) {
					userService.save( this.selected )
					.then( res => this.loadList(), e => console.log(e));
				}
			},
			editRegister(user) {
				this.selected = user;
				this.showDialog = true;
			},
			loadUserActivitys(user){
				this.selected = user;
				userService.findAllActivitys(user)
				.then( res=> {
					this.activitys = res;
				} , e=> console.log(e));
			},
			removeRegister(user) {
				userService.remove(user).then( res => this.loadList(), e => console.log(e));
			}
		}
	}
</script>

<template>
  <div>
  	<md-toolbar class="md-primary">
      <h3 class="md-title">CRUD de usuário</h3>
    </md-toolbar>
    <div>
    	<md-dialog :md-active.sync="showDialog">
				<md-dialog-title>Cadastro de {{ selected.name }}</md-dialog-title>
				<md-card class="md-layout-item md-size-450 md-small-size-450">
					<md-field>
			      <label>Nome do usuário</label>
			      <md-input v-model="selected.name"></md-input>
			      <span class="md-helper-text">Este deve ser o nome completo do usuário</span>
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
	      </md-table-row>
	    </md-table>
    </div>
    <p>Selected:</p>
    {{ selected }}
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
      users: [
        {
          id: 1,
          name: "Shawna Dubbin",
          emails: [{address: "sdubbin0@geocities.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Assistant Media Planner",
          authentications: []
        },
        {
          id: 2,
          name: "Odette Demageard",
          emails: [{address: "odemageard1@spotify.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "Account Coordinator",
          authentications: []
        },
        {
          id: 3,
          name: "Vera Taleworth",
          emails: [{address: "vtaleworth2@google.ca"}],
          telephones: [{number: "Male"}],
          insertionDate: "Community Outreach Specialist",
          authentications: []
        },
        {
          id: 4,
          name: "Lonnie Izkovitz",
          emails: [{address: "lizkovitz3@youtu.be"}],
          telephones: [{number: "Female"}],
          insertionDate: "Operator",
          authentications: []
        },
        {
          id: 5,
          name: "Thatcher Stave",
          emails: [{address: "tstave4@reference.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Software Test Engineer III",
          authentications: []
        },
        {
          id: 6,
          name: "Karim Chipping",
          emails: [{address: "kchipping5@scribd.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "Safety Technician II",
          authentications: []
        },
        {
          id: 7,
          name: "Helge Holyard",
          emails: [{address: "hholyard6@howstuffworks.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "Internal Auditor",
          authentications: []
        },
        {
          id: 8,
          name: "Rod Titterton",
          emails: [{address: "rtitterton7@nydailynews.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Technical Writer",
          authentications: []
        },
        {
          id: 9,
          name: "Gawen Applewhite",
          emails: [{address: "gapplewhite8@reverbnation.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "GIS Technical Architect",
          authentications: []
        },
        {
          id: 10,
          name: "Nero Mulgrew",
          emails: [{address: "nmulgrew9@plala.or.jp"}],
          telephones: [{number: "Female"}],
          insertionDate: "Staff Scientist",
          authentications: []
        },
        {
          id: 11,
          name: "Cybill Rimington",
          emails: [{address: "crimingtona@usnews.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "Assistant Professor",
          authentications: []
        },
        {
          id: 12,
          name: "Maureene Eggleson",
          emails: [{address: "megglesonb@elpais.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Recruiting Manager",
          authentications: []
        },
        {
          id: 13,
          name: "Cortney Caulket",
          emails: [{address: "ccaulketc@cbsnews.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Safety Technician IV",
          authentications: []
        },
        {
          id: 14,
          name: "Selig Swynfen",
          emails: [{address: "sswynfend@cpanel.net"}],
          telephones: [{number: "Female"}],
          insertionDate: "Environmental Specialist",
          authentications: []
        },
        {
          id: 15,
          name: "Ingar Raggles",
          emails: [{address: "iragglese@cbc.ca"}],
          telephones: [{number: "Female"}],
          insertionDate: "VP Sales",
          authentications: []
        },
        {
          id: 16,
          name: "Karmen Mines",
          emails: [{address: "kminesf@topsy.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Administrative Officer",
          authentications: []
        },
        {
          id: 17,
          name: "Salome Judron",
          emails: [{address: "sjudrong@jigsy.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Staff Scientist",
          authentications: []
        },
        {
          id: 18,
          name: "Clarinda Marieton",
          emails: [{address: "cmarietonh@theatlantic.com"}],
          telephones: [{number: "Male"}],
          insertionDate: "Paralegal",
          authentications: []
        },
        {
          id: 19,
          name: "Paxon Lotterington",
          emails: [{address: "plotteringtoni@netvibes.com"}],
          telephones: [{number: "Female"}],
          insertionDate: "Marketing Assistant",
          authentications: []
        },
        {
          id: 20,
          name: "Maura Thoms",
          emails: [{address: "mthomsj@webeden.co.uk"}],
          telephones: [{number: "Male"}],
          insertionDate: "Actuary",
          authentications: []
        }
      ]
    }),
    methods: {
      newUser () {
      	this.selected = {
      		name: '',
      		emails: [],
      		telephones: [],
      		authentications: []
      	};
        this.showDialog = true;
      },
      searchOnTable () {
        this.searched = searchByName(this.users, this.search)
      },
      onSelect (item) {
        this.selected = item
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
      	if( this.inputTelephone && this.inputTelephone.number ) {
      		let founded = this.selected.telephones.filter( e => e.number === this.inputTelephone.number);
      		if( ! founded.length ) {
      			this.selected.telephones.push( this.inputTelephone );
      		}
      		this.inputTelephone = { alias:'', number:'' }
      	}
      },
      removeTelephone(tel) {
      	if( this.selected ) {
      		this.selected.telephones = this.selected.telephones.filter( e=>e.number!==tel.number);
      	}
      }
    },
    created () {
      this.searched = this.users
    }
  }
</script>

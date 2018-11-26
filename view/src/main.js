import Vue from 'vue'
import App from './App2.vue'
//import { MdButton, MdContent, MdTabs } from 'vue-material/dist/components'
import VueMaterial from 'vue-material' //-- nao recomendado importar tudo
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'
import VueResource from 'vue-resource'


Vue.use(VueResource);
Vue.use(VueMaterial) //-- nao recomendado importar tudo

Vue.config.productionTip = false
Vue.http.options.root = 'http://localhost:9000';
Vue.http.headers.common['Access-Control-Allow-Origin'] = '*'

const app = new Vue({
  render: h => h(App),
}).$mount('#app')

export {app}
import Vue from 'vue'
import App from './App2.vue'
//import { MdButton, MdContent, MdTabs } from 'vue-material/dist/components'
import VueMaterial from 'vue-material' //-- nao recomendado importar tudo
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'

//Vue.use(MdButton)
//Vue.use(MdContent)
//Vue.use(MdTabs)

Vue.use(VueMaterial) //-- nao recomendado importar tudo

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')

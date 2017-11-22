import Vue from 'vue'
import App from './App'
import router from './router'
import 'bulma/css/bulma.css'
import ToggleButton from 'vue-js-toggle-button'
import Toasted from 'vue-toasted'
import VeeValidate from 'vee-validate'

Vue.config.productionTip = false
Vue.use(ToggleButton)
Vue.use(Toasted)
Vue.use(VeeValidate)


/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	render: h => h(App)
})

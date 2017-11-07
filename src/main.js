import Vue from 'vue'
import App from './App'
import router from './router'
import 'bulma/css/bulma.css'
import ToggleButton from 'vue-js-toggle-button'
import Toasted from 'vue-toasted';

Vue.config.productionTip = false
Vue.use(ToggleButton)
Vue.use(Toasted)

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	render: h => h(App)
})

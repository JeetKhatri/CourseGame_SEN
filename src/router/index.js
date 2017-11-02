import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import FacultyRegistration from '@/components/FacultyRegistration'

Vue.use(Router)

export default new Router({
	mode: 'history',
	
	routes: [
	{
		path: '/',
		name: 'login',
		component: Login
	},
	{
		path: '/facultyRegistration',
		name: 'facultyRegistration',
		component: FacultyRegistration
	}
	]
})

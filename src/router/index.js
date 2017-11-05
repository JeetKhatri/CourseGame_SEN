import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/containers/Login'
import FacultyRegistration from '@/containers/FacultyRegistration'
import Dashboard from '@/containers/Dashboard'
import Batch from '@/containers/Batch'
import Profile from '@/containers/Profile'
import Settings from '@/containers/Settings'
import QuizQuestions from '@/containers/QuizQuestions'

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
	},
	{
		path: '/dashboard',
		component: Dashboard,
		children: [
		{
			path: '/dashboard',
			name: 'dashboard',
			component: Batch
		},
		{
			path: '/profile',
			name: 'profile',
			component: Profile
		},
		{
			path: '/settings',
			name: 'settings',
			component: Settings
		},
		{
			path: '/quiz-questions',
			name: 'quiz-questions',
			component: QuizQuestions
		}
		]
	}
	]
})

import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/containers/Login'
import FacultyRegistration from '@/containers/FacultyRegistration'
import Dashboard from '@/containers/Dashboard'
import Batch from '@/containers/Batch'
import ViewDetails from '@/containers/ViewDetails'
import Students from '@/containers/Students'
import Ta from '@/containers/Ta'
import GamesLists from '@/containers/GamesLists'
import QuizPlayed from '@/containers/QuizPlayed'
import LeaderBoard from '@/containers/LeaderBoard'
import AdminDashboard from '@/containers/Admin/AdminDashboard'
import Faculties from '@/containers/Admin/Faculties'
import ThankYou from '@/containers/ThankYou'

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
		path: '/thank-you',
		name: 'thank-you',
		component: ThankYou
	},
	{
		path: '/dashboard',
		component: Dashboard,
		children: [
		{
			path: '/dashboard',
			name: 'dashboard',
			component: Batch
		}
		]
	},
	{
		path: '/admin-dashboard', 	
		component: AdminDashboard,
		children: [
		{
			path: '/admin-dashboard',
			name: 'admin-dashboard',
			component: Faculties
		}
		]
	},
	{
		path: '/view-details',
		component: ViewDetails,
		children:[
		{
			path: '/view-details',
			name: 'view-details',
			component: Students
		},
		{
			path: '/ta',
			name: 'ta',
			component: Ta
		},
		{
			path: '/games-lists',
			name: 'games-lists',
			component: GamesLists
		},
		{
			path: '/quiz-played',
			name: 'quiz-played',
			component: QuizPlayed
		},
		{
			path: '/leader-board',
			name: 'leader-board',
			component: LeaderBoard
		}
		]
	}
	]
})

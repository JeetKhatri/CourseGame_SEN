import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/containers/Login'
import FacultyRegistration from '@/containers/FacultyRegistration'
import Dashboard from '@/containers/Dashboard'
import ViewDetails from '@/containers/ViewDetails'
import Students from '@/containers/Students'
import Ta from '@/containers/Ta'
import GamesLists from '@/containers/GamesLists'
import QuizPlayed from '@/containers/QuizPlayed'
import LeaderBoard from '@/containers/LeaderBoard'
import AdminDashboard from '@/containers/Admin/AdminDashboard'
import Faculties from '@/containers/Admin/Faculties'
import ThankYou from '@/containers/ThankYou'
import Statistics from '@/containers/Statistics'
// import TADashboard from '@/containers/TA/TADashboard'
// import Batch from '@/containers/Batch'

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
		name: 'dashboard',
		component: Dashboard,
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
	// {
	// 	path: '/ta-dashboard',
	// 	name: 'ta-dashboard',
	// 	component: TADashboard
	// },
	{
		path: '/view-details/:batchid',
		name: 'view-details',
		component: ViewDetails,
		children:[
		{
			path: '/view-details/:batchid',
			name: 'view-details',
			component: Students
		},
		{
			path: '/ta/:batchid',
			name: 'ta',
			component: Ta
		},
		{
			path: '/games-lists/:batchid',
			name: 'games-lists',
			component: GamesLists
		},
		{
			path: '/quiz-played/:batchid',
			name: 'quiz-played',
			component: QuizPlayed
		},
		{
			path: '/leaderboard',
			name: 'leaderboard',
			component: LeaderBoard
		},
		{
			path: '/:batchid/statistics/:quizid',
			name: 'statistics',
			component: Statistics
		}
		]
	},
	{
		path: '/thank-you',
		name: 'thank-you',
		component: ThankYou
	},
	]
})

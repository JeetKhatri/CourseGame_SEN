<template>
	<div class="dashboard">
		<navbar :role="role"></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column">
					<div class="card main" id="views">
						<div class="field has-addons title">
						</div>
						<div class="columns is-multiline">
							<div class="column is-one-third" v-for="faculty in data">
								<div class="card" id="facultyCard">
									<header class="card-header">
										<p class="card-header-title">
											{{faculty.userName}}
										</p>
									</header>
									<footer class="card-footer">
										<a class="card-footer-item" @click="viewFaculty(faculty)">View</a>
										<a class="card-footer-item" @click="approveFaculty(faculty.facultyId)" v-if="faculty.isApproved=='N'">Approve</a>
										<a class="card-footer-item" @click="loginAs(faculty)" v-if="faculty.isApproved=='Y'">Login As</a>

									</footer>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<viewDetails v-if="viewfaculty" @closeDetails="close"></viewDetails>
	</div>	
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
import navbar from '@/components/Navbar'
import viewDetails from '@/components/ViewFacultyDetailsModal'
export default {
	name: 'dashboard',

	components: {
		viewDetails,
		navbar
	},
	
	data() {
		return {
			viewfaculty: false,
			role: '',
			data: [],
			fetched_faculty_id: '',
			temp:''
		}
	},

	created() {
		this.role = localStorage.getItem('role')
		this.getAllfaculties()
		console.log(this.facultyId)
		localStorage.removeItem("faculty_id")
		localStorage.removeItem("faculty_name")
		localStorage.removeItem("faculty_degree")
	},

	methods: {
		close() {
			this.viewfaculty = false
		},
		getAllfaculties() {
			HTTP.get(`rest/faculty/faculty-list`, {

			})
			.then(response => {
				this.data = response.data.facutlyBeans
			})
			.catch((e) => {
				console.log(e)
			})
		},
		viewFaculty(fac){
			localStorage.setItem("faculty_id",fac.facultyId)
			localStorage.setItem("faculty_name",fac.userName)
			localStorage.setItem("faculty_degree",fac.degree)
			this.viewfaculty = true
		},
		approveFaculty(fid){
			HTTP.get(`rest/user/get-userid?facultyid=`+fid
				,{

				})
			.then(response => {
				if (response.status === 200) {
					
					HTTP.post(`rest/faculty/faculty-approved/?userid=`+response.data.userId
						,{

						})
					.then(response => {
						if (response.status === 200) {
							console.log(response)
							this.getAllfaculties()			

						}
					})
					.catch((e) => {
						console.log(e)
					})
				}
			})
			.catch((e) => {
				console.log(e)
			})

			
		},
		loginAs(fac){
			HTTP.get(`rest/user/get-userid?facultyid=`+fac.facultyId
				,{

				})
			.then(response => {
				if (response.status === 200) {
					localStorage.setItem("faculty_id",response.data.userId)
				}
			})
			.catch((e) => {
				console.log(e)
			})

			localStorage.setItem("faculty_id",this.fetched_faculty_id)
			localStorage.setItem("mainrole","Faculty")
			localStorage.setItem("faculty_name",fac.userName)
			localStorage.setItem("role","Faculty")
			localStorage.removeItem("faculty_degree")
			localStorage.removeItem("admin_id")
			localStorage.removeItem("admin_name")
			localStorage.removeItem("faculty_id")
			localStorage.setItem("faculty_id",this.fetched_faculty_id)

			this.$router.push('/dashboard')
		}
	}
}
</script>

<style lang="scss">
.dashboard {
	.main {
		height: auto;
		top: 4rem;
	}
	.title {
		display: flex;
		justify-content: center;
	}
	.newBatch {
		margin:1rem;
		width: 100%;
	}
	#createBtn {
		margin: 1rem;
	}
	span:hover {
		cursor: pointer;
	}
	#facultyCard {
		box-shadow: 0px 4px 5px #d0cfcf;
	}
	.columns {
		padding-left: 1rem;
		padding-right: 1rem;
	}
}
</style>
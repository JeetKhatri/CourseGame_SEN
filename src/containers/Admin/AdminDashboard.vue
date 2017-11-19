<template>
	<div class="dashboard">
		<navbar :role="role"></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column">
					<div class="card main" id="views">
						<div class="field has-addons title">
							<p class="control is-fullwidth">
								<input class="input newBatch" type="text" placeholder="Add Faculty">
							</p>
							<p class="control">
								<a class="button is-info" id="createBtn" @click=""> Add </a>
							</p>
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
										<a class="card-footer-item" @click="viewfaculty=true">View</a>
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
			data: []
		}
	},

	created() {
		this.role = localStorage.getItem('role')
		this.getAllfaculties()
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
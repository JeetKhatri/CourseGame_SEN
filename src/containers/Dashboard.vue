<template>
	<div class="dashboard">
		<navbar></navbar>
		<div class="container">
			<div class="card main" id="views">
				<div class="columns is-mobile">
					<div class="column">
						<div class="field has-addons title">
							<p class="control is-fullwidth">
								<input class="input newBatch" type="text" v-model="batchName" placeholder="Create New Batch">
							</p>
							<p class="control">
								<a class="button is-info" id="createBtn" @click="batchRegistration()"> Create </a>
							</p>
						</div>
					</div>
				</div>
				<div class="columns is-multiline">
					<div class="column is-one-third" v-for="batch in data">
						<div class="card">
							<header class="card-header">
								<p class="card-header-title">
									{{batch.batchname}}
								</p>
						<!-- <div>
							<span class="tag is-info">Msc.it</span>
						</div> -->
					</header>
					<footer class="card-footer">
						<router-link :to="{name: 'view-details', params:{batchid: batch.batchid}}" class="card-footer-item">View</router-link>
					</footer>
				</div>
			</div>
		</div>
	</div>
</div>
</div>	
</template>

<script type="text/javascript">
import navbar from '@/components/Navbar'
import HTTP from '@/packages/HTTP'
export default {
	name: 'dashboard',
	components:{
		navbar
	},
	data(){
		return {
			data: [],
			faculty_id:'',
			batchName:'',
			// username: ''
		}
	},
	created(){
		this.getBatches()
		this.getId()
	},
	methods:{
		batchRegistration(){
			this.faculty_id=localStorage.getItem('faculty_id');
			HTTP.post(`rest/batch/batch-insert?userid=`+this.faculty_id+`&batchName=
				`+this.batchName,{

				})
			.then(response => {
				if (response.status === 200) {
					let toast = this.$toasted.success('Batch Created Successfully', {
						theme: 'outline',
						position: 'top-center',
						duration: 3000
					});
					this.getBatches()
					this.batchName = ''
				}
			})
			.catch((e) => {
				console.log(e)
			})	
		},
		getBatches() {
			this.faculty_id = localStorage.getItem('faculty_id');
			HTTP.post(`rest/faculty/faculty-batch/?userid=
				`+this.faculty_id,{

				})
			.then(response => {
				if (response.status === 200) {
					this.data =response.data.batchBeans;
				// console.log(this.data);
			}
		})
			.catch((e) => {
				console.log(e)
			})
		},
		getId () {
			var id = window.localStorage.getItem('faculty_id')
			// this.username = id
			if (id != null) {
				this.authToken = id;
				return true
			} else {
				this.$router.push('/')
			}
		}
	}
}
</script>

<style lang="scss">
.dashboard {
	.main{
		height: auto;
		top: 4.5rem;

		.title {
			display: flex;
			justify-content: center;
		}
		.newBatch{
			margin:1rem;
			width: 100%;
		}
		#createBtn {
			margin: 1rem;
		}
		.columns {
			padding-left: 1rem;
			padding-right: 1rem;
		}
	}
}
</style>
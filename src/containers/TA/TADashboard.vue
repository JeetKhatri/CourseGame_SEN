<template>
	<div class="dashboard">
		<navbar :name="name"></navbar>
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
						<div class="card" id="batchCard">
							<header class="card-header">
								<p class="card-header-title">
									{{batch.batchname}}
								</p>
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
			TA_id:'',
			batchName:'',
			name: ''
		}
	},
	created(){
		// this.getBatches()
		this.getTaId()
		this.name = localStorage.getItem('TA_name')
	},
	methods:{
		// batchRegistration(){
		// 	this.faculty_id=localStorage.getItem('faculty_id');
		// 	HTTP.post(`rest/batch/batch-insert?userid=`+this.faculty_id+`&batchName=
		// 		`+this.batchName,{

		// 		})
		// 	.then(response => {
		// 		if (response.status === 200) {
		// 			let toast = this.$toasted.success('Batch Created Successfully', {
		// 				theme: 'outline',
		// 				position: 'top-center',
		// 				duration: 3000
		// 			});
		// 			this.getBatches()
		// 			this.batchName = ''
		// 		}
		// 	})
		// 	.catch((e) => {
		// 		console.log(e)
		// 	})	
		// },
		// getBatches() {
		// 	this.faculty_id = localStorage.getItem('faculty_id');
		// 	HTTP.post(`rest/faculty/faculty-batch/?userid=
		// 		`+this.faculty_id,{

		// 		})
		// 	.then(response => {
		// 		if (response.status === 200) {
		// 			this.data =response.data.batchBeans;
		// 		// console.log(this.data);
		// 	}
		// })
		// 	.catch((e) => {
		// 		console.log(e)
		// 	})
		// },
		getTaId () {
			var id = window.localStorage.getItem('TA_id')
			// this.username = id
			if (id != null) {
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
	font-family: 'Quicksand', sans-serif;

	.main{
		height: auto;
		top: 4.5rem;
	}

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
	#batchCard {
		box-shadow: 0px 4px 5px #d0cfcf;
	}
}
</style>
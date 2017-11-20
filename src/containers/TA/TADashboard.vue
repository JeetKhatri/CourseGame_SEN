<template>
	<div class="dashboard">
		<navbar :role="role"></navbar>
		<div class="container">
			<div class="card main" id="views">
				<div class="columns is-multiline">
					<div class="column is-one-third" v-for="i in 12" :key="i">
						<div class="card" id="batchCard">
							<header class="card-header">
								<p class="card-header-title">
									batch
								</p>
							</header>
							<footer class="card-footer">
								<a class="card-footer-item">View</a>
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
			name: '',
			role: ''
		}
	},
	created(){
		// this.getBatches()
		this.getTaId()
		this.role = localStorage.getItem('role')
		this.name = localStorage.getItem('TA_name')	
		console.log(this.role)
		console.log(this.name)
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
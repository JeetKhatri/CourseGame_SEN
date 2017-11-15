<template>
	<div class="dashboard">
		<navbar></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column">
					<div class="card main" id="views">
						<div class="field has-addons title">
							<p class="control is-fullwidth">
								<input class="input newBatch" type="text" v-model="batchName" placeholder="Create New Batch">
							</p>
							<p class="control">
								<a class="button is-info" id="createBtn" @click="batchRegistration()"> Create </a>
							</p>
						</div>
						<router-view></router-view>
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
	data(){
		return {
			data: [],
			faculty_id:'',
			batchName:''
		}
	},
	methods:{
		batchRegistration(){
			this.faculty_id=localStorage.getItem('faculty_id');
			HTTP.post(`https://coursegame.herokuapp.com/rest/batch/batch-insert?facultyid=`+this.faculty_id+`VKVOSyUMrOE6KEh&batchName=
				`+this.batchName,{

				})
			.then(response => {
				if (response.status === 200) {
					console.log(response.data);

				}
			})
			.catch((e) => {
				console.log(e)
			})	
		}
	},
	components:{
		navbar
	}

}
</script>

<style lang="scss">
.dashboard {
	.main{
		height: auto;
		top: 4rem;

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

	}


}
</style>
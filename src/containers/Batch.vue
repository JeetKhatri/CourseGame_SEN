<template>
	<div class="batch">
		<div class="columns">
			<div class="column" v-for="batch in data">
				<div class="card">
					<header class="card-header">
						<p class="card-header-title">
							Batch 2016-15
						</p>
						<div>
							<span class="tag is-info">Msc.it</span>
						</div>
					</header>
					<footer class="card-footer">
						<router-link :to="{name: 'ViewDetails', params:{batchid: batch.batchid}}" class="card-footer-item">View</router-link>
					</footer>
				</div>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: 'batch',
	data(){
		return {
			data: [],
			faculty_id:''
		}
	},
	created(){
		this.faculty_id=localStorage.getItem('faculty_id');
		HTTP.post(`https://coursegame.herokuapp.com/rest/faculty/faculty-batch/?userid=
			`+this.faculty_id,{

			})
		.then(response => {
			if (response.status === 200) {
				this.data =response.data.batchBeans;
				console.log(this.data);

			}
		})
		.catch((e) => {
			console.log(e)
		})
	},


}
</script>

<style lang="scss">
.batch {
	.columns {
		margin: 0px;
	}
}
</style>
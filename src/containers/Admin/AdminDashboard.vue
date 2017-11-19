<template>
	<div class="dashboard">
		<navbar :adminName="adminName"></navbar>
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
						<router-view></router-view>
					</div>
				</div>
			</div>
		</div>
	</div>	
</template>

<script type="text/javascript">
import navbar from '@/components/AdminNavbar'
export default {
	name: 'dashboard',

	data() {
		return {
			adminName: ''
		}
	},

	created() {
		this.adminName = localStorage.getItem('admin_name')
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
}
</style>
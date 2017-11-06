<template>
	<div class="dashboard">
		<navbar></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column">
					<div class="card main" id="views">
						<div class="field has-addons title">
							<p class="control is-fullwidth">
								<input class="input newBatch" type="text" placeholder="Create New Batch">
							</p>
							<p class="control">
								<a class="button is-success" id="createBtn" @click=""> Create </a>
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
export default {
	name: 'dashboard',

	components:{
		navbar
	}

}
</script>

<style lang="scss">
.dashboard {
	.main{
		height: 40rem;
		top: 0.8rem;

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
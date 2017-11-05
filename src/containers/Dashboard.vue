<template>
	<div class="dashboard">
		<navbar></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column is-one-quarter">
					<sidebar></sidebar>
				</div>
				<div class="column">
					<div class="card main" id="views">
						<router-view></router-view>
					</div>
				</div>
			</div>
		</div>
	</div>	
</template>

<script type="text/javascript">
import navbar from '@/components/Navbar';
import sidebar from '@/components/Sidebar'
export default {
	name: 'dashboard',

	components:{
		navbar,
		sidebar
	}

}
</script>

<style lang="scss">
.dashboard {
	.main{
		height: 40rem;
		top: 0.8rem;
	}
}
</style>
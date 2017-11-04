<template>
	<div class="dashboard">
		<navbar></navbar>
		<div class="container">
			<div class="columns is-multiline is-mobile">
				<div class="column is-one-quarter">
					<sidebar></sidebar>
				</div>
				<div class="column">
					<div class="card main">
						<!-- <router-view></router-view> -->
						<h2>Hello</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import navbar from '@/components/Navbar';
import sidebar from '@/components/sidebar';
export default {
	name: 'dashboard',

	components: {
		navbar,
		sidebar
	}
}
</script>

<style lang="scss">
.dashboard {
	.main {
		min-height: auto;
		max-height: auto;
		height: 40rem;
		top: 0.8rem;
	}


}
</style>

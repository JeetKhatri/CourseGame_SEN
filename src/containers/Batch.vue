<template>
	<div class="batch">
		<div class="columns">
			<div class="column" v-for="i in 3" :key="i">
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
						<router-link to="view" class="card-footer-item">View</router-link>
					</footer>
				</div>
			</div>
		</div>
		<!-- <h2>Hello</h2> -->
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'batch',

}
</script>

<style lang="scss">
.batch {
	.columns {
		margin: 0px;
	}
}
</style>
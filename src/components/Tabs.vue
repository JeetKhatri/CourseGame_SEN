<template>
	<div class="tabs">
		<div class="tabs is-centered">
			<ul>
				<li><router-link to="/view-details">Students</router-link></li>
				<li><router-link to="/ta">TA</router-link></li>
				<li><router-link to="/game-status">Game Status</router-link></li>
				<li><router-link to="/reports">Reports</router-link></li>
			</ul>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'tabs',

}
</script>

<style lang="scss">
.tabs {
	display: flex;
	justify-content: center;
}
</style>
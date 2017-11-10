<template>
	<div class="sidebar">
		<div class="card sidebar">
			<aside class="menu">
				<ul class="menu-list">
					<li><router-link to='/view-details'>Students</router-link></li>
					<li><router-link to='/ta'>TA</router-link></li>
					<li><router-link to='/quiz-played'>Quiz Played</router-link></li>
					<li><router-link to='/games-lists'>Games</router-link></li>
				</ul>
			</aside>
		</div>		
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'sidebar',

}
</script>

<style lang="scss">
.sidebar {
	position: fixed;
	width: 100%;
	.card {
		position: absolute;
		width: 20%;
		top: 3.9rem;
	}
}

</style>
<template>
	<div class="sidebar">
		<div class="card sidebar">
			<aside class="menu">
				<ul class="menu-list">
					<li><router-link to='/dashboard'>Batch</router-link></li>
					<li><router-link to='profile'>Profile</router-link></li>
					<li><router-link to='settings'>Settings</router-link></li>
					<li><router-link to='quiz-questions'>Quiz Questions (gaurav)</router-link></li>
					<li><router-link to='dhiral'>Quiz (dhiral)</router-link></li>
					<li><router-link to='captcha'>Captcha</router-link></li>
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
	.card {
		position: absolute;
		width: 20%;
		top: 1.5rem;
	}
}

</style>
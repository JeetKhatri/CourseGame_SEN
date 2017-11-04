<template>
	<div class="navigation-bar">
		<nav class="navbar is-info">
			<div class="navbar-brand">
				<a class="navbar-item">
					<img src="../assets/courseGame.png" alt="course-game" width="40" height="28">Course-Game
				</a>
				<div class="navbar-burger burger" data-target="navMenuTransparentExample">
					<span></span>
					<span></span>
					<span></span>
				</div>
			</div>
			<div id="navMenuTransparentExample" class="navbar-menu">
				<div class="navbar-end">
					<div class="navbar-item">
						<div class="field is-grouped">
							<p class="control">
								<div class="navbar-item has-dropdown is-hoverable">
									<div class="navbar-link">
										Username
									</div>
									<div id="moreDropdown" class="navbar-dropdown is-boxed">
										<a class="navbar-item">Logout</a>
										<hr class="navbar-divider">
										<a class="navbar-item">Account Settings</a>
									</div>
								</div>
							</p>
						</div>
					</div>
				</div>
			</div>
		</nav>		
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'navbar',

	data() {
		return {
			isActive: false
		}
	},

	methods: {
		toggle() {
			this.isActive = true
		}
	}

}
</script>

<style type="text/css">

</style>
<template>
	<div class="navigation-bar">
		<nav class="navbar is-info">
			<div class="navbar-brand">
				<a class="navbar-item">
					<img src="../assets/courseGame.png" alt="course-game" width="40" height="28">Course-Game
				</a>
				<router-link to="/admin-dashboard" class="navbar-item">Home</router-link>
				<div class="navbar-burger burger" data-target="navMenuTransparentExample">
					<span></span>
					<span></span>
					<span></span>
				</div>
			</div>
			<div id="navMenuTransparentExample" class="navbar-menu">
				<div class="navbar-end">
					<div class="navbar-item">
						<div class="field is-grouped" id="menus">
							<p class="control">
								<div class="navbar-item has-dropdown is-hoverable">
									<div class="navbar-link">
										{{adminName}}
									</div>
									<div id="moreDropdown" class="navbar-dropdown is-boxed">
										<a class="navbar-item" @click="logout">Logout</a>
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

	props: {
		adminName: {
			required: true
		}
	},
	data() {
		return {
			isActive: false
		}
	},
	methods: {
		toggle() {
			this.isActive = true
		},
		logout() {
			localStorage.removeItem('admin_id')
			localStorage.removeItem('admin_name')
			this.$router.push('/')
		}
	}
}
</script>

<style lang="scss">
.navigation-bar {
	position: fixed;
	width: 100%;
	z-index: 2;

	#menus {
		margin-right: 2rem;
	}
	.navbar-item {
		padding-bottom: 0px;
	}

	.navbar {
		height: 60px;
	}
}
</style>
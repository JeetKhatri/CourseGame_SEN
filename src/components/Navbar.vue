<template>
	<div class="navigation-bar">
		<nav class="navbar is-info">
			<div class="navbar-brand">
				<a class="navbar-item">
					<img src="../assets/courseGame.png" alt="course-game" width="40" height="28">Course-Game
				</a>
				<router-link to="/dashboard" class="navbar-item">Home</router-link>
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
										 {{ name }}
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
		name: {
			required:true
		}
	},

	data() {
		return {
			isActive: false
		}
	},
	// bHHbg9igB6lHnRL

	methods: {
		toggle() {
			this.isActive = true
		},
		logout() {
			localStorage.removeItem('faculty_id')
			this.$router.push('/')
		}
	}

}
</script>

<style lang="scss">
.navigation-bar {
	position: fixed;
	width: 100%;
	height: 100px;
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
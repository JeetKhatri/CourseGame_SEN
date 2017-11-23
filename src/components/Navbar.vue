<template>
	<div class="navigation-bar">
		<nav class="navbar is-info">
			<div class="navbar-brand">
				<router-link to="/dashboard" class="navbar-item">
					<img src="../assets/courseGame.png" alt="course-game" width="40" height="28">Course-Game
				</router-link>
				<!-- <router-link to="/dashboard" class="navbar-item">Home</router-link> -->
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
										<a class="navbar-item" @click="change=true">Change Password</a>
									</div>
								</div>
							</p>
						</div>
					</div>
				</div>
			</div>
		</nav>	
		<changePassword v-if="change"></changePassword>	
	</div>
</template>

<script type="text/javascript">
import changePassword from '@/components/changePassword'
export default {
	name: 'navbar',
	props: {
		role: {
			required:true
		}
	},

	components: {
		changePassword
	},

	data() {
		return {
			name: '',
			change: false
		}
	},

	created() {
		this.getNames()
	},

	methods: {
		logout() {
			if(this.role == 'Faculty') {
				localStorage.removeItem('faculty_id')
				localStorage.removeItem('faculty_name')
				localStorage.removeItem('role')
				this.$router.push('/')
				let toast = this.$toasted.success('You have successfully logged out', {
					theme: 'outline',
					position: 'top-center',
					duration: 3000
				});
			} else if(this.role == 'Admin') {
				localStorage.removeItem('admin_id')
				localStorage.removeItem('admin_name')
				localStorage.removeItem('role')
				this.$router.push('/')
				let toast = this.$toasted.success('You have successfully logged out', {
					theme: 'outline',
					position: 'top-center',
					duration: 3000
				});
			} else if(this.role == 'TA') {
				localStorage.removeItem('TA_id')
				localStorage.removeItem('TA_name')
				localStorage.removeItem('role')
				this.$router.push('/')
				let toast = this.$toasted.success('You have successfully logged out', {
					theme: 'outline',
					position: 'top-center',
					duration: 3000
				});
			}
		},
		getNames() {
			if(this.role == 'Faculty') {
				this.name = localStorage.getItem('faculty_name')
			} else if(this.role == 'Admin') {
				this.name = localStorage.getItem('admin_name')
			} else if(this.role == 'TA') {
				this.name = localStorage.getItem('TA_name')
			}
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

	.navbar-brand {
		padding-left: 5rem;
	}

	.navbar-item {
		padding-bottom: 0px;
		padding-right: 2.2rem;
	}

	.navbar.is-info {
		height: 60px;
	}

	.navbar-link {
		pading-right: 1rem;
	}
}
</style>
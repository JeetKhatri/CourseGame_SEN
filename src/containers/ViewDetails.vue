<template>
	<div class="view-details">
		<navbar></navbar>
		<div class="container">
			<div class="columns">
				<div class="column is-one-quarter">
					<sidebar></sidebar>
				</div>
				<div class="column">
					<div class="card main">
						<router-view></router-view>
					</div>
				</div>
			</div>
			<!-- <tabs></tabs> -->
			<!-- <router-view></router-view> -->
		</div>
	</div>
</template>

<script type="text/javascript">
import navbar from '@/components/Navbar';
import sidebar from '@/components/Sidebar';
import tabs from '@/components/Tabs';
export default {
	name: 'view-details',
	components: {
		navbar,
		tabs,
		sidebar
	},

	created() {
		this.getId()
	},

	methods: {
		getId () {
			var id = window.localStorage.getItem('faculty_id')
			if (id != null) {
				this.authToken = id;
				return true
			} else {
				this.$router.push('/')
			}
		}
	}

}
</script>


<style lang="scss">
.view-details {
	.main {
		margin-top: 4.5rem;
		height: auto;
	}
}
</style>
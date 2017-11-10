<template>
	<div class="faculties">
		<div class="columns">
			<div class="column" v-for="i in 3" :key="i">
				<div class="card">
					<header class="card-header">
						<p class="card-header-title">
							Faculty Name
						</p>
					</header>
					<footer class="card-footer">
						<span class="card-footer-item" id="view" @click="viewfaculty=true">View</span>
					</footer>
				</div>
			</div>
		</div>
		<viewDetails v-if="viewfaculty" @closeDetails="close"></viewDetails>
	</div>
</template>

<script type="text/javascript">
import viewDetails from '@/components/ViewFacultyDetailsModal';
export default {
	name: 'faculties',

	data() {
		return {
			viewfaculty: false
		}
	},

	components: {
		viewDetails
	},

	methods: {
		close() {
			this.viewfaculty = false
		}
	}

}
</script>

<style lang="scss">
.faculties {
	.columns {
		margin: 0px;
	}

	span:hover {
		cursor: pointer;
	}
}
</style>
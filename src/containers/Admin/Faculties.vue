<template>
	<div class="faculties">
		<div class="columns">
			<div class="column" v-for="i in 3" :key="i">
				<div class="card" id="facultyCard">
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
import HTTP from '@/packages/HTTP';
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
		},
		getAllfaculties() {
			HTTP.get(`rest/faculty/faculty-list`, {

			})
			.then(response => {
				console.log(response)
			})
			.catch((e) => {
				console.log(e)
			})
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
	#facultyCard{
		box-shadow: 0px 4px 5px #d0cfcf;
	}
}
</style>
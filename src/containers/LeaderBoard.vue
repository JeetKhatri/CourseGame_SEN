<template>
	<div class="leaderboard">
		<h2>Welcome to Leaderboard</h2>
		<div class="columns" id="lists" v-for="i in 20" :key="i">
			<div class="column">
				<h4>201612065</h4>
			</div>
			<div class="column">
				<h4>Students</h4>
			</div>
			<div class="column">
				<h4>Marks</h4>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'leaderboard',

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
.leaderboard {
	.columns {
		margin: 0px;
	}
	#lists {
		padding: 0.1rem;
		padding-left: 5rem;
	}
	span:hover {
		cursor: pointer;
	}
	h2 {
		font-size: 24px;
		text-decoration: underline;
		display: flex;
		justify-content: center;
		padding-bottom: 0.5rem;
	}
}
</style>
<template>
	<div class="games-played">
		<h2>List of Games</h2>
		<div class="columns" id="lists" v-for="i in 10" :key="i">
			<div class="column">
				<h4>Games Lists</h4>
			</div>
			<div class="column">
				<span class="tag is-info">Allow</span>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: 'games-played',

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
.games-played {
	font-family: 'Quicksand', sans-serif;
	
	h2 {
		font-size: 20px;
		text-decoration: underline;
		display: flex;
		justify-content: center;
		padding-bottom: 0.5rem;
	}
	.columns {
		margin: 0px;
	}
	.column {
		padding-left: 8rem;
	}
	#lists {
		padding: 0.1rem;
	}
	span:hover {
		cursor: pointer;
	}
}
</style>
<template>
	<div class="games-played">
		<h2>List of Games</h2>
		<div class="columns" id="lists" v-for="i in 10" :key="i">
			<div class="column">
				<h4>Games Lists</h4>
			</div>
			<div class="column">
				<span class="tag is-info">Allowed</span>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'games-played',


}
</script>

<style lang="scss">
.games-played {
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
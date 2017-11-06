<template>
	<div class="ta">
		<h2>List of Teaching Assistants</h2>
		<div class="columns" id="lists" v-for="i in 10" :key="i">
			<div class="column">
				<h4>201612065</h4>
			</div>
			<div class="column">
				<h4>TA Abc</h4>
			</div>
			<div class="column">
				<span class="tag is-info">Remove</span>
			</div>
		</div>	
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'ta',

}
</script>

<style lang="scss">
.ta {
	.columns {
		margin: 0px;
	}
	#lists {
		padding: 0.1rem;
	}

	span:hover {
		cursor: pointer;
	}

	h2 {
		font-size: 20px;
		text-decoration: underline;
		display: flex;
		justify-content: center;
		padding-bottom: 0.5rem;
	}
}
</style>
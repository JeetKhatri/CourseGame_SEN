<template>
	<div class="ta">
		<div>
			<div class="column" id="button">
				<button class="button is-info" @click="addTa=true">Add New</button>
			</div>
			<div class="column" id="quiz">
				<h2>Teaching Assistants</h2>
			</div>
		</div>
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
		<addTa v-if="addTa" @closeAddTa="close"></addTa>
	</div>
</template>

<script type="text/javascript">
import addTa from '@/components/AddNewTaModal';
export default {
	name: 'ta',
	components: {
		addTa
	},
	
	data() {
		return{
			addTa: false
		}
	},

	methods: {
		close() {
			this.addTa = false
		}
	}

}
</script>

<style lang="scss">
.ta {
	.columns {
		margin: 0px;
	}
	#button {
		float: right;
		padding: 0px;
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
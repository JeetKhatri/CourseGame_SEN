<template>
	<div class="students">
		<div>
			<div class="column" id="button">
				<button class="button is-info" @click="addNewStudent=true">Add New</button>
			</div>
			<div class="column">
				<h2>List Of Students</h2>
			</div>
		</div>
		<div class="columns" id="lists" v-for="i in 20" :key="i">
			<div class="column">
				<h4>201612065</h4>
			</div>
			<div class="column">
				<h4>Students</h4>
			</div>
			<div class="column">
				<span class="tag is-info">Remove</span>
			</div>
		</div>
		<addStudent v-if="addNewStudent" @closeAddStudent="close"></addStudent>
	</div>
</template>

<script type="text/javascript">
import addStudent from '@/components/AddNewStudentModal';
export default {
	name: 'students',
	components: {
		addStudent
	},

	data() {
		return{
			addNewStudent:false
		}
	},

	methods: {
		close() {
			this.addNewStudent = false
		}
	}
}
</script>

<style lang="scss">
.students {
	.columns {
		margin: 0px;
	}

	#button {
		float: right;
		padding: 0px;
	}
	#button:hover {
		cursor: pointer;
	}
	.column {

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
		padding-left: 5rem;
	}
}
</style>
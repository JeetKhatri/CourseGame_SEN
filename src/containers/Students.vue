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
		<div class="columns" id="lists" v-for="student in data">
			<div class="column">
				<h4>{{student.emailId}}</h4>
			</div>
			<div class="column">
				<h4>{{student.userName}}</h4>
			</div>
			<div class="column">
				<span class="tag is-info" @click="removeStudent(student.userId)">Remove</span>
			</div>
		</div>
		<addStudent v-if="addNewStudent" @closeAddStudent="close"></addStudent>
	</div>
</template>

<script type="text/javascript">
import addStudent from '@/components/AddNewStudentModal';
import HTTP from '@/packages/HTTP'
export default {
	name: 'students',
	data(){
		return{
			batchid: '',
			addNewStudent:false,
			data: []
		}
	},
	components: {
		addStudent
	},
	created(){
		this.getStudents();
	},
	methods: {
		close() {
			this.addNewStudent = false
		},
		removeStudent(id){
			console.log(id)
			HTTP.post(`https://coursegame.herokuapp.com/rest/student/student-remove?userid=`+id,{

				})
			.then(response => {
				if (response.status === 200) {
					this.getStudents();
				}
			})
			.catch((e) => {
				console.log(e)
			})
		},
		getStudents(){
		this.batchid =this.$route.params.batchid,
		HTTP.get(`https://coursegame.herokuapp.com/rest/student/student-list?batchid=`+this.batchid)
		.then(response => {
			this.data = response.data.studentBeans;
			console.log(this.data)
		})
		.catch(e=>{
			console.log(e);
		})	
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
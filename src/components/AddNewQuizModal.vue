<template>
	<div class="add-new-quiz">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add New Quiz</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" v-model="quiz_name" type="text" placeholder="Quiz Name">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="addQuestions()">Add Questions</a>
					<a class="button is-info" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: 'add-new-quiz',
	data(){
		return{
			quiz_name:'',
			batchid:'',
			created_by_id:''
		}
	},
	created(){
		this.batchid = this.$route.params.batchid;

	},
	methods: {
		addQuestions() {
			this.created_by_id = localStorage.getItem('faculty_id');
			HTTP.post(`rest/quiz/quiz-insert?name=`+this.quiz_name+`&batchid=`+this.batchid+`&starttime=1996-08-28&endtime=1996-08-28&createdby=`+this.created_by_id,{

			})
			.then(response => {
				if (response.status === 200) {
					let toast = this.$toasted.success('Quiz Created', {
						theme: 'outline',
						position: 'top-center',
						duration: 3000
					});
					this.getAllQuiz()
					this.$emit('closeAddNewQuiz');
				}
			})
			.catch((e) => {
				console.log(e)
			})
		},
		close() {
			this.$emit('closeAddNewQuiz');
		},
		getAllQuiz() {
			this.batchid=this.$route.params.batchid;
			HTTP.post(`rest/batch/batch-quiz/?batchid=
				`+this.batchid,{

				})
			.then(response => {
				if (response.status === 200) {
					this.data =response.data.quizBeans;
					console.log(this.data);

				}
			})
			.catch((e) => {
				console.log(e)
			})
		}
	}
}
</script>

<style lang="scss">
.add-new-quiz {
	
}
</style>
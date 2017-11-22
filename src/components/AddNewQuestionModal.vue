<template>
	<div class="add-new-quiz">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add Question For Quiz</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<textarea :class="{'textarea': true, 'is-danger': errors.has('Question') }" name="Question" v-model="question" placeholder="Enter question for quiz" v-validate="'required'"></textarea>
						<br>
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Option A') }" name="Option A" type="text" v-model="option1" placeholder="Option A" v-validate="'required'">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Option B') }" name="Option B" v-model="option2" type="text" placeholder="Option B" v-validate="'required'">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Option C') }" name="Option C" type="text" v-model="option3" placeholder="Option c" v-validate="'required'">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Option D') }" name="Option D" type="text" v-model="option4" placeholder="Option D" v-validate="'required'">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="columns">
						<div class="column" id="correctAnswer">
							<input :class="{'input': true, 'is-danger': errors.has('Correct Answer') }" type="text" name="Correct Answer" v-model="correctAnswer" placeholder="Correct Answer" v-validate="'required'">
						</div>
						<!-- <div class="notification is-danger" v-show="errors.has('Correct Answer')">
							<span>{{ errors.first('Correct Answer') }}</span>
						</div> -->
						<div class="column" id="correctAnswer">
							<input class="input" type="number" name="Correct Marks" v-model="correctMarks" placeholder="Marks" >
						</div>
						<!-- <div class="notification is-danger" v-show="errors.has('Correct Marks')">
							<span>{{ errors.first('Correct Marks') }}</span>
						</div> -->
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="addQuestion()">Save</a>
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
			question:'',
			option1:'',
			option2:'',
			option3:'',
			option4:'',
			correctAnswer:'',
			correctMarks:1,
			difficulty:1,
			quizId:''
		}
	},
	created(){
		this.quizId = this.$route.params.quizid
	},
	methods: {
		validate() {
			return this.$validator.validateAll()
		},
		close() {
			this.getQuestions()
			this.$emit('closeAddNewQuestion');
		},
		addQuestion(){
			if(this.validate()) {
				console.log("Error")
			} else {
				HTTP.post(`https://coursegame.herokuapp.com/rest/quiz-content/quiz-content-insert?quizid=`+this.quizId+`&question=`+this.question+`&option1=`+this.option1+`&option2=`+this.option2+`&option3=`+this.option3+`&option4=`+this.option4+`&answer=`+this.correctAnswer+`&mark=`+this.correctMarks+`&difficulty=`+this.difficulty,{

				})
				.then(response => {
					if (response.status === 200) {
						let toast = this.$toasted.success('Question Saved', {
							theme: 'outline',
							position: 'top-center',
							duration: 3000
						});
						this.getQuestions()
						this.question = ''
						this.option1 = ''
						this.option2 = ''
						this.option3 = ''
						this.option4 = ''
						this.correctAnswer = ''
						this.correctMarks = ''
					}
				})
				.catch((e) => {
					console.log(e)
				})
			}
		},
		getQuestions() {
			HTTP.get(`rest/quiz-content/questions-list?quizid=
				`+this.quizId, {

				})
			.then(response => {
				this.data = response.data.quizcontent
				console.log(this.data)
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
	font-family: 'Quicksand', sans-serif;
}
</style>
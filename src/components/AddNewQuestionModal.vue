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
						<textarea class="textarea" v-model="question" placeholder="Enter question for quiz"></textarea>
						<br>
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" v-model="option1" placeholder="Option A">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" v-model="option2" type="text" placeholder="Option B">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" v-model="option3" placeholder="Option c">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" v-model="option4" placeholder="Option D">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="column" id="correctAnswer">
						<input class="input" type="text" v-model="correctAnswer" placeholder="Correct Answer">
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="addQuestion()">Save</a>
					<a class="button is-info" @click="addQuestion()">Add New</a>
					<a class="button is-info" id="closeBtn" @click="close">Close</a>
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
			correctAnswer:''
		}
	},
	methods: {
		close() {
			this.$emit('closeAddNewQuestion');
		},
		addQuestion(){
			// HTTP.post(`https://coursegame.herokuapp.com/rest/quiz-content/quiz-content-insert?quizid=Eq2ozSiSi22jPuP&question=Hello&option1=A&option2=B&option3=C&option4=D&answer=A&mark=5&difficulty=1`,{

			// })
			// .then(response => {
			// 	if (response.status === 200) {
			// 		this.$emit('openAddNewQuestion');
			// 	}
			// })
			// .catch((e) => {
			// 	console.log(e)
			// })

		}

	}

}
</script>

<style lang="scss">
.add-new-quiz {
	font-family: 'Quicksand', sans-serif;
}
</style>
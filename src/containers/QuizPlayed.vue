<template>
	<div class="quiz-played">
		<div>
			<div class="column" id="button">
				<button class="button is-info" @click="addNewQuiz=true">Add New</button>
			</div>
			<div class="column" id="quiz">
				<h2>Quiz</h2>
			</div>
		</div>
		<div>
			<div class="columns is-multiline">
				<div class="column is-one-third" v-for="quiz in data">
					<div class="card" id="quizCard">
						<header class="card-header">
							<p class="card-header-title">
								{{quiz.name}}
							</p>
							<div>
								<span class="tag is-info">{{quiz.createdBy}}</span>
							</div>
						</header>
						<footer class="card-footer">
							<router-link class="card-footer-item" :to="{name: 'statistics', params:{quizid: quiz.quizId }}">View</router-link>
							<a class="card-footer-item" @click="startQuiz(quiz.quizId)" v-if="quiz.status=='N'">Start</a>
						</footer>
					</div>
				</div>
			</div>
		</div>
		<addNewQuiz @closeAddNewQuiz="closeAddNewQuiz" v-if="addNewQuiz"></addNewQuiz>
	</div>
</template>

<script type="text/javascript">
import addNewQuiz from '@/components/AddNewQuizModal';
import HTTP from '@/packages/HTTP';
export default {
	name: 'quiz-played',
	components: {
		addNewQuiz
	},

	data() {
		return {
			statistics: false,
			addNewQuiz: false,
			addquestion: false,
			batchid: '',
			data:  [],
			status: ''
		}
	},
	created(){
		this.getAllQuiz()
		this.getId()
	},

	methods:  {
		closeStatistics() {
			this.statistics = false;
		},

		closeAddNewQuiz() {
			this.addNewQuiz = false;
			this.getAllQuiz()
		},

		closeAddNewQuestion() {
			this.addquestion = false;
		},
		getAllQuiz() {
			this.batchid=this.$route.params.batchid;
			HTTP.post(`rest/batch/batch-quiz/?batchid=
				`+this.batchid,{
					
				})
			.then(response => {
				if (response.status === 200) {
					this.data =response.data.quizBeans;

				}
			})
			.catch((e) => {
				console.log(e)
			})
		},
		startQuiz(quizid) {
			this.batchid=this.$route.params.batchid;
			HTTP.post(`rest/quiz/quiz-activation/?batchid=`+this.batchid+`&quizid=`+quizid, {
				
			})
			.then(response => {
				if (response.status === 200) {
					this.getAllQuiz()
				}
			})
			.catch((e) => {
				console.log(e)
			})
		},
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
.quiz-played {
	font-family: 'Quicksand', sans-serif;
	
	.columns {
		margin: 0px;
	}
	h2 {
		font-size: 24px;
		text-decoration: underline;
		display: flex;
		justify-content: center;
		padding-bottom: 0.5rem;
	}
	#button {
		float: right;
	}

	#correctAnswer {
		display: flex;
		justify-content: center;
	}
	#quizCard {
		box-shadow: 0px 4px 5px #d0cfcf;
	}
}
</style>
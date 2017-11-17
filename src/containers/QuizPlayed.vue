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
					<div class="card">
						<header class="card-header">
							<p class="card-header-title">
								{{quiz.name}}
							</p>
							<div>
								<span class="tag is-info">{{quiz.createdBy}}</span>
							</div>
						</header>
						<footer class="card-footer">
							<router-link class="card-footer-item" to="/statistics">View</router-link>
							<a class="card-footer-item" @click="startQuiz">Start</a>
						</footer>
					</div>
				</div>
			</div>
		</div>
		<!-- <statistics @closeStatistics="closeStatistics" v-if="statistics"></statistics> -->
		<addNewQuiz @closeAddNewQuiz="closeAddNewQuiz" v-if="addNewQuiz"></addNewQuiz>
		<!-- <addQuestion @closeAddNewQuestion="closeAddNewQuestion" v-if="addquestion"></addQuestion> -->
	</div>
</template>

<script type="text/javascript">
// import statistics from '@/components/StatisticsModal';
import addNewQuiz from '@/components/AddNewQuizModal';
// import addQuestion from '@/components/AddNewQuestionModal'
import HTTP from '@/packages/HTTP';
export default {
	name: 'quiz-played',
	components: {
		// statistics,
		addNewQuiz
		// addQuestion
	},

	data() {
		return {
			statistics: false,
			addNewQuiz: false,
			addquestion: false,
			batchid: '',
			data:  []
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
			// this.addquestion = true;
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
					console.log(this.data);

				}
			})
			.catch((e) => {
				console.log(e)
			})
		},
		startQuiz() {
			alert('started')
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
}
</style>
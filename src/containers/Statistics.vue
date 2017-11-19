<template>
	<div class="statistics">
		<div>
			<div class="column" id="tags">
				<span class="tag is-info" @click="addQuestion=true">Add Questions</span>
				<router-link class="tag is-info" to="/leaderboard">View Leaderboard</router-link>
			</div>
			<div class="column">
				<h2>Manage Quiz</h2>
			</div>
		</div>
		<div class="columns" id="lists" v-for="content in data">
			<div class="column">
				{{content.question}}
			</div>
			<div class="column">
				{{content.answer}}
			</div>
			<div class="column">
				<span class="tag is-info">Update</span>
			</div>
		</div>
		<addQuestion @closeAddNewQuestion="close" v-if="addQuestion"></addQuestion>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP';
import addQuestion from '@/components/AddNewQuestionModal'
export default {
	name: 'statistics',
	data() {
		return {
			quizId: ''
		}
	},
	components: {
		addQuestion
	},

	created() {
		this.getId()
		this.quizId = this.$route.params.quizid
		console.log(this.quizId)
		this.getQuestions()
	},

	data() {
		return {
			addQuestion: false,
			data: []
		}
	},

	methods: {
		close() {
			this.addQuestion = false
		},
		getId () {
			var id = window.localStorage.getItem('faculty_id')
			if (id != null) {
				this.authToken = id;
				return true
			} else {
				this.$router.push('/')
			}
		},
		getQuestions() {
			HTTP.get(`rest/quiz-content/questions-list?quizid=
				`+this.quizId, {

				}).then(response => {
					this.data = response.data.quizcontent
					console.log(this.data)
				}).catch((e) => {
					console.log(e)
				})
			}
		}
	}
	</script>

	<style lang="scss">
	.statistics {
		font-family: 'Quicksand', sans-serif;

		#tags {
			float: right;
			font-size: 16px;
		}
		.columns {
			margin: 0px;
		}
		#tags:hover {
			cursor: pointer;
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
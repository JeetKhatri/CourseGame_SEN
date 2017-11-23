<template>
	<div class="statistics">
		<div>
			<div class="column" id="tags">
				<span class="tag is-info" @click="addQuestion=true">Add Questions</span>
				<router-link class="tag is-info" :to="{name: 'leaderboard'}">View Leaderboard</router-link>
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
				<span class="tag is-info" @click="show(content.quizContentId)">Update</span>
			</div>
		</div>
		<addQuestion @closeAddNewQuestion="close" v-if="addQuestion"></addQuestion>
		<updateQuestion @closeUpdateQuestion="closeUpdate" v-if="update"></updateQuestion>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP';
import addQuestion from '@/components/AddNewQuestionModal'
import updateQuestion from '@/components/UpdateQuestionModal'
export default {
	name: 'statistics',
	data() {
		return {
			quizId: '',
			batchId: '',
			addQuestion: false,
			update: false,
			data: []
		}
	},
	components: {
		addQuestion,
		updateQuestion
	},

	created() {
		this.getId()
		this.quizId = this.$route.params.quizid
		this.getQuestions()
		localStorage.removeItem('quizContentId')
	},

	methods: {
		close() {
			this.addQuestion = false
			this.getQuestions()
		},
		closeUpdate() {
			this.update = false
			this.getQuestions()
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
				}).catch((e) => {
					console.log(e)
				})
			},
			show(quizContentId)
			{
				this.update = true
				localStorage.setItem('quizContentId',quizContentId)
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
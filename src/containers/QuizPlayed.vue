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
			<div class="columns">
				<div class="column" v-for="i in 3" :key="i">
					<div class="card">
						<header class="card-header">
							<p class="card-header-title">
								Quiz 1
							</p>
							<div>
								<span class="tag is-info">Msc.it</span>
							</div>
						</header>
						<footer class="card-footer">
							<a class="card-footer-item" @click="statistics = true">View</a>
						</footer>
					</div>
				</div>
			</div>
		</div>
		<statistics @closeStatistics="closeStatistics" v-if="statistics"></statistics>
		<addNewQuiz @closeAddNewQuiz="closeAddNewQuiz" v-if="addNewQuiz"></addNewQuiz>
	</div>
</template>

<script type="text/javascript">
import statistics from '@/components/StatisticsModal';
import addNewQuiz from '@/components/AddNewQuizModal';
export default {
	name: 'quiz-played',
	components: {
		statistics,
		addNewQuiz
	},

	data() {
		return {
			statistics: false,
			addNewQuiz: false
		}
	},

	methods:  {
		closeStatistics() {
			this.statistics = false;
		},

		closeAddNewQuiz() {
			this.addNewQuiz = false;
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
		padding: 0px;
	}

	#correctAnswer {
		display: flex;
		justify-content: center;
	}
}
</style>
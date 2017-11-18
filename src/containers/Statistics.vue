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
		<div class="columns" id="lists" v-for="i in 10" :key="i">
			<div class="column">
				Question
			</div>
			<div class="column">
				Answer
			</div>
			<div class="column">
				<span class="tag is-info">Update</span>
			</div>
		</div>
		<addQuestion @closeAddNewQuestion="close" v-if="addQuestion"></addQuestion>
	</div>
</template>

<script type="text/javascript">
import addQuestion from '@/components/AddNewQuestionModal'
export default {
	name: 'statistics',
	components: {
		addQuestion
	},

	created() {
		this.getId()
	},

	data() {
		return {
			addQuestion: false
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
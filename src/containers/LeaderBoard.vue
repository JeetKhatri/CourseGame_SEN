<template>
	<div class="leaderboard">
		<h2>Leaderboard</h2>
		<div class="columns" id="lists" v-for="i in lb">
			<div class="column">
				
				<h4>{{i.emailid}}</h4>
				
			</div>
			<div class="column">
				<h4>{{i.studentname}}</h4>
			</div>
			<div class="column">
				<h4>{{i.marks}}</h4>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: 'leaderboard',
	data(){
		return{
			quizId:'',
			batchId:'',
			lb:[]
		}
	},
	created(){
		this.quizId = this.$route.params.quizid
		this.batchId = this.$route.params.batchid
		this.getStatistics()
	},
	methods: {
		getId () {
			var id = window.localStorage.getItem('faculty_id')
			if (id != null) {
				this.authToken = id;
				return true
			} else {
				this.$router.push('/')
			}
		},
		getStatistics(){
			HTTP.get(`https://coursegame.herokuapp.com/rest/quiz-content/batch-wise-result?quizid=`+this.quizId+`&batchid=`+this.batchId,{

			})
			.then(response => {
				if (response.status === 200) {
					
					this.lb = response.data.marklist
			
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
.leaderboard {
	font-family: 'Quicksand', sans-serif;
	
	.columns {
		margin: 0px;
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
		padding-bottom: 0.5rem;
	}
}
</style>
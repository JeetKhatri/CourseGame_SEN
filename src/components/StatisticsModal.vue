<template>
	<div class="statistics-modal">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Student Statistics</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="view">
						<button class="button is-info" @click="leaderboard">Start</button>
						<!-- <button class="button is-info" @click="leaderboard">View Leaderboard</button> -->
					</div>
					<div class="columns" id="lists" v-for="i in 20" :key="i">
						<div class="column">
							<h4>Question</h4>
						</div>
						<div class="column">
							<h4>Answer</h4>
						</div>
						<div class="column">
							<span class="tag is-info">Update</span>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button close-btn" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'statistics-modal',

	methods: {
		close() {
			this.$emit('closeStatistics');
		},
		leaderboard() {
			this.$router.push('/leader-board');
		}
	}

}
</script>

<style lang="scss">
.statistics-modal {
	.view {
		padding-left: 33rem;
	}
	span {
		cursor: pointer;
	}
}
</style>
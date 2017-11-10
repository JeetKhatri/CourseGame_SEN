<template>
	<div class="add-new-quiz">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add New Quiz</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<textarea class="textarea" placeholder="Enter question for quiz"></textarea>
						<br>
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Option A">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Option B">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Option c">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Option D">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="column" id="correctAnswer">
						<input class="input" type="text" placeholder="Correct Answer">
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info">Save</a>
					<a class="button is-info" id="closeBtn" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'add-new-quiz',

	methods: {
		close() {
			this.$emit('closeAddNewQuiz');
		}
	}

}
</script>

<style lang="scss">
.add-new-quiz {
	#closeBtn {
		margin-left: 29rem;
	}
}
</style>
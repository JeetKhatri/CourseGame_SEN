<template>
	<div class="add-new-quiz">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add New Quiz</p>
					<!-- <button class="delete" @click="close"></button> -->
				</header>
				<section class="modal-card-body">
					<div class="details">
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Quiz Name">
									</div>
								</div>
							</div>
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Created By">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="close">Add Questions</a>
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
			this.$emit('openAddNewQuestion');
		}
	}
}
</script>

<style lang="scss">
.add-new-quiz {
	
}
</style>
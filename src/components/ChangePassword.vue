<template>
	<div class="change-password">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Change Password</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Old Password') }" name="Old Password" type="password" placeholder="Old Password" v-model="oldPassword" v-validate="'required'"> 
									</div>
								</div>
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('New Password') }" name="New Password" type="password" placeholder="New Password" v-model="newPassword" v-validate="'required'">
									</div>
								</div>
								<div class="field">
									<div class="control">
										<input :class="{'input': true, 'is-danger': errors.has('Confirm Password') }" name="Confirm Password" type="password" placeholder="Confirm Password" v-model="confirmPassword" v-validate="'required'">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="change">Change</a>
				</footer>
			</div>
		</div>
	</div>	
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: "change-password",

	data() {
		return {
			oldPassword: '',
			newPassword: '',
			confirmPassword: '',
			id: ''
		}
	},

	created() {
		this.id = localStorage.getItem('faculty_id')
	},

	methods: {
		close() {
			this.$emit("changePasswordClose")
		},
		validate() {
			return this.$validator.validateAll()
		},
		change() {
			if(this.oldPassword == '' || this.newPassword == '' || this.confirmPassword == ''){
				this.validate()
			} else if(this.newPassword != this.confirmPassword){
				let toast = this.$toasted.error('Password Mismatch', {
					theme: 'outline',
					position: 'top-center',
					duration: 3000
				});
			} else {
				HTTP.post(`rest/user/change-pass?userid=`+this.id+`&oldpassword=`+this.oldPassword+`&newpassword=`+this.newPassword, {

				})	
				.then(response => {
					let toast = this.$toasted.success('Password Successfully Changed', {
						theme: 'outline',
						position: 'top-center',
						duration: 3000
					});
					this.$emit("changePasswordClose")
				})		
				.catch((e) => {
					console.log(e)
				})	
			}
		}
	}

}
</script>

<style lang="scss">

</style>
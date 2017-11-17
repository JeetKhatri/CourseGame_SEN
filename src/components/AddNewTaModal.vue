<template>
	<div class="add-new-student">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add New TA</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="form">
						<div class="field is-horizontal">
							<div class="field-body">
								<div class="field">
									<div class="field-wrap">
										<input type="text" v-model="emailId" placeholder="Email Id">
									</div>
								</div>
								<div class="field">
									<div class="field-wrap">
										<input type="text" v-model="name" placeholder="Name">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="addTa()">Add</a>
					<a class="button is-info close-btn" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
import HTTP from '@/packages/HTTP'
export default {
	name: 'add-new-student',
	data(){
		return{
			emailId:'',
			name:'',
			batchid: ''
		}
	},
	created(){
		this.batchid =this.$route.params.batchid	
	},
	methods: {
		close() {
			this.$emit("closeAddTa");
		},
		addTa(){
			console.log(this.batchid)
			HTTP.post(`https://coursegame.herokuapp.com/rest/ta/ta-insert`, {
				emailid: this.emailId,
				name: this.name,
				batchId: this.batchid
			})
			.then(response => {
				if (response.status === 200) {
					let toast = this.$toasted.success('TA created successfully', {
						theme: 'outline',
						position: 'top-center',
						duration: 3000
					});
					this.$emit("closeAddTa");
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
.add-new-student {
	.mainw3-agileinfo {
		width: 50%;
		margin: 2em auto;
		padding: 3em;
	}
	.form a {
		text-decoration: none;
		-webkit-transition: .5s ease;
		-moz-transition: .5s ease;
		-o-transition: .5s ease;
		-ms-transition: .5s ease;
		transition: .5s ease;
	}
	label {
		position: absolute;
		-webkit-transform: translateY(6px);
		-moz-transform: translateY(14px);
		-o-transform: translateY(14px);
		-ms-transform: translateY(14px);
		transform: translateY(14px);
		left: 2px;
		color: rgba(255, 255, 255, 0.45);
		-webkit-transition: all 0.25s;
		-moz-transition: all 0.25s; 
		transition: all 0.25s;
		-webkit-backface-visibility: hidden;
		pointer-events: none;
		font-size: 1em;
	}
	label .req {
		margin: 2px;
		color: #FFC107;
	}
	label.active {
		-webkit-transform: translateY(50px);
		-moz-transform: translateY(58px); 
		-o-transform: translateY(58px); 
		-ms-transform: translateY(58px); 
		transform: translateY(58px); 
		font-size: .75em;
	}
	label.active .req {
		opacity: 0;
	} 
	label.highlight {
		color: #fff;
	} 
	input, textarea {
		font-size: 1em;
		display: block;
		width: 93%;
		padding:1em 1em 1em 0;
		background: none;
		background-image: none;
		border: none;
		border-bottom: 1px solid #8e8e8e;
		border-radius: 0;
		-webkit-transition: border-color .25s ease, box-shadow .25s ease;
		-moz-transition: border-color .25s ease, box-shadow .25s ease; 
		transition: border-color .25s ease, box-shadow .25s ease;
	}
	input:focus, textarea:focus {
		outline: 0;
		border-color: #FFC107;
	} 
	textarea {
		border: 2px solid #a0b3b0;
		resize: vertical;
	} 
	.field-wrap {
		position: relative;
		margin-bottom: 40px;
	} 
}
</style>
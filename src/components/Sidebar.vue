<template>
	<div class="sidebar">
		<div class="card sidebar">
			<aside class="menu">
				<ul class="menu-list">
					<li><router-link :to="{name: 'view-details', params:{batchid: batchid}}">Students</router-link></li>
					<li><router-link :to="{name: 'ta', params:{batchid: batchid}}">TA</router-link></li>
					<li><router-link :to="{name: 'quiz-played', params:{batchid: batchid}}">Quiz</router-link></li>
				</ul>
			</aside>
		</div>		
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'sidebar',
	data(){
		return{
			batchid:''
		}
	},
	created(){
		this.batchid =this.$route.params.batchid;
	}

}
</script>

<style lang="scss">
.sidebar {
	position: fixed;
	width: 100%;
	.card {
		position: absolute;
		width: 20%;
		top: 4.5rem;
	}
}

</style>
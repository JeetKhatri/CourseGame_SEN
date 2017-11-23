package root.Bean;

public class UserBean {

	private String userId;
	private String emailId;
	private String userName;
	private String userPassword;
	private String userRole;
	private String userIsAvailable;
	private boolean responseStatus=false;

	public boolean isResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(boolean responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserIsAvailable() {
		return userIsAvailable;
	}

	public void setUserIsAvailable(String userIsAvailable) {
		this.userIsAvailable = userIsAvailable;
	}

}

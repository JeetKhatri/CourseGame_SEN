package root.Bean;

public class TABean {

	private String taid;
	private String userid;
	private String batchid;
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getTaid() {
		return taid;
	}

	public void setTaid(String taid) {
		this.taid = taid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

}

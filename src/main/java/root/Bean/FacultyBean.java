package root.Bean;

public class FacultyBean {

	private String facultyId;
	private String degree;
	private String isApproved;
	private UserBean userbean;

	public UserBean getUserbean() {
		return userbean;
	}

	public void setUserbean(UserBean userbean) {
		this.userbean = userbean;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

}

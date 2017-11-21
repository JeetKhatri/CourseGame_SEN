package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.BatchBean;
import root.Bean.FacultyBean;
import root.Controller.SendEmail;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class FacultyDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null,pstmt1=null;
	Connection conn = null;

	public boolean insert(String id, String degree) {

		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement("insert into Faculty(userId,degree,isApproved) value(?,?,?)");
				pstmt.setString(1, id);
				pstmt.setString(2, degree);
				pstmt.setString(3, "N");
				if (pstmt.executeUpdate() != 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public boolean approve(String userId) {

		String sql = "update faculty set isApproved='Y' where userid= '" + userId + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				if (pstmt.executeUpdate() != 0) {
					String random = GenrateMathodsUtils.getRandomString(7);
					pstmt1 = conn.prepareStatement("update users set password=?,isavailable='Y' where userid=?");
					pstmt1.setString(1, GenrateMathodsUtils.makeSHA512(random));
					pstmt1.setString(2, userId);
					int no = pstmt1.executeUpdate();
					if(no==0){
						conn.rollback();
					}else {
						SendEmail obj = new SendEmail();
						obj.sendEmail("Course Game 2k17", getFacultyEmail(userId),
								"Welcome to CourseGame 2k17<br>"
								+ "<font color='red'>Congratulation!<font> Your regestraion is Successful.<br>"
								+ "Please find your Username and Password mention below <br>"
								+ "<b>Username :- "+getFacultyEmail(userId)+"<br>Password :- "+random+"</b><br><br>Regards,<br>Course Game 2k17");
						conn.commit();
						conn.setAutoCommit(true);
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}
	
	public String getFacultyEmail(String id) {
		String sql = "select * from users where userid = ?";
	//	conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getString("emailid");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		}
		return null;

	}
	
	
	public String getFacultyId(String id,Connection conn) {
		String sql = "select facultyid from faculty where userid = ?";
		String fid="";
		if (conn != null) {
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					fid = rs.getString("facultyid");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		}
		return fid;

	}
	
	public boolean update(FacultyBean bean) {

		String sql = "update faculty set degree=?,isapproved=? where facultyid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getDegree());
				pstmt.setString(2, bean.getIsApproved());
				pstmt.setString(3, bean.getFacultyId());
				int temp = pstmt.executeUpdate();
				if (temp > 0) {
					sql = "update users set " + "emailid=?,name=?,role=?,isavailable=? where userid=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bean.getEmailId());
					pstmt.setString(2, bean.getUserName());
					pstmt.setString(3, bean.getUserRole());
					pstmt.setString(4, bean.getUserIsAvailable());
					pstmt.setString(5, bean.getUserId());
					temp = pstmt.executeUpdate();
					if (temp > 0)
						return true;
					conn.rollback();
					return false;
				} else{
					conn.rollback();
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}
	
	public ArrayList<BatchBean> facultyBatch(String userId) {

		ArrayList<BatchBean> arrayList = new ArrayList<BatchBean>();

		String sql = "select * from batch b,faculty f where b.facultyid=f.facultyid and userId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();
				BatchBean bean = new BatchBean();
				while (rs.next()) {
					bean = new BatchBean();
					bean.setBatchid(rs.getString("batchid"));
					bean.setBatchname(rs.getString("batchname"));
					bean.setFacultyid(rs.getString("facultyid"));

					arrayList.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arrayList;
	}

	public ArrayList<FacultyBean> facultyList() {
		ArrayList<FacultyBean> arrayList = new ArrayList<FacultyBean>();

		String sql = "select * from faculty f,users u where f.userid=u.userid";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				FacultyBean bean = new FacultyBean();
				while (rs.next()) {
					bean = new FacultyBean();
					bean.setDegree(rs.getString("degree"));
					bean.setFacultyId(rs.getString("facultyid"));
					bean.setIsApproved(rs.getString("isApproved"));
					bean.setEmailId(rs.getString("emailid"));
					bean.setUserId(rs.getString("userid"));
					bean.setUserIsAvailable(rs.getString("isavailable"));
					bean.setUserName(rs.getString("name"));
					bean.setUserRole(rs.getString("role"));

					arrayList.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arrayList;
	}
}
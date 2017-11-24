package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import root.Bean.StatusBean;
import root.Bean.StudentBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.Controller.SendEmail;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class UserDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null,pstmt1 = null;
	Connection conn = null;

	public ArrayList<UserBean> getList() {
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		UserBean albumBean = new UserBean();
		String sql = "select * from users";
		conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new UserBean();
					albumBean.setEmailId(rs.getString("emailid"));
					albumBean.setUserId(rs.getString("userId"));
					albumBean.setUserIsAvailable(rs.getString("isAvailable"));
					albumBean.setUserName(rs.getString("name"));
					albumBean.setUserRole(rs.getString("role"));
					albumBean.setResponseStatus(true);
					list.add(albumBean);
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
		return list;

	}

	public boolean insertFaculty(UserBean userBean, String degree) {

		String sql = "insert into users(userId,emailid,name,role,isAvailable,password) values(?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		String id = GenrateMathodsUtils.getRandomString(15);
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, userBean.getEmailId());
				pstmt.setString(3, userBean.getUserName());
				pstmt.setString(4, "Faculty");
				pstmt.setString(5, "N");
				pstmt.setString(6, GenrateMathodsUtils.makeSHA512(GenrateMathodsUtils.getRandomPass(10)));
				int no = pstmt.executeUpdate();
				if (no != 0) {

					pstmt = conn.prepareStatement(
							"insert into Faculty (facultyid,userId,degree,isApproved) values(?,?,?,?)");
					pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
					pstmt.setString(2, id);
					pstmt.setString(3, degree);
					pstmt.setString(4, "N");
					if (pstmt.executeUpdate() == 0) {
						conn.rollback();
						return false;
					} else {
						return true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
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

	public boolean insertTA(UserBean userBean, String batchId) {

		TABean bean = new TABean();
		String random = GenrateMathodsUtils.getRandomString(10);
		String sql = "insert into users(userId,emailid,name,role,isAvailable,password) values(?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		String id = GenrateMathodsUtils.getRandomString(15);
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, userBean.getEmailId());
				pstmt.setString(3, userBean.getUserName());
				pstmt.setString(4, "TA");
				pstmt.setString(5, "Y");
				pstmt.setString(6, GenrateMathodsUtils.makeSHA512(random));
				int no = pstmt.executeUpdate();
				if (no != 0) {

					pstmt = conn.prepareStatement("insert into ta (taid,userId,batchId) values(?,?,?)");
					String taid = GenrateMathodsUtils.getRandomString(15);
					pstmt.setString(1, taid);
					pstmt.setString(2, id);
					pstmt.setString(3, batchId);
					if (pstmt.executeUpdate() == 0) {
						conn.rollback();
					} else {
						bean.setBatchid(batchId);
						bean.setTaid(taid);
						bean.setUserid(id);

						SendEmail obj = new SendEmail();
						String msg="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
								+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='https://course-game.firebaseapp.com'><img src='https://coursegame.herokuapp.com/imgs/logo.png' style='max-height: 60px;max-width: 100%;display: inline-block;' />"
								+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
								+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+ userBean.getUserName() +",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that now you are joined our website. Your password is <b></u>"+random+ "</u></b>.<br /></p>"
								+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
								+ "</p>"
								+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>Course Game 2k17</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
								+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. Course Game 2k17. All rights reserved.</p>"
								+ "</div></td</tr></tbody></table>";
						obj.sendEmail("Request accepted", getTEmail(taid),
								msg);
						conn.commit();
						conn.setAutoCommit(true);
						return true;
					}
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
		return false;
	}

	private String getTEmail(String id) {
		String sql = "select * from ta,users where users.userid = ta.userid and taid = ?";

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					return rs.getString("emailid")+"";
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
			}

		}
		return null;

	}
	
	
	public StatusBean insertStudent(StudentBean studentBean) {

		String sql = "insert into users(userId,emailid,name,role,isAvailable,password) values(?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		String id = GenrateMathodsUtils.getRandomString(15);
		String pass = GenrateMathodsUtils.getRandomString(10);
		StatusBean status= new StatusBean(); 
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, studentBean.getEmailId());
				pstmt.setString(3, studentBean.getUserName());
				pstmt.setString(4, "Student");
				pstmt.setString(5, "Y");
				pstmt.setString(6, GenrateMathodsUtils.makeSHA512(pass));
				int no = pstmt.executeUpdate();
				if (no != 0) {

					pstmt = conn.prepareStatement(
							"insert into student (studentid,userid,batchid) values(?,?,?)");
					pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
					pstmt.setString(2, id);
					pstmt.setString(3, studentBean.getBatchId());
					if (pstmt.executeUpdate() == 0) {
						conn.rollback();
					} else {
						SendEmail obj = new SendEmail();
						String msg="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
								+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='https://course-game.firebaseapp.com'><img src='https://coursegame.herokuapp.com/imgs/logo.png' style='max-height: 60px;max-width: 100%;display: inline-block;' />"
								+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
								+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+ studentBean.getEmailId() +",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that now you are joined our website. Your password is <b></u>"+pass+ "</u></b>.<br /></p>"
								+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
								+ "</p>"
								+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>Course Game 2k17</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
								+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. Course Game 2k17. All rights reserved.</p>"
								+ "</div></td</tr></tbody></table>";
						obj.sendEmail("Dear Student ", studentBean.getEmailId(),msg);
						status.setResponseStatus(true);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
		return status;

	}

	
	public StatusBean remove(String userId) {
		StatusBean bean = new StatusBean();
		String sql = "delete from users where userid='" + userId + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				if (pstmt.executeUpdate() > 0)
					bean.setResponseStatus(true);
			}

			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return bean;
	}

	public boolean forgotPass(String emailId) {
		String sql = "update users set password = ? where emailId = ?";
		conn = DBConnection.getConnection();
		String pass = GenrateMathodsUtils.getRandomPass(10);
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.makeSHA512(pass));
				pstmt.setString(2, emailId);
				int result = pstmt.executeUpdate();
				if (result > 0) {
					SendEmail send = new SendEmail();
					String msg="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
							+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='https://course-game.firebaseapp.com'><img src='https://coursegame.herokuapp.com/imgs/logo.png' style='max-height: 60px;max-width: 100%;display: inline-block;' />"
							+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
							+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+ emailId +",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'> Your password is <b></u>"+pass+ "</u></b>.<br /></p>"
							+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
							+ "</p>"
							+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>Course Game 2k17</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
							+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. Course Game 2k17. All rights reserved.</p>"
							+ "</div></td</tr></tbody></table>";
					send.sendEmail("Password", emailId, "Your Password is " + msg);
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

			}

		}
		return false;

	}
	
	public HashMap<String, Object> getUserId(String facultyId) {
		String sql = "select * from faculty where facultyid = ?";
		conn = DBConnection.getConnection();
		HashMap<String, Object> obj = new HashMap<String, Object>(); 
		boolean flag=false;
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, facultyId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					obj.put("userId", rs.getString("userid"));
					flag=true;
				}
				obj.put("status", flag);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		}
		return obj;

	}
	
	public Boolean changePassword(String userid, String oldpassword, String newpassword) {

		String sql = "select * from users where userid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String password = rs.getString("password");
					if (GenrateMathodsUtils.makeSHA512(oldpassword).equals(password)) {
						pstmt1 = conn.prepareStatement("update users set password=? where userid=?");
						pstmt1.setString(1, GenrateMathodsUtils.makeSHA512(newpassword));
						pstmt1.setString(2, userid);
						int n = pstmt1.executeUpdate();
						if (n != 0)
							return true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		}
		return false;

	}
	
}
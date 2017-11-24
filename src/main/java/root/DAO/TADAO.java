package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.BatchBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.Controller.SendEmail;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class TADAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public ArrayList<UserBean> getList() {
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		UserBean Bean = new UserBean();
		String sql = "select * from users";
		conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Bean = new UserBean();
					Bean.setEmailId(rs.getString("emailid"));
					Bean.setUserId(rs.getString("userId"));
					Bean.setUserIsAvailable(rs.getString("isAvailable"));
					Bean.setUserName(rs.getString("name"));
					Bean.setUserRole(rs.getString("role"));
					Bean.setResponseStatus(true);
					list.add(Bean);
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

	public boolean insert(UserBean userBean, String degree) {

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
				pstmt.setString(4, userBean.getUserRole());
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
						SendEmail obj = new SendEmail();
						String msg="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
								+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='https://course-game.firebaseapp.com'><img src='https://coursegame.herokuapp.com/imgs/logo.png' style='max-height: 60px;max-width: 100%;display: inline-block;' />"
								+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
								+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+ userBean.getUserName() +",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that your request is arrived, we will accept your request.<br /></p>"
								+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
								+ "</p>"
								+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>Course Game 2k17</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
								+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. Course Game 2k17. All rights reserved.</p>"
								+ "</div></td</tr></tbody></table>";
						obj.sendEmail("Request arrive", userBean.getEmailId(), msg);
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

	public ArrayList<TABean> taList(String batchId) {

		ArrayList<TABean> arrayList = new ArrayList<TABean>();

		String sql = "select * from ta t,users u where t.userid=u.userid and batchid='" + batchId + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				TABean bean = new TABean();
				while (rs.next()) {
					bean = new TABean();
					bean.setTaid(rs.getString("taid"));
					bean.setBatchid(rs.getString("batchid"));
					bean.setEmailId(rs.getString("emailid"));
					bean.setUserId(rs.getString("userid"));
					bean.setUserIsAvailable(rs.getString("isavailable"));
					bean.setUserName(rs.getString("name"));
					bean.setUserRole(rs.getString("role"));
					bean.setUserid(rs.getString("userid"));

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

	public ArrayList<BatchBean> batchList(String userId) {

		ArrayList<BatchBean> arrayList = new ArrayList<BatchBean>();

		String sql = "select * from ta t,batch b where t.batchid=b.batchid and userid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();
				BatchBean bean;
				
				while (rs.next()) {
					bean = new BatchBean();
					bean.setBatchid(rs.getString("batchid"));
					bean.setBatchname(rs.getString("batchname"));
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

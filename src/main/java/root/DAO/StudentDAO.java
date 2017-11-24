package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import root.Bean.Mail;
import root.Bean.StudentBean;
import root.Controller.ThreadImplement;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class StudentDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public ArrayList<StudentBean> studentList(String batchid) {
		ArrayList<StudentBean> arrayList = new ArrayList<StudentBean>();

		String sql = "select * from student s,users u where s.userid=u.userid and s.batchid='" + batchid + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				StudentBean bean = new StudentBean();
				while (rs.next()) {
					bean = new StudentBean();
					bean.setBatchId(rs.getString("batchid"));
					bean.setStudentId(rs.getString("studentid"));
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

	public boolean insertStudent(String studentList, String batchid) {

		String[] students = studentList.split(",");
		String insertUser = "insert into users(userid,emailid,name,password,role,isavailable) values ";
		String insertStudent = "insert into student(studentid,batchid,userid) values ";

		String perticular[];

		String userid = "";
		String password = "";
		Queue<Mail> queue = new LinkedList<Mail>();

		for (int i = 0; i < students.length; i++) {

			perticular = students[i].split("~");
			userid = GenrateMathodsUtils.getRandomString(15);
			password = GenrateMathodsUtils.getRandomPass(10);
			String msg="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
					+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='https://course-game.firebaseapp.com'><img src='https://coursegame.herokuapp.com/imgs/logo.png' style='max-height: 60px;max-width: 100%;display: inline-block;' />"
					+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
					+ "<strong style='display: block;margin-bottom: 20px;'>Hi Student,</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that now you are joined our website. Your password is <b></u>"+password+ "</u></b>.<br /></p>"
					+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
					+ "</p>"
					+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>Course Game 2k17</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
					+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. Course Game 2k17. All rights reserved.</p>"
					+ "</div></td</tr></tbody></table>";
			queue.add(new Mail("Account Activation", perticular[0],
					"Dear, " + perticular[1] + msg));

			String temp = "('" + userid + "','" + perticular[0] + "','" + perticular[1] + "','"
					+ GenrateMathodsUtils.makeSHA512(password) + "'," + "'Student','Y'),";
			insertUser += temp;

			String temps = "('" + GenrateMathodsUtils.getRandomString(15) + "','" + batchid + "','" + userid + "'),";
			insertStudent += temps;
		}

		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(insertUser.substring(0, insertUser.length() - 1));

				if (pstmt.executeUpdate() != 0) {

					pstmt = null;
					pstmt = conn.prepareStatement(insertStudent.substring(0, insertStudent.length() - 1));

					int no = pstmt.executeUpdate();
					if (no == 0) {
						conn.rollback();
					} else {

						// send mail by thread
						ThreadImplement th = new ThreadImplement(queue);
						Thread thread = new Thread(th);
						thread.start();
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
}

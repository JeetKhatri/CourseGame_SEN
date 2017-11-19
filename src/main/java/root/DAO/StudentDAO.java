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

			queue.add(new Mail("Account Activation", perticular[0],
					"Dear, "+perticular[1] + " we accepted your requst and your password is " + password));

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

	public static void main(String[] args) {
		System.out.println("ok");

		System.out.println(new StudentDAO().insertStudent(
				"201612066@daiict.ac.in~Dhiral,201612038@daiict.ac.in~Avnsih,201612024@daiict.ac.in~Jeet,201612019@daiict.ac.in~hardik,",
				"WwFIwWWJGIGZEWw"));

		System.out.println(GenrateMathodsUtils.makeSHA512("123456"));
	}

}

package root.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.twilio.rest.ipmessaging.v2.service.User;

import root.Bean.UserBean;
import root.Utils.GenrateMathodsUtils;

public class UserDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int insert(final UserBean userBean) {

		String sql = "insert into user(userId,emailId,name,password,role,isAvailable) value(?,?,?,?,?,?)";
		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, GenrateMathodsUtils.getRandomString(15));
				ps.setString(2, userBean.getEmailId());
				ps.setString(3, userBean.getUserName());
				ps.setString(4, GenrateMathodsUtils.makeSHA512(userBean.getUserPassword()));
				ps.setString(5, userBean.getUserRole());
				ps.setString(6, userBean.getUserIsAvailable());
			}
		});
	}

	public ArrayList<UserBean> list() {

		String sql = "select * from user";
		return template.query(sql, new ResultSetExtractor<ArrayList<UserBean>>() {
			public ArrayList<UserBean> extractData(ResultSet rs) throws SQLException {
				ArrayList<UserBean> userBeans = new ArrayList<UserBean>();
				UserBean bean = new UserBean();

				while (rs.next()) {
					bean = new UserBean();
					bean.setEmailId(rs.getString("emailId"));
					bean.setUserId(rs.getString("userId"));
					bean.setUserName(rs.getString("name"));
					bean.setUserRole(rs.getString("role"));
					bean.setUserIsAvailable(rs.getString("isAvailable"));
					userBeans.add(bean);
				}
				return userBeans;
			}
		});
	}

	public int update(final UserBean userBean) {

		String sql = "update user set emailId=?,name=?,role=?,isAvailable=? where userId=?";
		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(5, userBean.getUserId());
				ps.setString(1, userBean.getEmailId());
				ps.setString(2, userBean.getUserName());
				ps.setString(3, userBean.getUserRole());
				ps.setString(4, userBean.getUserIsAvailable());
			}
		});
	}

	public int delete(final String userId) {

		String sql = "update user set isAvailable='n' where userId=?";
		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userId);
			}
		});
	}

}

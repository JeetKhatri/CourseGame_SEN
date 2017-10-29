package root.WebServices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import root.Bean.UserBean;
import root.DAO.UserDAO;

@RestController
public class UserWebServices {

	@Autowired
	UserDAO user;

	@RequestMapping(value = "/user-insert/{token}", method = RequestMethod.POST)
	public Integer insertUser(@PathVariable("token") String token, @RequestBody UserBean userBean) {

		System.out.println(user.insert(userBean));
		return 1;
	}

	@RequestMapping(value = "/user-list/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<UserBean>> listUser(@PathVariable("token") String token) {
		System.out.println();
		return new ResponseEntity<ArrayList<UserBean>>(user.list(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user-update/{token}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateUser(@PathVariable("token") String token,@RequestBody UserBean userBean) {
		
		return user.update(userBean);
		
	}
	
	@RequestMapping(value = "/user-delete/{token}/{userId}", method = RequestMethod.DELETE)
	public Integer deleteUser(@PathVariable("token") String token,@PathVariable("userId") String userId) {
		
		return user.delete(userId);
		
	}

}

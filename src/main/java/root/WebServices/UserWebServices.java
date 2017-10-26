package root.WebServices;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import root.Bean.UserBean;

@RestController
public class UserWebServices {

	@RequestMapping(value = "/rest_user_login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getLoginAccessForAdmin(@PathVariable("userName") String userName,
			@PathVariable("password") String password) {
		
		System.out.println("yes");
		return 1;
	}

	/*// check is customer mobile no is registered
	@RequestMapping(value = "/rest_customer_isregistered/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isCustomerRegistered(@PathVariable("phone") String phone) {
		return new ResponseEntity<Integer>(dataAccess.isCustomerRegistered(phone), HttpStatus.OK);
	}

	// check is service provider mobile no is registered
	@RequestMapping(value = "/rest_serviceprovider_isregistered/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isServiceProviderRegistered(@PathVariable("phone") String phone) {
		return new ResponseEntity<Integer>(dataAccess.isServiceProviderRegistered(phone), HttpStatus.OK);
	}*/
}




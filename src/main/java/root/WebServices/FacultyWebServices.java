package root.WebServices;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.UserBean;
import root.DAO.FacultyDAO;
import root.DAO.UserDAO;

@Path("/faculty")
public class FacultyWebServices {


	@PUT
	@Path("/faculty-approved")
	@Produces("application/json")
	public boolean insertAlbum(@Context UriInfo info) {

		String facultyId = info.getQueryParameters().getFirst("facultyid");
		return new FacultyDAO().approve(facultyId);
	}
	
}

package root.WebServices;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.BatchBean;
import root.DAO.BatchDAO;

@Path("/batch")
public class BatchWebService {

	@POST
	@Path("/batch-insert")
	@Produces("application/json")
	public boolean insert(@Context UriInfo info) {

		String facultyId = info.getQueryParameters().getFirst("facultyid");
		String batchname = info.getQueryParameters().getFirst("batchName");
		
		return new BatchDAO().insert(facultyId, batchname);
	}

}

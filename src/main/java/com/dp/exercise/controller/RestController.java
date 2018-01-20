package com.dp.exercise.controller;

import com.dp.exercise.model.User;
import com.dp.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {
	
	@Autowired
	public UserService userService;

	@GET
	@Path("/listUser")
	public Response home() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> listUsers = userService.getListUser();
		response.put("data", listUsers);
		response.put("total", listUsers.size());
		return Response.status(200).entity(response).build();
	}
	
}

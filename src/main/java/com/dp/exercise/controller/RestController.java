package com.dp.exercise.controller;

import com.dp.exercise.model.User;
import com.dp.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


import java.io.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {
	
	@Autowired
	public UserService userService;

	private String rootPath = System.getProperty("catalina.home");

	@PostConstruct
	public void init() {
		rootPath += "/webapps/tmpFiles";
		File dir = new File(rootPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	@GET
	public Response getAllUsers() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> listUsers = userService.getListUser();
		response.put("data", listUsers);
		response.put("total", listUsers.size());
		return Response.status(200).entity(response).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser (@PathParam("id") int id){
		User user = userService.findById(id);
		if(user != null){
			try {
				userService.deleteFile(rootPath, user.getEmail());
				userService.delete(user);
				return Response.status(200).build();
			}catch (Exception e){
				return Response.status(400).entity(e.getMessage()).build();
			}
		}else {
			return Response.status(400).build();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createUser(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
			@FormDataParam("user") User user){

		try {
			String fileName = contentDispositionHeader.getFileName().replaceAll("\\s+","_");
			String filePath = rootPath + "/" + fileName;
			// save the file to the server
			saveFile(fileInputStream, filePath);

			String relativePath = "../tmpFiles/" + fileName;
			user.setEmail(relativePath);
			userService.create(user);

			return Response.status(200).build();
		}catch (Exception e){
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	private void saveFile(InputStream uploadedInputStream,
						  String serverLocation) {
		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

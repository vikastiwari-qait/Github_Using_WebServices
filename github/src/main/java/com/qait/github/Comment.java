package com.qait.github;
import java.sql.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("comment")
public class Comment {
	
	@POST
	@Path("addcomment")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddComment(@FormParam("comment") String comment,@FormParam("email") String email) throws URISyntaxException{
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/github","root","Vikastiwari489#");  
    		  
    		PreparedStatement ps=con.prepareStatement("update information set comment = ? where email = ?");  
    		
    		ps.setString(1,comment);  
    		ps.setString(2,email );
    		
    		ps.executeUpdate();
    		
    		System.out.println("Comment Added");
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		  
    	URI location = new URI("http://localhost:8080/github/home.html");
    	return Response.seeOther(location).build(); 

}
	@GET
    @Path("getallcomments")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllComments() throws URISyntaxException {
    	String output = "";
    	try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/github","root","Vikastiwari489#");  
    		Statement stmt=con.createStatement();  
    		ResultSet rs=stmt.executeQuery("select fname,comment from information");  
    		while(rs.next())  
    		{
    			output += rs.getString(1) + " : " + rs.getString(2) + "\n";
    		}
    		con.close();
    		return Response.status(200).entity(output).build();	
    		}
    	catch(Exception e){ System.out.println(e);
    	}
    	URI location = new URI("http://localhost:8080/github/home.html");
    	return Response.seeOther(location).build(); 
    	}
}

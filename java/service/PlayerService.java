package service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;

import service.ResultSetConverter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "EloSwiss")
@Path("/")
public class PlayerService {

	private static final String INSERT_PLAYER_SQL = "INSERT_PLAYER.sql";
	private static final String BLANK = "\\?";
	private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
	private static final String JDBC_SQLITE = "jdbc:sqlite:";
	private static final String ELOSWISS_DB = "eloswiss.db";
	private static final String RANKINGS_SQL = "RANKINGS.sql";
	private static final String res = "/Users/gsainsbury86/development/tournament/EloSwiss/src/main/resources/";

	static String log;

	public PlayerService(){

	}

	@GET
	@Path("/hello")
	@Produces("text/html;charset=UTF-8;version=1")
	public String hello() {
		return "Hello World";
	}

	@GET
	@Path("/rankings")
	@Produces("application/json")
	public Response rankings(){
		Connection connection = null;  
		ResultSet resultSet = null;  
		Statement statement = null;  
		String query = null;

		try 
		{  
			File queryFile = new File(res+RANKINGS_SQL);
			query = FileUtils.readFileToString(queryFile);

			Class.forName(ORG_SQLITE_JDBC);  
			connection = DriverManager.getConnection(JDBC_SQLITE+res+ELOSWISS_DB);  
			statement = connection.createStatement();  
			resultSet = statement  
					.executeQuery(query);  

			return Response.status(200).entity(ResultSetConverter.convert(resultSet).toString()).build();


		} 
		catch (Exception e) 
		{  
			e.printStackTrace();
		}
		finally 
		{  
			try 
			{  
				resultSet.close();  
				statement.close();  
				connection.close();  
			} 
			catch (Exception e) 
			{  
				e.printStackTrace();  
			}  
		}  
		return null;
	}

	@GET
	@Path("/addPlayer/{playerName}")
	@Produces("application/json")
	public Response addPlayer(@PathParam("playerName") String playerName){
		Connection connection = null;  
		Statement statement = null;  
		String query = null;

		Random random = new Random();

		try 
		{  
			File queryFile = new File(res+INSERT_PLAYER_SQL);
			query = FileUtils.readFileToString(queryFile);
			
			
			query = query.replaceFirst(BLANK, ""+random.nextInt()/*java.util.UUID.randomUUID()*/);
			//TODO: Sanitise
			query = query.replaceFirst(BLANK, playerName);
			query = query.replaceFirst(BLANK, /*new rating*/"0");

			System.err.println(query);

			Class.forName(ORG_SQLITE_JDBC);  
			connection = DriverManager.getConnection(JDBC_SQLITE+res+ELOSWISS_DB);  
			statement = connection.createStatement();  
			statement.executeQuery(query);

			return Response.status(200).entity("Added New Player: "+playerName/*ResultSetConverter.convert(resultSet).toString()*/).build();


		} 
		catch (Exception e) 
		{  
			e.printStackTrace();
		}
		finally 
		{  
			try 
			{  
				statement.close();  
				connection.close();  
			} 
			catch (Exception e) 
			{  
				e.printStackTrace();
			}  
		}  
		return null;
	}

	
	


}

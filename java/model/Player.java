package model;

//import org.xerial.*;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  


public class Player {

	private int ID;
	private String name;
	private int rating;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Player(int iD, String name, int rating) {
		super();
		ID = iD;
		this.name = name;
		this.rating = rating;
	}
	
	
	
	
}
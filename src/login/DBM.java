package login;

import java.time.*;
import java.sql.*;

public class DBM {
	boolean contains;
	Timestamp starttime = null;
	Timestamp endtime = null;
		
	public DBM() {
	}

	public boolean contains(String Username) {
		contains = false;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "dbpass");
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM user_table having username = ?");
			
			preparedStatement.setString(1, Username);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				contains = true;
			}
		
		} 
		catch (SQLException e) {
			System.out.print("Error connecting to database");
		}
		return contains;
	}
	public User getuser(String Username) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "dbpass");
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM user_table having username = ?");
			
			preparedStatement.setString(1, Username);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			String Uname = resultSet.getString("username");
			String Pword = resultSet.getString("password");
			String Perm = resultSet.getString("permission");
			int parkSpot = resultSet.getInt("spotNumber");
			if(parkSpot == 0) {
				return new User(Uname,Pword,Perm);
			}
			starttime = resultSet.getTimestamp("starttime");
			endtime = resultSet.getTimestamp("endtime");
			Reservation res = new Reservation(parkSpot,starttime,endtime);
			return new User(Uname,Pword,res,Perm);
			
		
		} 
		catch (SQLException e) {
			System.out.print("Error connecting to database");
			return null;
		}
	}
	public User createUser(String Username, String Password) {
		User currentUser = new User(Username,Password,null);
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "dbpass");
			PreparedStatement preparedStatement=connection.prepareStatement("INSERT into user_table values(?,?,?,?,?)");
			
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			preparedStatement.setInt(3, 0);
			preparedStatement.setNull(4, Types.NULL);
			preparedStatement.setNull(5, Types.NULL);
			preparedStatement.executeUpdate();
			System.out.print("executed");
		} 
		catch (SQLException e) {
			System.out.print("Error connecting to database");
		}
		
		return currentUser;
	}
	public void UpdatePerm(User currentUser,String Perm) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "dbpass");
			PreparedStatement preparedStatement=connection.prepareStatement("update user_table set permission = ? where username = ?");
			preparedStatement.setString(1, Perm);
			preparedStatement.setString(2, currentUser.getUsername());
		}
		catch (SQLException e) {
			System.out.print("Error connecting to database");
		}
	}
	public void UpdateRes(User currentUser,Reservation res) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "dbpass");
			PreparedStatement preparedStatement=connection.prepareStatement("update user_table set spotNumber = ?, starttime = ?, endtime = ?  where username = ?");
			preparedStatement.setInt(1, res.parkingSpotNumber);
			preparedStatement.setTimestamp(2, res.starttime);
			preparedStatement.setTimestamp(3, res.endtime);
		}
		catch (SQLException e) {
			System.out.print("Error connecting to database");
		}
	}
	
	
}

package DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import QueryData.Products;

public class MySQL_Connection {

	private Connection connect;
	private Statement stmt;
	//private ResultSet rs;
	private String data;
	
	
	public MySQL_Connection(){
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost/tesco?autoReconnect=true&useSSL=false","root","root");
		}catch(SQLException e){
			System.out.println(e);
			
		}
	}
	
	
	public void insertGloceris(Products td, String type){
		try{
			stmt = connect.createStatement();
			 data = "Insert into tescogroceries (product_id, product_name, super_department, department, description,"
					+ " content_quantity, price, unit_price, Retrieved_Date, type) values (" 
					+ td.Product_ID + ", \"" + td.Product_Name + "\", '" + td.Super_Department +"', '" + td.Department + "', \"" 
					+ td.Description + "\"	," + td.Content_Quantity + "," + td.Price + ", '" + td.Unit_Price +"', + now(),'" + type + "')";
			
			//System.out.println(data); 
			stmt.executeUpdate(data);
			stmt.close();
		}
	
		catch(SQLException e){
			System.out.println(e);
		}
	}
	

	
	public void deleteGloceries() {
		try {
			String query = "delete from tesco.tescogroceries;";
			stmt = connect.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
//	public void insertDailyWeather(int id, DailyWeather dw){
//		try{
//			stmt = conn.createStatement();
//			String query = "INSERT INTO DAILYWEATHER (idDailyWeather, dateTime, maxTempC, minTempC, totalSnowCM, avgTempC, avgFeelsLIkeC, "
//					+ "windSpeedKmph, precipMM, avgHumidity, windChillC, windGustKmph) VALUES ("
//					+ id +", '" + dw.dateTime + "', " + dw.maxTempC + ", " + dw.minTempC + ", " + dw.totalSnowCM + ", " + dw.avgTempC + ", " 
//					+ dw.avgFeelsLikeC + ", " + dw.windSpeedKmph + ", " + dw.precipMM + ", " + dw.avgHumidity + ", " + dw.windChillC + ", " + dw.windGustkmph + ")";
//			
//			System.out.println(query);
//			stmt.executeUpdate(query);
//			
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}		
//	}
	
	
}

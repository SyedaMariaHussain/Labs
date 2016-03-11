/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geo_database;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
/**
 *
 * @author kkumar.bscs13seecs
 */
public class Geo_database {

    /**
     * @param args the command line arguments
     */
     // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost";
   
   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        // TODO code application logic here
          Connection conn = null;
          Statement stmt = null;
        
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
             stmt = conn.createStatement();
            String sql = "CREATE DATABASE if not exists GeoLiteCity_Location;";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
            String sql1 = "USE  GeoLiteCity_Location;" ;
            ResultSet rs = stmt.executeQuery(sql1);
            System.out.println("Creating table in given database...");
            
             String sql2 = "CREATE TABLE if not exists GeoPos " +
                   "(locId INTEGER not NULL, " +
                   " country VARCHAR(255), " + 
                   " region VARCHAR(255), " + 
                   "city VARCHAR(255), " + 
                     "postalcode VARCHAR(255),"+
                     "latitude DECIMAL,"+
                     "longitude DECIMAL,"+
                     "metroCode VARCHAR(255),"+
                     "areaCode VARCHAR(255),"+
                   " PRIMARY KEY ( locId ))"; 

      stmt.executeUpdate(sql2);
             
            String csvFile = "GeoLiteCity-Location.csv";
            BufferedReader br = null;
            String line = "";
            String splitBy = ",";
              br = new BufferedReader(new FileReader(csvFile));  
            while ((line = br.readLine()) != null) {  
  
             String[] db_object = line.split(splitBy);  
              
             
                
            String sql3 = "INSERT INTO GeoPos " +
                   "VALUES (" +db_object[0] +","+db_object[1]+","+db_object[2]+","+db_object[3]+","+db_object[4]+","+db_object[5]+","+db_object[6]+","+db_object[7]+","+db_object[8]+")";
            
            stmt.executeUpdate(sql3);
            
            int choice;
             
             Scanner in = new Scanner(System.in);
              System.out.println("Enter \n 1: Search by City \n 2:Search by long/lat ");
                 choice = in.nextInt();
                 
                 if(choice == 1)
                 {
                     String city;
                     System.out.println("Enter City:  ");
                    city = in.nextLine();
                     
                     String sql4 = "SELECT city, latitude, longitude FROM GeoPos where city like "+city+";";
                     
                        ResultSet rs1 = stmt.executeQuery(sql4);
                        ResultSetMetaData rsmd = rs1.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
    while (rs1.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = rs.getString(i);
            System.out.print(columnValue + " " + rsmd.getColumnName(i));
        }
        System.out.println("");
    }
                        
                 }
                 else
                 {
                      float longitude;
                     System.out.println("Enter longitude:  ");
                     longitude = in.nextFloat();
                     
                 }


        
        
        
        
        
    }  
    }
}

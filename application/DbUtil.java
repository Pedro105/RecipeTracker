package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



//Establishes connection to the database
public class DbUtil {
	
    private static Connection connection ;
	
	 public static Connection getConnect (){
	        try {
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipememories","root","Hellofriend1");
	        } catch (SQLException ex) {
	        	Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
	        }
	            
	            return  connection;
	        }
	//Used for displaying data from DB in the Recipe Table
	 public static ObservableList<Recipe> getRecipeData(){
	        Connection connection = getConnect();
	        ObservableList<Recipe> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select `RecipeID`,`RecipeName`,`RecipeMethod`,`RecipeIngredients`,`RecipeCategory`,`CategoryName` from `recipememories`.`tblrecipe` inner join `recipememories`.`tblcategory` on `RecipeCategory` = `CategoryID`");
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	         
	            while (resultSet.next()){   
	                list.add(new Recipe(Integer.parseInt(resultSet.getString("RecipeID")), resultSet.getString("RecipeName"),resultSet.getString("RecipeMethod"),
	                		resultSet.getString("RecipeIngredients"),Integer.parseInt(resultSet.getString("RecipeCategory")),resultSet.getString("CategoryName")));
	                
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return list;
	    }
	 
	//Used for displaying data from DB in the Category Table
	 public static ObservableList<Category> getCategoryData(){
	        Connection connection = getConnect();
	        ObservableList<Category> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement preparedStatement = connection .prepareStatement("select `CategoryID`,`CategoryName` "
	            		+ "from `recipememories`.`tblcategory`");
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while (resultSet.next()){   
	                list.add(new Category(Integer.parseInt(resultSet.getString("CategoryID")), resultSet.getString("CategoryName")));               
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return list;
	    }
	 
	 
	 //Used for displaying data from DB in the Friend Table
	 public static ObservableList<Friend> getFriendData(){
	        Connection connection = DbUtil.getConnect();
	        ObservableList<Friend> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement preparedStatement = connection .prepareStatement("select * from `recipememories`.`tblfriend`");
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while (resultSet.next()){   
	                list.add(new Friend(Integer.parseInt(resultSet.getString("FriendID")), resultSet.getString("FriendName")));               
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return list;
	    }
	 

}

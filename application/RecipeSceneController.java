package application;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;


public class RecipeSceneController {

	

    

    @FXML
    private TextArea RecipeIngredients;

    @FXML 
    private TextArea RecipeMethod;

    @FXML
    private Label RecipeTitle;;

    @FXML
    private ScrollPane scroll;

   
    
    private Stage stage;
	private Scene scene;
	String query = null;
	Connection connection = null ;
	PreparedStatement preparedStatement = null ;
	ResultSet resultSet = null ;
	Recipe selectedRecipe = null ;
	

	    
	public void initData(Recipe recipe) {
		selectedRecipe = recipe;
		RecipeTitle.setText(selectedRecipe.getRecipeName());
		RecipeMethod.setText(selectedRecipe.getRecipeMethod());
		RecipeIngredients.setText(selectedRecipe.getRecipeIngredients());
		
		
	}
		
	public void switchToRecipe(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Recipe.fxml"));
		stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCategory(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
		stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
	
	public void switchToFriend(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Friend.fxml"));
		stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToHelpMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("helpMenu.fxml"));
		stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}

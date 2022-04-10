package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddRecipeController {

	@FXML
	TextArea RecipeIngredients;

	@FXML
	TextArea RecipeMethod;

	@FXML
	TextField RecipeTitle;

	@FXML
	TextField category;

	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	ResultSet checkCategoryResultSet = null;
	PreparedStatement preparedStatement;
	Recipe recipe = null;
	private boolean update;
	int recipeID;
	PreparedStatement psCheckRecipeExists = null;
	PreparedStatement psCheckCategoryExists = null;
	RecipeController recipeController ;

	@FXML
	void saveEdit(MouseEvent event) throws SQLException {
		
		connection = DbUtil.getConnect();
		String ingredients = RecipeIngredients.getText();
		String method = RecipeMethod.getText();
		String title = RecipeTitle.getText();
		String cat = category.getText();

		// gathering all information from recipe table where recipe name is equal to the entered value.
		psCheckRecipeExists = connection.prepareStatement("SELECT * FROM tblRecipe WHERE RecipeName = ?");
		psCheckRecipeExists.setString(1, title);
		resultSet = psCheckRecipeExists.executeQuery();
		//gather all the information from category table where category ID is equal to value entered
		psCheckCategoryExists = connection.prepareStatement("SELECT * FROM tblcategory WHERE CategoryID = ?");
		psCheckCategoryExists.setString(1, cat);
		checkCategoryResultSet = psCheckCategoryExists.executeQuery();

		// method isBeforeFirst() are used to determine if the entered value is already in the database or not
		// method isEmpty() determines whether the text areas are all filled in
		if (ingredients.isEmpty() || method.isEmpty() || title.isEmpty() || cat.isEmpty()) {
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("please fill in all fields");
			al.showAndWait();
			//if value entered is already in table, give an elert
		} else if (resultSet.isBeforeFirst()) {
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Recipe title already exists");
			al.showAndWait();
			//if value entered is not in table, give an elert
		} else if (checkCategoryResultSet.next() == false) {
			Alert al = new Alert(Alert.AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Category does not exist");
			al.showAndWait();
		} else {
			Query();
			Insertion();
			
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		}
	}

	//method is used to determine the query to be executed
	private void Query() {
		
		
		if (update == false) {
			query = "INSERT INTO `recipememories`.`tblrecipe` (`RecipeName`, `RecipeMethod`, "
					+ "`RecipeIngredients`,`RecipeCategory`) VALUES (?,?,?,?);";
		} else {

			query = "UPDATE `recipememories`.`tblrecipe` SET "
					+ "RecipeName=?, RecipeMethod=?,RecipeIngredients=?,RecipeCategory=? WHERE RecipeID = '"
					+ recipeID + "'";
		}
	}
	//method used to insert the values entered in the GUI to the query to be executed.
	private void Insertion() {
		try {
			//sets the prepares statement to the query obtained in query method
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, RecipeTitle.getText());
			preparedStatement.setString(2, RecipeMethod.getText());
			preparedStatement.setString(3, RecipeIngredients.getText());
			preparedStatement.setString(4, category.getText());

			preparedStatement.execute();

		} catch (SQLException ex) {
			Logger.getLogger(AddRecipeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setUpdate(boolean b) {
		this.update = b;

	}

	public void setTextField(int id, String name, String m, String I) {

		this.recipeID = id;
		RecipeTitle.setText(name);
		RecipeMethod.setText(m);
		RecipeIngredients.setText(I);
	
	}

}

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RecipeController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txt_id;

	@FXML
	TableColumn<Recipe, Integer> idCol;
	
	@FXML
    TableColumn<Recipe, String> categoryCol;

	@FXML
	TableColumn<Recipe, String> nameCol;
	
	 @FXML
	 TableColumn<Recipe, String> ingredientsCol;

	@FXML
	TableColumn<Recipe, String> methodCol;


	@FXML
	TableView<Recipe> recipesTable;

	ObservableList<Recipe> recipeList;
	ObservableList<Recipe> dataList;
	

	Recipe recipe;
	AddRecipeController addRecipeController;

	int index = -1;

	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;

	private Stage stage;
	private Scene scene;

	@FXML
	void delete(ActionEvent event) {
		connection = DbUtil.getConnect();
		query = "delete from `recipememories`.`tblrecipe` where RecipeID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, txt_id.getText());
			preparedStatement.execute();
			update();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		idCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("RecipeID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("RecipeName"));
		methodCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("RecipeMethod"));
		ingredientsCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("RecipeIngredients"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("CategoryName"));

		recipeList = DbUtil.getRecipeData();
		recipesTable.setItems(recipeList);
	}
	
	//this method updates the data in the database
	public void UpdateDB(ActionEvent event) throws IOException {
		recipe = recipesTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/application/AddRecipe.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        AddRecipeController addRecipeController = loader.getController();
        //setUpdate method is set as true, which will set query as update query in AddRecipeController
        addRecipeController.setUpdate(true);
        //The text areas of AddRecipeController are set as the values in the selceted row of the table view
        addRecipeController.setTextField(recipe.getRecipeID(), recipe.getRecipeName(), recipe.getRecipeMethod(), recipe.getRecipeIngredients());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
		
	}

	@FXML
	void getSelected(MouseEvent event) {

		index = recipesTable.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		txt_id.setText(idCol.getCellData(index).toString());

	}

	@FXML
	void showRecipe(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/RecipeScene.fxml"));
		Parent p = loader.load();
		Scene showRecipeInfoScene = new Scene(p);
		RecipeSceneController controller = loader.getController();
		controller.initData(recipesTable.getSelectionModel().getSelectedItem());
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		
		stage.setScene(showRecipeInfoScene);
		stage.show();
		
		
	}
	//changes stage to category menu
	public void switchToCategory(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	//changes stage to friend menu
	public void switchToFriend(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Friend.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	//changes stage to help menu
	public void switchToHelpMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("helpMenu.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchToEditScene(ActionEvent event) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/Application/AddRecipe.fxml"));
			Scene scene = new Scene(parent, 1200, 600);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void switchToRecipe(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Recipe.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		update();

	}

}

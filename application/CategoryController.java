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


public class CategoryController implements Initializable {

	@FXML
	private TableColumn<Category, String> CategoryCol;

	@FXML
	private TextField txt_id;

	@FXML
	private TableView<Category> categories;

	@FXML
	private TableColumn<Category, Integer> idCol;

	ObservableList<Category> categoryList;
	ObservableList<Category> dataList;

	@FXML
	private TextField Category;

	int index = -1;

	private Stage stage;
	private Scene scene;

	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Category category = null;
	int CategoryID;

	public void addCategory() {
		connection = DbUtil.getConnect();
		String query = "insert into `recipememories`.`tblcategory` (CategoryName)values(?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Category.getText());
			preparedStatement.execute();

			update();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void update() {
		idCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("CategoryID"));
		CategoryCol.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryName"));

		categoryList = DbUtil.getCategoryData();
		categories.setItems(categoryList);
	}

	public void UpdateDB(ActionEvent event) {
		try {
			connection = DbUtil.getConnect();
			String value1 = txt_id.getText();
			String value2 = Category.getText();

			query = "update `recipememories`.`tblcategory` set CategoryID= '" + value1 + "',CategoryName= '" + value2+"' where CategoryID = '"+value1+"'";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();

			update();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void Delete() {
		connection = DbUtil.getConnect();
		query = "delete from `recipememories`.`tblcategory` where CategoryID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, txt_id.getText());
			preparedStatement.execute();
			update();

		} catch (Exception e) {
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

	public void switchToFriend(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Friend.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToHelpMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("helpMenu.fxml"));
		stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void getSelected(MouseEvent event) {
		index = categories.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		txt_id.setText(idCol.getCellData(index).toString());
		Category.setText(CategoryCol.getCellData(index).toString());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		update();

	}

}

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



public class FriendController implements Initializable {

	@FXML
	private TableColumn<Friend, String> nameCol;

	@FXML
	private TextField txt_id;

	@FXML
	private TableView<Friend> FriendTable;

	@FXML
	private TableColumn<Friend, Integer> idCol;

	ObservableList<Friend> friendList;
	ObservableList<Friend> dataList;

	@FXML
	private TextField Friend;

	int index = -1;

	private Stage stage;
	private Scene scene;

	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Friend category = null;
	int CategoryID;

	public void addFriend() {
		connection = DbUtil.getConnect();
		String query = "insert into `recipememories`.`tblfriend` (FriendName)values(?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Friend.getText());
			preparedStatement.execute();

			update();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		idCol.setCellValueFactory(new PropertyValueFactory<Friend, Integer>("FriendID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Friend, String>("FriendName"));

		friendList = DbUtil.getFriendData();
		FriendTable.setItems(friendList);
	}

	public void UpdateDB(ActionEvent event) {
		try {
			connection = DbUtil.getConnect();
			String value1 = txt_id.getText();
			String value2 = Friend.getText();

			query = "update `recipememories`.`tblfriend` set FriendID= '" + value1 + "',FriendName= '" + value2 + "' where FriendID = '"+value1+"'";
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
		query = "delete from `recipememories`.`tblfriend` where FriendID = ?";
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

	public void switchToCategory(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
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
		index = FriendTable.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		txt_id.setText(idCol.getCellData(index).toString());
		Friend.setText(nameCol.getCellData(index).toString());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		update();

	}

}

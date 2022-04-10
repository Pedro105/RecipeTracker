package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpMenuController {
	
	private Stage stage;
	private Scene scene;

	
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

}

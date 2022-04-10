package application;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IconController {
	  @FXML
	  private Label Title;

	  @FXML
	  private ImageView image;
	  
	  Stage stage;
	  Scene scene;
	  Recipe recipe;

	  @FXML
	  void switchToRecipe(MouseEvent event) throws IOException {
		  
		  Parent root = FXMLLoader.load(getClass().getResource("RecipeScene.fxml"));
		  stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();

	    }
	  
	 

}

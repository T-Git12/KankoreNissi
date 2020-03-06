package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreatSecondWindow {

	public void DropReport() throws IOException{
		Stage stage = new Stage();
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DropReport.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("ドロップ報告書");
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	//他のボタンで作成されるウィンドウのメソッドも追加

}

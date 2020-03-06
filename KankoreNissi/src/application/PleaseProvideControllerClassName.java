package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.io.*;

import java.net.URI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PleaseProvideControllerClassName implements Initializable{
	

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableView<DropRecordData> DropTeble1;
    @FXML private TableColumn<DropRecordData, CheckBox> select;
    @FXML private TableColumn<DropRecordData, String> InDateAndTime;
    @FXML private TableColumn<DropRecordData, String> InSeaArea;
    @FXML private TableColumn<DropRecordData, String> InSquare;
    @FXML private TableColumn<DropRecordData, String> InRank;
    @FXML private TableColumn<DropRecordData, String> InDropShip;
    @FXML private Button DropImport;
    @FXML private Button DropReport;
    @FXML private Button Expedition;
    @FXML private Button Recipe;
    @FXML private TextField TextSeaArea;
    @FXML private TextField TextSquare;
    @FXML private TextField TextRank;
    @FXML private TextField TextDropShip;
    @FXML private Button importButton;
    @FXML private Button exportButton;
    public static ObservableList<DropRecordData> data = FXCollections.observableArrayList();

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle bundle) {
      data = FXCollections.observableArrayList();
      DropTeble1.itemsProperty().setValue(data);
      DropTeble1.setItems(data);
      InDateAndTime.setCellValueFactory(new PropertyValueFactory<DropRecordData, String>("DateAndTime"));
      InSeaArea.setCellValueFactory(new PropertyValueFactory<DropRecordData, String>("SeaArea"));
      InSquare.setCellValueFactory(new PropertyValueFactory<DropRecordData, String>("Square"));
      InRank.setCellValueFactory(new PropertyValueFactory<DropRecordData, String>("Rank"));
      InDropShip.setCellValueFactory(new PropertyValueFactory<DropRecordData, String>("DropShip"));
      select.setCellValueFactory(new PropertyValueFactory<DropRecordData, CheckBox>("CheckBox"));
    } 
    

    @FXML
    void onDropReport(ActionEvent event) {
    	try {
    		Stage stage = new Stage();
    		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DropReport.fxml"));
    		Scene scene = new Scene(root);
    		stage.setTitle("ドロップ報告書");
    		stage.setScene(scene);
    		stage.showAndWait();
    	}catch(Exception ex) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText("ドロップデータが存在しません");
    		alert.setContentText("ドロップデータを追加またはインポートしてください");
    		alert.showAndWait();
    	}

    }

    @FXML
    void onExpedition(ActionEvent event) {
    	try {
    		Stage stage = new Stage();
    		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Expedition.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("遠征データ");
			stage.setScene(scene);
			stage.showAndWait();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}

    }

    @FXML
    void onImportButtonAction(ActionEvent e) {
    	data.add(new DropRecordData(Date.getDate(), TextSeaArea.getText(), TextSquare.getText(), TextRank.getText(), TextDropShip.getText(), AddCheckBox.addCheckBox()));
    	TextSquare.setText("");
    	TextRank.setText("");
    	TextDropShip.setText("");
    }

    @FXML
    void onRecipe(ActionEvent event) {
    	//ブラウザを開く
    	String uriString="https://wantora.github.io/kancolle-recipe-generator/";
    	Desktop desktop = Desktop.getDesktop();
    	try {
    		URI uri = new URI(uriString);
    		desktop.browse(uri);
    	}catch(Exception e){
    		 e.getStackTrace();
    	}
    }
    
    @FXML
    private void DeletSelectedRow(ActionEvent e) {
    	for(DropRecordData dt : DropTeble1.getItems()) {
    		if(dt.getCheckBox().isSelected()) {
    			Platform.runLater(() -> {
    				DropTeble1.getItems().remove(dt);
    			});
    		}
    	}
    }
    
    @FXML
    void onCSVExport(ActionEvent event) {
    	 FileChooser fc = new FileChooser();
         fc.setTitle("ファイル選択");
         fc.getExtensionFilters().addAll(
           new FileChooser.ExtensionFilter("CSVファイル", "*.csv", "*.CSV"),
           new FileChooser.ExtensionFilter("すべてのファイル", "*.*")
         );
         File file = fc.showSaveDialog(null);
         if(file == null) return;
         try {
         FileWriter f = new FileWriter(file.getPath());
         PrintWriter p = new PrintWriter(new BufferedWriter(f));
    	 int index = 0;
         for(Object i : data) {
        	 p.print(data.get(index).getDateAndTime());
        	 p.print(",");
        	 p.print(data.get(index).getSeaArea());
        	 p.print(",");
        	 p.print(data.get(index).getSquare());
        	 p.print(",");
        	 p.print(data.get(index).getRank());
        	 p.print(",");
        	 p.print(data.get(index).getDropShip());
        	 p.println();
        	 index++;
         }
         p.close();
         }catch(IOException e) {
        	 e.getStackTrace();
         }

    }

    @FXML
    void onCSVImport(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("ファイル選択");
        fc.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSVファイル", "*.csv", "*.CSV"),
          new FileChooser.ExtensionFilter("すべてのファイル", "*.*")
        );
        File file = fc.showOpenDialog(null);
        if(file == null) return;
        //おそらく元からテーブルに表示されているデータを消去
    	for(Object dt : data) {
   			Platform.runLater(() -> {
    		data.remove(dt);
   			});
    	}
        //csvファイル読み込み
        try {
          File f = new File(file.getPath());
          BufferedReader br = new BufferedReader(new FileReader(f));
          String line;
          while((line = br.readLine()) != null) {
        	  String[] csvdata = line.split(",", -1);
        	  data.add(new DropRecordData(csvdata[0], csvdata[1], csvdata[2], csvdata[3], csvdata[4], AddCheckBox.addCheckBox()));
          }
          br.close();
        	  
        }catch(IOException e) {
        	System.out.println(e);
        }
    }

    @FXML
    void initialize() {
        assert DropTeble1 != null : "fx:id=\"DropTeble1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert select != null : "fx:id=\"select\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert InDateAndTime != null : "fx:id=\"InDateAndTime\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert InSeaArea != null : "fx:id=\"InSeaArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert InSquare != null : "fx:id=\"InSquare\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert InRank != null : "fx:id=\"InRank\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert InDropShip != null : "fx:id=\"InDropShip\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert DropImport != null : "fx:id=\"DropImport\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert DropReport != null : "fx:id=\"DropReport\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert Expedition != null : "fx:id=\"Expedition\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert Recipe != null : "fx:id=\"Recipe\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TextSeaArea != null : "fx:id=\"TextSeaArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TextSquare != null : "fx:id=\"TextSquare\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TextRank != null : "fx:id=\"TextRank\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TextDropShip != null : "fx:id=\"TextDropShip\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert importButton != null : "fx:id=\"importButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert exportButton != null : "fx:id=\"exportButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}
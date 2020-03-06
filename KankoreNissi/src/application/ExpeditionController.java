package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpeditionController implements Initializable{

    @FXML    private ResourceBundle resources;
    @FXML    private URL location;
    @FXML    private TableView<ExpeditionData> ExpeditionTable;
    @FXML    private TableColumn<ExpeditionData, String> No;
    @FXML    private TableColumn<ExpeditionData, String> ExpeditionName;
    @FXML    private TableColumn<ExpeditionData, String> Time;
    @FXML    private TableColumn<ExpeditionData, String> ShipLv;
    @FXML    private TableColumn<ExpeditionData, String> FlagShipLv;
    @FXML    private TableColumn<ExpeditionData, String> MinimumNumber;
    @FXML    private TableColumn<ExpeditionData, String> EssentialShip;
    @FXML    private TableColumn<ExpeditionData, String> Drum;
    @FXML    private TableColumn<ExpeditionData, String> Fuel;
    @FXML    private TableColumn<ExpeditionData, String> Ammunition;
    @FXML    private TableColumn<ExpeditionData, String> Steel;
    @FXML    private TableColumn<ExpeditionData, String> Bauxite;
    @FXML    private TableColumn<ExpeditionData, String> Repair;
    @FXML    private TableColumn<ExpeditionData, String> Construction;
    @FXML    private TableColumn<ExpeditionData, String> Development;
    @FXML    private TableColumn<ExpeditionData, String> FurnitureBox;
    @FXML    private TableColumn<ExpeditionData, String> FuelCon;
    @FXML    private TableColumn<ExpeditionData, String> AmmunitionCon;
    @FXML    private ChoiceBox<String> SeaAreaSelect;
    @FXML    private Label SeaArea;
    public static ObservableList<ExpeditionData> expeditiondata = FXCollections.observableArrayList();
    
    @Override    
    public void initialize(java.net.URL url, java.util.ResourceBundle bundle) {
    	expeditiondata =  FXCollections.observableArrayList();
    	ExpeditionTable.itemsProperty().setValue(expeditiondata);
    	ExpeditionTable.setItems(expeditiondata);
    	No.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("No"));
    	ExpeditionName.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("ExpeditionName"));
    	Time.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Time"));
    	ShipLv.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("ShipLv"));
    	FlagShipLv.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("FlagShipLv"));
    	MinimumNumber.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("MinimumNumber"));
    	EssentialShip.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("EssentialShip"));
    	Drum.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Drum"));
    	Fuel.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Fuel"));
    	Ammunition.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Ammunition"));
    	Steel.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Steel"));
    	Bauxite.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Bauxite"));
    	Repair.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Repair"));
    	Construction.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Construction"));
    	Development.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("Development"));
    	FurnitureBox.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("FurnitureBox"));
    	FuelCon.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("FuelCon"));
    	AmmunitionCon.setCellValueFactory(new PropertyValueFactory<ExpeditionData, String>("AmmunitionCon"));
    	
    	SeaAreaSelect.getItems().add("í¡éÁï{äCàÊ");
    	SeaAreaSelect.getItems().add("ìÏêºèîìáäCàÊ");
    }
    
    @FXML
    void onSeaAreaSelect(ActionEvent event) {
    	String seaAreaSelect = SeaAreaSelect.getValue();
    	for(Object dt : expeditiondata) {
   			Platform.runLater(() -> {
   				expeditiondata.remove(dt);
   			});
    	}
    	
    	if(seaAreaSelect.contentEquals("í¡éÁï{äCàÊ")) {
        	//CSVì«Ç›çûÇ›
        	try {
        		File f = new File("tinnzyuhu.csv");
        		BufferedReader br = new BufferedReader(new FileReader(f));
        		String line;
        		while((line = br.readLine()) != null) {
        			String[] csvdata = line.split(",", -1);
        			expeditiondata.add(new ExpeditionData(csvdata[0], csvdata[1], csvdata[2], csvdata[3], csvdata[4], csvdata[5], csvdata[6], csvdata[7], csvdata[8], csvdata[9], 
        										csvdata[10], csvdata[11], csvdata[12], csvdata[13], csvdata[14], csvdata[15], csvdata[16], csvdata[17]));
        		}
        		br.close();
        	}catch(IOException e) {
        		System.out.println(e);
        	}
    	}else if(seaAreaSelect.contentEquals("ìÏêºèîìáäCàÊ")) {
        	//CSVì«Ç›çûÇ›
        	try {
        		File f = new File("nanseisyotou.csv");
        		BufferedReader br = new BufferedReader(new FileReader(f));
        		String line;
        		while((line = br.readLine()) != null) {
        			String[] csvdata = line.split(",", -1);
        			expeditiondata.add(new ExpeditionData(csvdata[0], csvdata[1], csvdata[2], csvdata[3], csvdata[4], csvdata[5], csvdata[6], csvdata[7], csvdata[8], csvdata[9], 
        										csvdata[10], csvdata[11], csvdata[12], csvdata[13], csvdata[14], csvdata[15], csvdata[16], csvdata[17]));
        		}
        		br.close();
        	}catch(IOException e) {
        		System.out.println(e);
        	}
    	}
    	

    }

    @FXML
    void initialize() {
        assert ExpeditionTable != null : "fx:id=\"ExpeditionTable\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert No != null : "fx:id=\"No\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert ExpeditionName != null : "fx:id=\"ExpeditionName\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Time != null : "fx:id=\"Time\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert ShipLv != null : "fx:id=\"ShipLv\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert FlagShipLv != null : "fx:id=\"FlagShipLv\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert MinimumNumber != null : "fx:id=\"MinimumNumber\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert EssentialShip != null : "fx:id=\"EssentialShip\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Drum != null : "fx:id=\"Drum\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Fuel != null : "fx:id=\"Fuel\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Ammunition != null : "fx:id=\"Ammunition\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Steel != null : "fx:id=\"Steel\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Bauxite != null : "fx:id=\"Bauxite\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Repair != null : "fx:id=\"Repair\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Construction != null : "fx:id=\"Construction\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert Development != null : "fx:id=\"Development\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert FurnitureBox != null : "fx:id=\"FurnitureBox\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert FuelCon != null : "fx:id=\"FuelCon\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert AmmunitionCon != null : "fx:id=\"AmmunitionCon\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert SeaAreaSelect != null : "fx:id=\"SeaAreaSelect\" was not injected: check your FXML file 'Expedition.fxml'.";
        assert SeaArea != null : "fx:id=\"SeaArea\" was not injected: check your FXML file 'Expedition.fxml'.";

    }
}

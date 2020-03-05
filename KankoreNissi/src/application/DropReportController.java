package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class DropReportController implements Initializable{
	
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableView<DropReportData> DropReportTable;
    @FXML private TableColumn<DropReportData, String> DropArea;
    @FXML private TableColumn<DropReportData, String> DropSquare;
    @FXML private TableColumn<DropReportData, String> DropShipName;
    @FXML private TableColumn<DropReportData, String> DropShipNumber;
    @FXML private ChoiceBox<String> ItemSelect;
    @FXML private Label SortieCount;
    @FXML private Label WithdrawalCount;
    @FXML private Label SRankWinCount;
    @FXML private Label ARankWinCount;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

            // チョイスボックスに項目を追加
    	ItemSelect.getItems().add("指定なし");
    	List<String> choicelist = new ArrayList<String>();
    	List<String> table = new ArrayList<String>();
    	
    	if(PleaseProvideControllerClassName.data != null) {
    		Comparator<DropRecordData> comparator = Comparator.comparing(DropRecordData::getSeaArea).thenComparing(DropRecordData::getSquare);
    		PleaseProvideControllerClassName.data.stream().sorted(comparator).forEach(i -> {table.add(i.getSeaArea());
    																																	table.add(i.getSquare()); });

   			String tempArea = table.get(0);
    		String tempSquare = table.get(1);
   			choicelist.add(tempArea);
   			choicelist.add(tempSquare);
		
   			for(int temp = 2; temp<table.size(); temp=temp+2) {

    			if(!(tempArea.equals(table.get(temp))) || !(tempSquare.equals(table.get(temp+1)))) {
    				tempArea = table.get(temp);
    				tempSquare = table.get(temp+1);
   					choicelist.add(tempArea);
    				choicelist.add(tempSquare);
    			}
    		}
    	
    		for(int i = 0; i<choicelist.size(); i=i+2) {
    			ItemSelect.getItems().add(choicelist.get(i) + " : " + choicelist.get(i+1) + "マス");
   			}
   		}
    }



	@FXML
    void initialize() {
        assert DropReportTable != null : "fx:id=\"DropReportTable\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert DropArea != null : "fx:id=\"DropArea\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert DropSquare != null : "fx:id=\"DropSquare\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert DropShipName != null : "fx:id=\"DropShipName\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert DropShipNumber != null : "fx:id=\"DropShipNumber\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert SortieCount != null : "fx:id=\"SortieCount\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert WithdrawalCount != null : "fx:id=\"WithdrawalCount\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert SRankWinCount != null : "fx:id=\"SRankWinCount\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert ARankWinCount != null : "fx:id=\"ARankWinCount\" was not injected: check your FXML file 'DropReport.fxml'.";
        assert ItemSelect != null : "fx:id=\"ItemSelect\" was not injected: check your FXML file 'DropReport.fxml'.";

    }

}

package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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
    @FXML private Button Decision;
    public static ObservableList<DropReportData> dropdata = FXCollections.observableArrayList();
    public static  ObservableList<DropReportData> tempdropdata = FXCollections.observableArrayList();


    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        
    	//���x���̏����ݒ�
        SortieCount.setText("0");
        //�P�ސ��̓����N�̒l���P�ނ̎����J�E���g����
        WithdrawalCount.setText("0");
        SRankWinCount.setText("0");
        ARankWinCount.setText("0");

    	//���X�g�̏����ݒ�
    	dropdata = FXCollections.observableArrayList();
    	DropReportTable.itemsProperty().setValue(dropdata);
    	DropReportTable.setItems(dropdata);
    	DropArea.setCellValueFactory(new PropertyValueFactory<DropReportData, String>("DropArea"));
    	DropSquare.setCellValueFactory(new PropertyValueFactory<DropReportData, String>("DropSquare"));
    	DropShipName.setCellValueFactory(new PropertyValueFactory<DropReportData, String>("DropShipName"));
    	DropShipNumber.setCellValueFactory(new PropertyValueFactory<DropReportData, String>("DropShipNumber"));
    	

            // �`���C�X�{�b�N�X�ɍ��ڂ�ǉ�
    	ItemSelect.getItems().add("�w��Ȃ�");
    	List<String> choicelist = new ArrayList<String>();
    	List<String> table = new ArrayList<String>();
    	
    	//table��SeaArea��Square�������ŕ��בւ������̂��i�[
    	if(PleaseProvideControllerClassName.data != null) {
    		Comparator<DropRecordData> comparator = Comparator.comparing(DropRecordData::getSeaArea).thenComparing(DropRecordData::getSquare);
    		PleaseProvideControllerClassName.data.stream().sorted(comparator).forEach(i -> {table.add(i.getSeaArea());
    																																	table.add(i.getSquare()); });
    		//�������ڂ𖳎����Ă��ׂĈႤ���ڂ�choicelist�Ɋi�[
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
    	//�`���C�X���X�g�ɕ\��
    		for(int i = 0; i<choicelist.size(); i=i+2) {
    			ItemSelect.getItems().add(choicelist.get(i) + " : " + choicelist.get(i+1) + "�}�X");
   			}
   		}
    }
    
    //�`���C�X���X�g�őI���������ڂɍ������f�[�^���e�[�u���ɕ\��
    @FXML
    void onDecision(ActionEvent event) {
    	//�J�E���g�̏����ݒ�
		int cntall = 0;
		int cntwd = 0;
		int cntSrank = 0;
		int cntArank = 0;
   
    	for(Object dt : dropdata) {
    		Platform.runLater(() -> {
    			dropdata.remove(dt);
    		});
    	}
    	tempdropdata.clear();
    	String value = ItemSelect.getValue(); 
    	String[] valuelist = value.split(" : ", 0);
    	String SeaArea = valuelist[0];
    	String Square = valuelist[1].replace("�}�X", "");
    	//�������ڂ̃f�[�^��ۑ����郊�X�g
    	for(int i=0; i<PleaseProvideControllerClassName.data.size(); i++) {
    		if(PleaseProvideControllerClassName.data.get(i).getSeaArea().equals(SeaArea) && 
    				PleaseProvideControllerClassName.data.get(i).getSquare().equals(Square)) {
    			//�Ō��getRank�͓P�ނ������̃J�E���g�̂��߂ɐǐ��̂Ƃ���Ɉ�U����Ă�
    			tempdropdata.add(new DropReportData(PleaseProvideControllerClassName.data.get(i).getSeaArea(),PleaseProvideControllerClassName.data.get(i).getSquare(),
    														PleaseProvideControllerClassName.data.get(i).getDropShip(), PleaseProvideControllerClassName.data.get(i).getRank()));
    			if(PleaseProvideControllerClassName.data.get(i).getRank().contentEquals("S")) {
    				cntSrank++;
    			}else if(PleaseProvideControllerClassName.data.get(i).getRank().contentEquals("A")) {
    				cntArank++;
    			}
    		}
    	}
    	//Map���g����DropShipName���������̂��J�E���g
    	Map<String, Integer> m = new HashMap<>();
		int v = 0;
    	for(DropReportData i : tempdropdata) {
    		String s;
    		if(i.getDropShipNumber().equals("�P��")) {
    			s = "�P��";
    			v +=1;
    			cntall++;
    			cntwd++;
    		}else if(m.containsKey(i.getDropShipName())) {
    			s = i.getDropShipName();
    			v = m.get(i.getDropShipName()) + 1;
    			cntall++;
    		}else {
    			s = i.getDropShipName();
    			v = 1;
    			cntall++;
    		}
    		m.put(s, v);
    	}
    	for(Entry<String, Integer> entry: m.entrySet()) {
    		String mapvalue = String.valueOf(entry.getValue());
    		dropdata.add(new DropReportData(tempdropdata.get(0).getDropArea(), tempdropdata.get(0).getDropSquare(), entry.getKey(), mapvalue));
    	}
    	SortieCount.setText(String.valueOf(cntall));
    	WithdrawalCount.setText(String.valueOf(cntwd));
    	SRankWinCount.setText(String.valueOf(cntSrank));
    	ARankWinCount.setText(String.valueOf(cntArank));
    	
    	
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

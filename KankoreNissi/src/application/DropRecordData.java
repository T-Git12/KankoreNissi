package application;

import javafx.scene.control.CheckBox;

/*mainのテーブルデータのゲッターセッター*/

public class DropRecordData {
	private String DateAndTime;
	private String SeaArea;
	private String Square;
	private String Rank;
	private String DropShip;
	private CheckBox CheckBox;
	
	public DropRecordData(String DateAndTime, String SeaArea, String Square, String Rank, String DropShip, CheckBox CheckBox) {
		this.DateAndTime = DateAndTime;
		this.SeaArea = SeaArea;
		this.Square = Square;
		this.Rank = Rank;
		this.DropShip = DropShip;
		this.CheckBox = CheckBox;
	}
	
	public CheckBox getCheckBox() { return CheckBox; }
	public String getDateAndTime() { return DateAndTime; }
	public String getSeaArea() { return SeaArea; }
	public String getSquare() { return Square; }
	public String getRank() { return Rank; }
	public String getDropShip() { return DropShip; }
	public void setCheckBox(CheckBox CheckBox) { this.CheckBox = CheckBox; }
	public void setDateAndTime(String DateAndTime) { this.DateAndTime = DateAndTime; }
	public void setSeaArea(String SeaArea) { this.SeaArea = SeaArea; }
	public void setSquare(String Square) { this.Square = Square; }
	public void setRank(String Rank) { this.Rank = Rank; }
	public void setDropShip(String DropShip) { this.DropShip = DropShip; }

}


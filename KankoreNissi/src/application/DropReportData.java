package application;

public class DropReportData {
		private String DropArea;
		private String DropSquare;
		private String DropShipName;
		private String DropShipNumber;
		
		public DropReportData(String DropArea, String DropSquare, String DropShipName, String DropShipNumber) {
			this.DropArea = DropArea;
			this.DropSquare = DropSquare;
			this.DropShipName = DropShipName;
			this.DropShipNumber = DropShipNumber;
		}
		
		public String getDropArea() {
			return DropArea;
		}

		public void setDropArea(String dropArea) {
			DropArea = dropArea;
		}

		public String getDropSquare() {
			return DropSquare;
		}

		public void setDropSquare(String dropSquare) {
			DropSquare = dropSquare;
		}

		public String getDropShipName() {
			return DropShipName;
		}

		public void setDropShipName(String dropShipName) {
			DropShipName = dropShipName;
		}

		public String getDropShipNumber() {
			return DropShipNumber;
		}

		public void setDropShipNumber(String dropShipNumber) {
			DropShipNumber = dropShipNumber;
		}


}

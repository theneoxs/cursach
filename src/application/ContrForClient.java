package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContrForClient {
	@FXML private TableView<client> tvclient;
	@FXML private TableColumn<client, Integer> tcidclient;
	@FXML private TableColumn<client, String> tcName;
	@FXML private TableColumn<client, String> tcSurname;
	@FXML private TableColumn<client, Date> tcDate_of_autorization;
	@FXML private TableColumn<client, Integer> tcAccess_level;
	@FXML private TableColumn<client, String> tcInfo;
	
	@FXML private TextField tfidclient;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfDate_of_autorization;
	@FXML private TextField tfAccess_level;
	@FXML private TextField tfInfo;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	
	@FXML
	private void initialize() throws IOException {
		System.out.println("yeas");
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        lvl.close();
        tcidclient.setCellValueFactory(new PropertyValueFactory<client, Integer>("idclient")); //1 �������
        tcName.setCellValueFactory(new PropertyValueFactory<client, String>("Name")); //2 �������
        tcSurname.setCellValueFactory(new PropertyValueFactory<client, String>("Surname")); //3 �������
        tcDate_of_autorization.setCellValueFactory(new PropertyValueFactory<client, Date>("Date_of_autorization")); //4 �������
        tcAccess_level.setCellValueFactory(new PropertyValueFactory<client, Integer>("Access_level"));
        tcInfo.setCellValueFactory(new PropertyValueFactory<client, String>("Info"));
        tvclient.setItems(FXCollections.observableArrayList(db.getAllClient())); //������������� ������������� ������� �� ��������� Tech � �������� ��������������� ���������� � ��������
		
        tvclient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(client cl) throws IOException{
		if (cl != null) {
			tfidclient.setText(Integer.toString(cl.getIdclient()));
			tfName.setText(cl.getName());
			tfSurname.setText(cl.getSurname());
			tfDate_of_autorization.setText(cl.getDate_of_autorization().toString());
			tfAccess_level.setText(Integer.toString(cl.getAccess_level()));
			tfInfo.setText(cl.getInfo());
			
		} else {
			tfidclient.setText("");
			tfName.setText("");
			tfSurname.setText("");
			tfDate_of_autorization.setText("");
			tfAccess_level.setText("");
			tfInfo.setText("");
			
		}
	}
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newClient(tfName.getText(),tfSurname.getText(),
					Date.valueOf(tfDate_of_autorization.getText()), Integer.parseInt(tfAccess_level.getText()),tfInfo.getText());
			tvclient.setItems(FXCollections.observableArrayList(db.getAllClient()));
		}
	}
	//������� ������� � id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delClient(Integer.parseInt(tfidclient.getText()));
			tvclient.setItems(FXCollections.observableArrayList(db.getAllClient()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updClient(Integer.parseInt(tfidclient.getText()),tfName.getText(),tfSurname.getText(),
					Date.valueOf(tfDate_of_autorization.getText()), Integer.parseInt(tfAccess_level.getText()),tfInfo.getText());
			tvclient.setItems(FXCollections.observableArrayList(db.getAllClient()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfDate_of_autorization.getText() == null || tfDate_of_autorization.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_of_autorization.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			
			if (tfAccess_level.getText() == null || tfAccess_level.getText().length() == 0) {
				errorMessage += "No valid level!\n";
			} else {
				try {
					Integer.parseInt(tfAccess_level.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfInfo.getText() == null || tfInfo.getText().length() == 0) {
				errorMessage += "No valid info!\n";
			} 
			
		}
		if (i == 2) {
			if (tfidclient.getText() == null || tfidclient.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidclient.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfidclient.getText() == null || tfidclient.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidclient.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfDate_of_autorization.getText() == null || tfDate_of_autorization.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_of_autorization.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			
			if (tfAccess_level.getText() == null || tfAccess_level.getText().length() == 0) {
				errorMessage += "No valid level!\n";
			} else {
				try {
					Integer.parseInt(tfAccess_level.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfInfo.getText() == null || tfInfo.getText().length() == 0) {
				errorMessage += "No valid info!\n";
			} 		
		}
	
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}
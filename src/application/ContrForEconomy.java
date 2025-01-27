package application;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContrForEconomy {
	@FXML private Label lNum;
	
	@FXML private TableView<Economy> tvEconomy;
	@FXML private TableColumn<Economy, String> tcType;
	@FXML private TableColumn<Economy, Float> tcStudent_scholarship;
	
	@FXML private TextField tfType;
	@FXML private TextField tfStudent_scholarship;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	
	private Database db = new Database();
	@FXML private String password;
	@FXML private String info;
	@FXML private String login;
	@FXML private Scanner scan;
	
	@FXML
	private void initialize() throws IOException {
		System.out.println("yeas");
		FileReader lvl= new FileReader("lvl");
        scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        login = scan.nextLine();
        password = scan.nextLine();
        info = scan.nextLine();
        String id = scan.nextLine();
        lvl.close();
        String text = id + ",  Access Level: ";
        if (level_accept.equals("0")) {
        	text += "Main Admin";
        }
        if (level_accept.equals("1")) {
        	text += "Student";
        }
        if (level_accept.equals("2")) {
        	text += "Student Manager";
        }
        if (level_accept.equals("3")) {
        	text += "Faculty Manager";
        }
        if (level_accept.equals("4")) {
        	text += "Finance Manager";
        }
        if (level_accept.equals("5")) {
        	text += "Administrator";
        }
        if (level_accept.equals("6")) {
        	text += "Organisation Manager";
        }
        if (level_accept.equals("7")) {
        	text += "Teacher";
        }
        
        lNum.setText(text);
        tcType.setCellValueFactory(new PropertyValueFactory<Economy, String>("Type")); //1 �������
        tcStudent_scholarship.setCellValueFactory(new PropertyValueFactory<Economy, Float>("Student_scholarship")); //2 �������
        tvEconomy.setItems(FXCollections.observableArrayList(db.getAllEconomy())); //������������� ������������� ������� �� ��������� Tech � �������� ��������������� ���������� � ��������
		
        tvEconomy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Economy cl) throws IOException{
		if (cl != null) {
			tfType.setText(cl.getType());
			tfStudent_scholarship.setText(Float.toString(cl.getStudent_scholarship()));
			
		} else {
			tfType.setText("");
			tfStudent_scholarship.setText("");
			
		}
	}
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newEconomy(tfType.getText(), Float.parseFloat(tfStudent_scholarship.getText()));
			tvEconomy.setItems(FXCollections.observableArrayList(db.getAllEconomy()));
		}
	}
	//������� ������� � id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delEconomy(tfType.getText());
			tvEconomy.setItems(FXCollections.observableArrayList(db.getAllEconomy()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updEconomy(tfType.getText(), Float.parseFloat(tfStudent_scholarship.getText()));
			tvEconomy.setItems(FXCollections.observableArrayList(db.getAllEconomy()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1 || i == 3) {
			if (tfType.getText() == null || tfType.getText().length() == 0) {
				errorMessage += "No valid type!\n";
			}
			if (tfStudent_scholarship.getText() == null || tfStudent_scholarship.getText().length() == 0) {
				errorMessage += "No valid money!\n";
			}else {
				try {
					Float.parseFloat(tfStudent_scholarship.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 2) {
			if (tfType.getText() == null || tfType.getText().length() == 0) {
				errorMessage += "No valid type!\n";
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

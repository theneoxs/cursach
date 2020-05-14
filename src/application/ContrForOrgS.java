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

public class ContrForOrgS {
	@FXML private Label lNum;
	
	@FXML private TableView<OrgStudent> tvOrgStudent;
	@FXML private TableColumn<OrgStudent, Integer> tcOrganization_idOrganization;
	@FXML private TableColumn<OrgStudent, Integer> tcStudent_idStudent;
	
	@FXML private TextField tfOrganization_idOrganization;
	@FXML private TextField tfStudent_idStudent;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	
	private Database db = new Database();
	
	@FXML
	private void initialize() throws IOException {
		System.out.println("yeas");
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        String info = scan.nextLine();
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
        tcOrganization_idOrganization.setCellValueFactory(new PropertyValueFactory<OrgStudent, Integer>("Organization_idOrganization")); //1 столбик
        tcStudent_idStudent.setCellValueFactory(new PropertyValueFactory<OrgStudent, Integer>("Student_idStudent")); //2 столбик
        tvOrgStudent.setItems(FXCollections.observableArrayList(db.getAllOrgS())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
        tvOrgStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(OrgStudent cl) throws IOException{
		if (cl != null) {
			tfOrganization_idOrganization.setText(Integer.toString(cl.getOrganization_idOrganization()));
			tfStudent_idStudent.setText(Integer.toString(cl.getStudent_idStudent()));
			
		} else {
			tfOrganization_idOrganization.setText("");
			tfStudent_idStudent.setText("");
			
		}
	}
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid()) {
			db.newOrgS(Integer.parseInt(tfOrganization_idOrganization.getText()), Integer.parseInt(tfStudent_idStudent.getText()));
			tvOrgStudent.setItems(FXCollections.observableArrayList(db.getAllOrgS()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid()) {
			db.delOrgS(Integer.parseInt(tfOrganization_idOrganization.getText()), Integer.parseInt(tfStudent_idStudent.getText()));
			tvOrgStudent.setItems(FXCollections.observableArrayList(db.getAllOrgS()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid()) {
			db.updOrgS(Integer.parseInt(tfOrganization_idOrganization.getText()), Integer.parseInt(tfStudent_idStudent.getText()));
			tvOrgStudent.setItems(FXCollections.observableArrayList(db.getAllOrgS()));
		}
	}
	private boolean isInputValid() {
		String errorMessage = "";
		if (tfOrganization_idOrganization.getText() == null || tfOrganization_idOrganization.getText().length() == 0) {
			errorMessage += "No valid ID Org!\n";
		}else {
			try {
				Float.parseFloat(tfOrganization_idOrganization.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Format is not a number!\n";
			}
		}
		if (tfStudent_idStudent.getText() == null || tfStudent_idStudent.getText().length() == 0) {
			errorMessage += "No valid ID Student!\n";
		} else {
			try {
				Float.parseFloat(tfStudent_idStudent.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Format is not a number!\n";
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

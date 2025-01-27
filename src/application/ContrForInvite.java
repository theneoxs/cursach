package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
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

public class ContrForInvite {
	@FXML private Label lNum;
	
	@FXML private TableView<Invite> tvInvite;
	@FXML private TableColumn<Invite, Integer> tcidInvite;
	@FXML private TableColumn<Invite, Integer> tcidStudent;
	@FXML private TableColumn<Invite, Integer> tcidOrganization;
	@FXML private TableColumn<Invite, String> tcMessage;
	@FXML private TableColumn<Invite, Date> tcdate;
	
	@FXML private TextField tfidInvite;
	@FXML private TextField tfidStudent;
	@FXML private TextField tfidOrganization;
	@FXML private TextField tfMessage;
	@FXML private TextField tfdate;
	@FXML private TextField tfFIO;
	@FXML private TextField tfName_of_org;
	
	@FXML private Button bAccept;
	@FXML private Button bDenied;
	
	private Database db = new Database();
	
	Student stud = null;
	Organization org = null;
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
        tcidInvite.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idInvite")); //1 �������
        tcidStudent.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idStudent")); //2 �������
        tcidOrganization.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idOrganization")); //3 �������
        tcMessage.setCellValueFactory(new PropertyValueFactory<Invite, String>("Message")); //4 �������
        tcdate.setCellValueFactory(new PropertyValueFactory<Invite, Date>("date"));
        tvInvite.setItems(FXCollections.observableArrayList(db.getAllInvite())); //������������� ������������� ������� �� ��������� Tech � �������� ��������������� ���������� � ��������
		
        tvInvite.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
				stud = db.getConcrStudent(Integer.toString(newValue.getIdStudent()));
				tfFIO.setText(stud.getSurname() + " " + stud.getName() + " " + stud.getMiddle_name());
				org = db.getConcrOrg(Integer.toString(newValue.getIdOrganization()));
				tfName_of_org.setText(org.getName_of_organization());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Invite cl) throws IOException{
		if (cl != null) {
			tfidInvite.setText(Integer.toString(cl.getIdInvite()));
			tfidStudent.setText(Integer.toString(cl.getIdStudent()));
			tfidOrganization.setText(Integer.toString(cl.getIdOrganization()));
			tfMessage.setText(cl.getMessage());
			tfdate.setText(cl.getDate().toString());
			
		} else {
			tfidInvite.setText("");
			tfidStudent.setText("");
			tfidOrganization.setText("");
			tfMessage.setText("");
			tfdate.setText("");
			tfFIO.setText("");
			tfName_of_org.setText("");
			
		}
	}
	@FXML
	private void handleAcc() throws IOException{
		System.out.println("Work!");
		if (isInputValid()) {
			db.acceptInvite(Integer.parseInt(tfidInvite.getText()),Integer.parseInt(tfidStudent.getText()),Integer.parseInt(tfidOrganization.getText()));
			tvInvite.setItems(FXCollections.observableArrayList(db.getAllInvite()));
		}
	}
	//������� ������� � id n 
	@FXML
	private void handleDen() throws IOException{
		if (isInputValid()) {
			db.deniedInvite(Integer.parseInt(tfidInvite.getText()));
			tvInvite.setItems(FXCollections.observableArrayList(db.getAllInvite()));
		}
	}
	private boolean isInputValid() {
		String errorMessage = "";

		if (tfidInvite.getText() == null || tfidInvite.getText().length() == 0) {
			errorMessage += "No valid student!\n";
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

package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
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

public class ContrForInvite {
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
	
	@FXML private Button bAccept;
	@FXML private Button bDenied;
	
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	
	@FXML
	private void initialize() throws IOException {
		System.out.println("yeas");
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        lvl.close();
        tcidInvite.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idInvite")); //1 столбик
        tcidStudent.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idStudent")); //2 столбик
        tcidOrganization.setCellValueFactory(new PropertyValueFactory<Invite, Integer>("idOrganization")); //3 столбик
        tcMessage.setCellValueFactory(new PropertyValueFactory<Invite, String>("Message")); //4 столбик
        tcdate.setCellValueFactory(new PropertyValueFactory<Invite, Date>("date"));
        tvInvite.setItems(FXCollections.observableArrayList(db.getAllInvite())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
        tvInvite.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Invite cl) throws IOException{
		if (cl != null) {
			tfidInvite.setText(Integer.toString(cl.getIdInvite()));
			tfidStudent.setText(Integer.toString(cl.getidStudent()));
			tfidOrganization.setText(Integer.toString(cl.getidOrganization()));
			tfMessage.setText(cl.getMessage());
			tfdate.setText(cl.getDate().toString());
			
		} else {
			tfidInvite.setText("");
			tfidStudent.setText("");
			tfidOrganization.setText("");
			tfMessage.setText("");
			tfdate.setText("");
			
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
	//удалить строчку с id n 
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

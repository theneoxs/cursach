package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ContfForOrg {
	@FXML private Label lNum;
	
	@FXML private TableView<Organization> tvOrganization;
	@FXML private TableColumn<Organization, Integer> tcidOrganization;
	@FXML private TableColumn<Organization, String> tcName_of_organization;
	@FXML private TableColumn<Organization, String> tcName_of_head_of_organization;
	@FXML private TableColumn<Organization, String> tcOrganization_direction;
	
	@FXML private TextField tfidOrganization;
	@FXML private TextField tfName_of_organization;
	@FXML private TextField tfName_of_head_of_organization;
	@FXML private TextField tfOrganization_direction;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	@FXML private Button bInv;
	private Database db = new Database();
	@FXML
	private void initialize() throws IOException {
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
        tcidOrganization.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("idOrganization")); //1 столбик
        tcName_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_organization")); //2 столбик
        tcName_of_head_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_head_of_organization")); //3 столбик
        tcOrganization_direction.setCellValueFactory(new PropertyValueFactory<Organization, String>("Organization_direction")); //4 столбик
		
        tvOrganization.setItems(FXCollections.observableArrayList(db.getAllOrg())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
        tvOrganization.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Organization cl) throws IOException{
		if (cl != null) {
			tfidOrganization.setText(Integer.toString(cl.getIdOrganization()));
			tfName_of_organization.setText(cl.getName_of_organization());
			tfName_of_head_of_organization.setText(cl.getName_of_head_of_organization());
			tfOrganization_direction.setText(cl.getOrganization_direction());
			
		} else {
			tfidOrganization.setText("");
			tfName_of_organization.setText("");
			tfName_of_head_of_organization.setText("");
			tfOrganization_direction.setText("");
		}
	}
	
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newOrg(tfName_of_organization.getText(), tfName_of_head_of_organization.getText(), tfOrganization_direction.getText());
			tvOrganization.setItems(FXCollections.observableArrayList(db.getAllOrg()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delOrg(Integer.parseInt(tfidOrganization.getText()));
			tvOrganization.setItems(FXCollections.observableArrayList(db.getAllOrg()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updOrg(Integer.parseInt(tfidOrganization.getText()), tfName_of_organization.getText(), tfName_of_head_of_organization.getText(), tfOrganization_direction.getText());
			tvOrganization.setItems(FXCollections.observableArrayList(db.getAllOrg()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName_of_organization.getText() == null || tfName_of_organization.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfName_of_head_of_organization.getText() == null || tfName_of_head_of_organization.getText().length() == 0) {
				errorMessage += "No valid name of head!\n";
			}
			if (tfOrganization_direction.getText() == null || tfOrganization_direction.getText().length() == 0) {
				errorMessage += "No valid dislocation!\n";
			}
		}
		if (i == 2) {
			if (tfidOrganization.getText() == null || tfidOrganization.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidOrganization.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfidOrganization.getText() == null || tfidOrganization.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidOrganization.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName_of_organization.getText() == null || tfName_of_organization.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfName_of_head_of_organization.getText() == null || tfName_of_head_of_organization.getText().length() == 0) {
				errorMessage += "No valid name of head!\n";
			}
			if (tfOrganization_direction.getText() == null || tfOrganization_direction.getText().length() == 0) {
				errorMessage += "No valid dislocation!\n";
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
	@FXML
	private void winInvite() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Invite.fxml"));
		
		Scene scene = new Scene(root,1250,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Invite");
		primaryStage.show();
		
	}
	
	
}

package application;

import java.io.FileReader;
import java.io.IOException;
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

public class ContrForGroup {
	@FXML private TableView<Group> tvGroup;
	@FXML private TableColumn<Group, Integer> tcidGroup;
	@FXML private TableColumn<Group, String> tcNumber_of_group;
	@FXML private TableColumn<Group, String> tcName_of_head_of_group;
	@FXML private TableColumn<Group, Integer> tcNumber_of_group_members;
	@FXML private TableColumn<Group, Integer> tcFaculty_idFaculty;
	
	@FXML private TextField tfidGroup;
	@FXML private TextField tfNumber_of_group;
	@FXML private TextField tfName_of_head_of_group;
	@FXML private TextField tfNumber_of_group_members;
	@FXML private TextField tfFaculty_idFaculty;
	
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
        lvl.close();
        tcidGroup.setCellValueFactory(new PropertyValueFactory<Group, Integer>("idGroup")); //1 столбик
        tcNumber_of_group.setCellValueFactory(new PropertyValueFactory<Group, String>("Number_of_group")); //2 столбик
        tcName_of_head_of_group.setCellValueFactory(new PropertyValueFactory<Group, String>("Name_of_head_of_group")); //3 столбик
        tcNumber_of_group_members.setCellValueFactory(new PropertyValueFactory<Group, Integer>("Number_of_group_members")); //4 столбик
        tcFaculty_idFaculty.setCellValueFactory(new PropertyValueFactory<Group, Integer>("Faculty_idFaculty"));
        tvGroup.setItems(FXCollections.observableArrayList(db.getAllGroup())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
        tvGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Group cl) throws IOException{
		if (cl != null) {
			tfidGroup.setText(Integer.toString(cl.getIdGroup()));
			tfNumber_of_group.setText(cl.getNumber_of_group());
			tfName_of_head_of_group.setText(cl.getName_of_head_of_group());
			tfNumber_of_group_members.setText(Integer.toString(cl.getNumber_of_group_members()));
			tfFaculty_idFaculty.setText(Integer.toString(cl.getFaculty_idFaculty()));
			
		} else {
			tfidGroup.setText("");
			tfNumber_of_group.setText("");
			tfName_of_head_of_group.setText("");
			tfNumber_of_group_members.setText("");
			tfFaculty_idFaculty.setText("");
			
		}
	}
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newGroup(tfNumber_of_group.getText(), tfName_of_head_of_group.getText(), 0, Integer.parseInt(tfFaculty_idFaculty.getText()));
			tvGroup.setItems(FXCollections.observableArrayList(db.getAllGroup()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delGroup(Integer.parseInt(tfidGroup.getText()));
			tvGroup.setItems(FXCollections.observableArrayList(db.getAllGroup()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updGroup(Integer.parseInt(tfidGroup.getText()), tfNumber_of_group.getText(), tfName_of_head_of_group.getText(), Integer.parseInt(tfNumber_of_group_members.getText()), Integer.parseInt(tfFaculty_idFaculty.getText()));
			tvGroup.setItems(FXCollections.observableArrayList(db.getAllGroup()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfNumber_of_group.getText() == null || tfNumber_of_group.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfName_of_head_of_group.getText() == null || tfName_of_head_of_group.getText().length() == 0) {
				errorMessage += "No valid name of head!\n";
			}
			if (tfFaculty_idFaculty.getText() == null || tfFaculty_idFaculty.getText().length() == 0) {
				errorMessage += "No valid faculty!\n";
			}else {
				try {
					Integer.parseInt(tfFaculty_idFaculty.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 2) {
			if (tfidGroup.getText() == null || tfidGroup.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidGroup.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfidGroup.getText() == null || tfidGroup.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidGroup.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfNumber_of_group.getText() == null || tfNumber_of_group.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfName_of_head_of_group.getText() == null || tfName_of_head_of_group.getText().length() == 0) {
				errorMessage += "No valid name of head!\n";
			}
			if (tfNumber_of_group_members.getText() == null || tfNumber_of_group_members.getText().length() == 0) {
				errorMessage += "No valid members!\n";
			} else {
				try {
					Integer.parseInt(tfNumber_of_group_members.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfFaculty_idFaculty.getText() == null || tfFaculty_idFaculty.getText().length() == 0) {
				errorMessage += "No valid faculty!\n";
			}else {
				try {
					Integer.parseInt(tfFaculty_idFaculty.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
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

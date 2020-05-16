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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContrForFaculty {
	@FXML private Label lNum;
	
	@FXML private TableView<Faculty> tvFaculty;
	@FXML private TableColumn<Faculty, Integer> tcidFaculty;
	@FXML private TableColumn<Faculty, String> tcName_of_faculty;
	@FXML private TableColumn<Faculty, Date> tcDate_of_establishment_faculty;
	@FXML private TableColumn<Faculty, String> tcName_of_head_of_faculty;
	@FXML private TableColumn<Faculty, Integer> tcNumber_of_faculty_members;
	@FXML private TableColumn<Faculty, Integer> tcMembers_now;
	
	@FXML private TextField tfidFaculty;
	@FXML private TextField tfName_of_faculty;
	@FXML private TextField tfDate_of_establishment_faculty;
	@FXML private TextField tfName_of_head_of_faculty;
	@FXML private TextField tfNumber_of_faculty_members;
	@FXML private TextField tfMembers_now;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	
	@FXML private TextField tfSortStatus;
	@FXML private TextField tfSortNum;
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	
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
        tcidFaculty.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("idFaculty")); //1 столбик
        tcName_of_faculty.setCellValueFactory(new PropertyValueFactory<Faculty, String>("Name_of_faculty")); //2 столбик
        tcDate_of_establishment_faculty.setCellValueFactory(new PropertyValueFactory<Faculty, Date>("Date_of_establishment_faculty")); //3 столбик
        tcName_of_head_of_faculty.setCellValueFactory(new PropertyValueFactory<Faculty, String>("Name_of_head_of_faculty")); //4 столбик
        tcNumber_of_faculty_members.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("Number_of_faculty_members"));
        tcMembers_now.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("Members_now"));
        tvFaculty.setItems(FXCollections.observableArrayList(db.getAllFaculty())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
        tvFaculty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void showTechDetails(Faculty cl) throws IOException{
		if (cl != null) {
			tfidFaculty.setText(Integer.toString(cl.getIdFaculty()));
			tfName_of_faculty.setText(cl.getName_of_faculty());
			tfDate_of_establishment_faculty.setText(cl.getDate_of_establishment_faculty().toString());
			tfName_of_head_of_faculty.setText(cl.getName_of_head_of_faculty());
			tfNumber_of_faculty_members.setText(Integer.toString(cl.getNumber_of_faculty_members()));
			tfMembers_now.setText(Integer.toString(cl.getMembers_now()));
			
		} else {
			tfidFaculty.setText("");
			tfName_of_faculty.setText("");
			tfDate_of_establishment_faculty.setText("");
			tfName_of_head_of_faculty.setText("");
			tfNumber_of_faculty_members.setText("");
			tfMembers_now.setText("0");
			
		}
	}
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newFaculty(tfName_of_faculty.getText(), Date.valueOf(tfDate_of_establishment_faculty.getText()), tfName_of_head_of_faculty.getText(), Integer.parseInt(tfNumber_of_faculty_members.getText()), 
					0);
			tvFaculty.setItems(FXCollections.observableArrayList(db.getAllFaculty()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delFaculty(Integer.parseInt(tfidFaculty.getText()));
			tvFaculty.setItems(FXCollections.observableArrayList(db.getAllFaculty()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updFaculty(Integer.parseInt(tfidFaculty.getText()), tfName_of_faculty.getText(), Date.valueOf(tfDate_of_establishment_faculty.getText()), tfName_of_head_of_faculty.getText(), Integer.parseInt(tfNumber_of_faculty_members.getText()), 
					Integer.parseInt(tfMembers_now.getText()));
			tvFaculty.setItems(FXCollections.observableArrayList(db.getAllFaculty()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName_of_faculty.getText() == null || tfName_of_faculty.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfDate_of_establishment_faculty.getText() == null || tfDate_of_establishment_faculty.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_of_establishment_faculty.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfName_of_head_of_faculty.getText() == null || tfName_of_head_of_faculty.getText().length() == 0) {
				errorMessage += "No valid year of name of head!\n";
			} 
			if (tfNumber_of_faculty_members.getText() == null || tfNumber_of_faculty_members.getText().length() == 0) {
				errorMessage += "No valid number!\n";
			} else {
				try {
					Integer.parseInt(tfNumber_of_faculty_members.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			
		}
		if (i == 2) {
			if (tfidFaculty.getText() == null || tfidFaculty.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidFaculty.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfidFaculty.getText() == null || tfidFaculty.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfidFaculty.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName_of_faculty.getText() == null || tfName_of_faculty.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfDate_of_establishment_faculty.getText() == null || tfDate_of_establishment_faculty.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_of_establishment_faculty.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfName_of_head_of_faculty.getText() == null || tfName_of_head_of_faculty.getText().length() == 0) {
				errorMessage += "No valid year of name of head!\n";
			} 
			if (tfNumber_of_faculty_members.getText() == null || tfNumber_of_faculty_members.getText().length() == 0) {
				errorMessage += "No valid number!\n";
			} else {
				try {
					Integer.parseInt(tfNumber_of_faculty_members.getText());
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
	@FXML
	private void SortByMem() throws IOException {
		tvFaculty.setItems(FXCollections.observableArrayList(db.nowSort(tfSortStatus.getText())));	
	}
	@FXML
	private void SortByTot() throws IOException {
		tvFaculty.setItems(FXCollections.observableArrayList(db.totSort(tfSortNum.getText())));	
	}
	@FXML
	private void pass() throws IOException {
		tvFaculty.setItems(FXCollections.observableArrayList(db.getAllFaculty()));
	}
	
}

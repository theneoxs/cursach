package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
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

public class ContrForEconomy {
	@FXML private TableView<Economy> tvEconomy;
	@FXML private TableColumn<Economy, String> tcType;
	@FXML private TableColumn<Economy, Float> tcStudent_scholarship;
	
	@FXML private TextField tfType;
	@FXML private TextField tfStudent_scholarship;
	
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
        tcType.setCellValueFactory(new PropertyValueFactory<Economy, String>("Type")); //1 столбик
        tcStudent_scholarship.setCellValueFactory(new PropertyValueFactory<Economy, Float>("Student_scholarship")); //2 столбик
        tvEconomy.setItems(FXCollections.observableArrayList(db.getAllEconomy())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
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
	//удалить строчку с id n 
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

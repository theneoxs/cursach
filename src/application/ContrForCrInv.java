package application;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContrForCrInv {
	@FXML private Label lNum;
	
	@FXML private TableView<Organization> tvOrganization;
	@FXML private TableColumn<Organization, Integer> tcidOrganization;
	@FXML private TableColumn<Organization, String> tcName_of_organization;
	@FXML private TableColumn<Organization, String> tcName_of_head_of_organization;
	@FXML private TableColumn<Organization, String> tcOrganization_direction;
	
	@FXML private TextField tfidStudent;
	@FXML private TextField tfidOrganization;
	@FXML private TextArea tfMessage;
	@FXML private TextField tfdate;
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	
	private Database db = new Database();
	Date dateNow = new Date();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
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
        
        
        tfidStudent.setText(info);
        System.out.println(df.format(dateNow));
        tfdate.setText(df.format(dateNow));
        
        tcidOrganization.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("idOrganization")); //1 столбик
        tcName_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_organization")); //2 столбик
        tcName_of_head_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_head_of_organization")); //3 столбик
        tcOrganization_direction.setCellValueFactory(new PropertyValueFactory<Organization, String>("Organization_direction")); //4 столбик
        tvOrganization.setItems(FXCollections.observableArrayList(db.getAllExOrg(info))); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
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
			
		} else {
			tfidOrganization.setText("");
		}
	}

	@FXML
	private void handleDen() throws IOException{
		System.out.println("Work!");
		tfMessage.setText("");
		tfidOrganization.setText("");
	}
	@FXML
	private void handleAcc() throws IOException{
		
		db.addInvite(Integer.parseInt(tfidStudent.getText()), Integer.parseInt(tfidOrganization.getText()), tfMessage.getText(), java.sql.Date.valueOf(tfdate.getText()));
		handleDen();
	}
	
}

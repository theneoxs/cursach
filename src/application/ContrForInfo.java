package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ContrForInfo {
	@FXML private Label lNum;
	
	@FXML private TableView<Organization> tvOrganization;
	@FXML private TableColumn<Organization, Integer> tcidOrganization;
	@FXML private TableColumn<Organization, String> tcName_of_organization;
	@FXML private TableColumn<Organization, String> tcName_of_head_of_organization;
	@FXML private TableColumn<Organization, String> tcOrganization_direction;
	
	@FXML private TextField tfIdStudent;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfMiddle_name;
	@FXML private TextField tfDate_of_birth;
	@FXML private TextField tfSex;
	@FXML private TextField tfYear_of_enrollment;
	@FXML private TextField tfStatus;
	@FXML private TextField tfType;
	@FXML private TextField tfidGroup;
	
	@FXML private ObservableList<String> cblMRID;
	@FXML private ObservableList<String> cblSID;
	@FXML private ObservableList<String> cblSex;
	@FXML private ObservableList<String> cblStatus;
	@FXML private ComboBox<String> cbMRID = new ComboBox<String>();
	@FXML private ComboBox<String> cbSID = new ComboBox<String>();
	@FXML private ComboBox<String> cbSex = new ComboBox<String>();
	@FXML private ComboBox<String> cbStatus = new ComboBox<String>();
	@FXML private Button bZayavka;
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	
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
        
        Student stud = db.getConcrStudent(info);
        tfIdStudent.setText(Integer.toString(stud.getIdStudent()));
        tfName.setText(stud.getName());
        tfSurname.setText(stud.getSurname());
        tfMiddle_name.setText(stud.getMiddle_name());
        tfDate_of_birth.setText(stud.getDate_of_birth().toString());
        tfYear_of_enrollment.setText(stud.getYear_of_enrollment().toString());
        
        cblMRID = FXCollections.observableArrayList(db.listAllEc());
		cbMRID.setItems(cblMRID);
		cblSex = FXCollections.observableArrayList("Male", "Female");
		cbSex.setItems(cblSex);
		cblStatus = FXCollections.observableArrayList("Learning", "Expelled","Finish","Vacation");
		cbStatus.setItems(cblStatus);
		cblSID = FXCollections.observableArrayList(db.listAllGroup());
		cbSID.setItems(cblSID);
		
		cbSex.setValue(stud.getSex());
		cbStatus.setValue(stud.getStatus());
		cbMRID.setValue(db.getEconomy(stud.getType()));
		cbSID.setValue(db.getGroup(stud.getIdGroup()));
		
		tcidOrganization.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("idOrganization")); //1 столбик
        tcName_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_organization")); //2 столбик
        tcName_of_head_of_organization.setCellValueFactory(new PropertyValueFactory<Organization, String>("Name_of_head_of_organization")); //3 столбик
        tcOrganization_direction.setCellValueFactory(new PropertyValueFactory<Organization, String>("Organization_direction")); //4 столбик
        tvOrganization.setItems(FXCollections.observableArrayList(db.getAllConcrOrg(info))); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики

	}
	@FXML
	private void winInv() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("CreateInvite.fxml"));
		
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Create Invite");
		primaryStage.show();
		
	}
}

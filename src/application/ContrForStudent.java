package application;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ContrForStudent {
	@FXML private Label lNum;
	
	@FXML private TableView<Student> tvStudent;
	@FXML private TableColumn<Student, Integer> tcIdStudent;
	@FXML private TableColumn<Student, String> tcName;
	@FXML private TableColumn<Student, String> tcSurname;
	@FXML private TableColumn<Student, String> tcMiddle_name;
	@FXML private TableColumn<Student, Date> tcDate_of_birth;
	@FXML private TableColumn<Student, String> tcSex;
	@FXML private TableColumn<Student, Date> tcYear_of_enrollment;
	@FXML private TableColumn<Student, String> tcStatus;
	@FXML private TableColumn<Student, String> tcType;
	@FXML private TableColumn<Student, Integer> tcIdGroup;
	
	@FXML private TextField tfIdStudent;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfMiddle_name;
	@FXML private TextField tfDate_of_birth;
	@FXML private TextField tfSex;
	@FXML private TextField tfYear_of_enrollment;
	@FXML private TextField tfStatus;
	@FXML private ObservableList<String> cblMRID;
	@FXML private ObservableList<String> cblSID;
	@FXML private ObservableList<String> cblSex;
	@FXML private ObservableList<String> cblStatus;
	@FXML private ComboBox<String> cbMRID = new ComboBox<String>();
	@FXML private ComboBox<String> cbSID = new ComboBox<String>();
	@FXML private ComboBox<String> cbSex = new ComboBox<String>();
	@FXML private ComboBox<String> cbStatus = new ComboBox<String>();
	
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	@FXML private Button bOrg;
	@FXML private Button bGroup;
	@FXML private Button bFaculty;
	@FXML private Button bEconomy;
	@FXML private Button bOrgS;
	@FXML private Button bClient;
	@FXML private Button bInvite;
	
	@FXML private TextField tfSortStatus;
	@FXML private TextField tfSortNum;
	@FXML private Button bSortStatus;
	@FXML private Button bSortNum;
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@FXML private String password;
	@FXML private String info;
	@FXML private String login;
	@FXML private Scanner scan;
	@FXML
	private void initialize() throws IOException {
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
		cblMRID = FXCollections.observableArrayList(db.listAllEc());
		cbMRID.setItems(cblMRID);
		cblSex = FXCollections.observableArrayList("Мужской", "Женский");
		cbSex.setItems(cblSex);
		cblStatus = FXCollections.observableArrayList("Учится", "Отчислен","Завершил","Академотпуск");
		cbStatus.setItems(cblStatus);
		cblSID = FXCollections.observableArrayList(db.listAllGroup());
		cbSID.setItems(cblSID);
		cbSex.setValue(cblSex.get(0));
		cbStatus.setValue(cblStatus.get(0));
		cbMRID.setValue(cblMRID.get(0));
		cbSID.setValue(cblSID.get(0));
		tcIdStudent.setCellValueFactory(new PropertyValueFactory<Student, Integer>("idStudent")); //1 столбик
		tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name")); //2 столбик
		tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname")); //3 столбик
		tcMiddle_name.setCellValueFactory(new PropertyValueFactory<Student, String>("Middle_name")); //4 столбик
		tcDate_of_birth.setCellValueFactory(new PropertyValueFactory<Student, Date>("Date_of_birth")); //5 столбик
		tcSex.setCellValueFactory(new PropertyValueFactory<Student, String>("Sex")); //6 столбик
		tcYear_of_enrollment.setCellValueFactory(new PropertyValueFactory<Student, Date>("Year_of_enrollment")); //7 столбик
		tcStatus.setCellValueFactory(new PropertyValueFactory<Student, String>("Status")); //8 столбик
		tcType.setCellValueFactory(new PropertyValueFactory<Student, String>("Type"));
		tcIdGroup.setCellValueFactory(new PropertyValueFactory<Student, Integer>("idGroup"));
		
		tvStudent.setItems(FXCollections.observableArrayList(db.getAllStudent())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
		tvStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showTechDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}); //Прослушиватель нажатия на табличку
		
	}
	private void showTechDetails(Student cl) throws IOException{
		if (cl != null) {
			tfIdStudent.setText(Integer.toString(cl.getIdStudent()));
			tfName.setText(cl.getName());
			tfSurname.setText(cl.getSurname());
			tfMiddle_name.setText(cl.getMiddle_name());
			tfDate_of_birth.setText(cl.getDate_of_birth().toString());
			cbSex.setValue(cl.getSex());
			tfYear_of_enrollment.setText(cl.getYear_of_enrollment().toString());
			cbStatus.setValue(cl.getStatus());
			cbMRID.setValue(db.getEconomy(cl.getType()));
			cbSID.setValue(db.getGroup(cl.getIdGroup()));
			
		} else {
			tfIdStudent.setText("");
			tfName.setText("");
			tfSurname.setText("");
			tfMiddle_name.setText("");
			tfDate_of_birth.setText("");
			cbSex.setValue(cblSex.get(0));
			tfYear_of_enrollment.setText("");
			cbStatus.setValue(cblStatus.get(0));
			cbMRID.setValue(cblMRID.get(0));
			cbSID.setValue(cblSID.get(0));
		}
	}
	
	@FXML
	private void handleNew() throws IOException{
		System.out.println("Work!");
		if (isInputValid(1)) {
			db.newStudent(tfName.getText(), tfSurname.getText(), tfMiddle_name.getText(), Date.valueOf(tfDate_of_birth.getText()), cbSex.getValue(), 
					Date.valueOf(tfYear_of_enrollment.getText()), cbStatus.getValue(), cbMRID.getValue().substring(0, cbMRID.getValue().lastIndexOf(" ")), 
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().lastIndexOf(" "))));
			tvStudent.setItems(FXCollections.observableArrayList(db.getAllStudent()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() throws IOException{
		if (isInputValid(2)) {
			db.delStudent(Integer.parseInt(tfIdStudent.getText()));
			tvStudent.setItems(FXCollections.observableArrayList(db.getAllStudent()));
		}
	}
	@FXML
	private void handleUpd() throws IOException{
		if (isInputValid(3)) {
			db.updStudent(Integer.parseInt(tfIdStudent.getText()), tfName.getText(), tfSurname.getText(), tfMiddle_name.getText(), Date.valueOf(tfDate_of_birth.getText()), cbSex.getValue(), 
					Date.valueOf(tfYear_of_enrollment.getText()), cbStatus.getValue(), cbMRID.getValue().substring(0, cbMRID.getValue().lastIndexOf(" ")), 
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().lastIndexOf(" "))));
			tvStudent.setItems(FXCollections.observableArrayList(db.getAllStudent()));
		}
	}
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfMiddle_name.getText() == null || tfMiddle_name.getText().length() == 0) {
				errorMessage += "No valid middle name!\n";
			}
			if (tfDate_of_birth.getText() == null || tfDate_of_birth.getText().length() == 0) {
				errorMessage += "No valid birthday!\n";
			} else {
				try {
					df.parse(tfDate_of_birth.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfYear_of_enrollment.getText() == null || tfYear_of_enrollment.getText().length() == 0) {
				errorMessage += "No valid year of enrollment!\n";
			} else {
				try {
					df.parse(tfDate_of_birth.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}			
		}
		if (i == 2) {
			if (tfIdStudent.getText() == null || tfIdStudent.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdStudent.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfIdStudent.getText() == null || tfIdStudent.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdStudent.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfMiddle_name.getText() == null || tfMiddle_name.getText().length() == 0) {
				errorMessage += "No valid middle name!\n";
			}
			if (tfDate_of_birth.getText() == null || tfDate_of_birth.getText().length() == 0) {
				errorMessage += "No valid birthday!\n";
			} else {
				try {
					df.parse(tfDate_of_birth.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfYear_of_enrollment.getText() == null || tfYear_of_enrollment.getText().length() == 0) {
				errorMessage += "No valid year of enrollment!\n";
			} else {
				try {
					df.parse(tfDate_of_birth.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
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
	private void SortByStatus() throws IOException {
		tvStudent.setItems(FXCollections.observableArrayList(db.statusSort(tfSortStatus.getText())));	
	}
	@FXML
	private void SortByNum() throws IOException {
		tvStudent.setItems(FXCollections.observableArrayList(db.groupSort(tfSortNum.getText())));	
	}
	@FXML
	private void pass() throws IOException {
		tvStudent.setItems(FXCollections.observableArrayList(db.getAllStudent()));
	}
	@FXML
	private void winOrg() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Organization.fxml"));
		
		Scene scene = new Scene(root,1250,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Organizaton");
		primaryStage.show();
		
	}
	@FXML
	private void winFaculty() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Faculty.fxml"));
		
		Scene scene = new Scene(root,1250,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Faculty");
		primaryStage.show();
		
	}
	@FXML
	private void winOrgS() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("OrgStudent.fxml"));
		
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("OrgStudent");
		primaryStage.show();
		
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
	@FXML
	private void winClient() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("client.fxml"));
		
		Scene scene = new Scene(root,1200,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}
	@FXML
	private void winGroup() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Group.fxml"));
		
		Scene scene = new Scene(root,1250,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Group");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	try {
		    		cblSID = FXCollections.observableArrayList(db.listAllGroup());
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	cbSID.setItems(cblSID);
		    	cbSID.setValue(cblSID.get(0));
		    }
		});
		
	}
	@FXML
	private void winEconomy() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Economy.fxml"));
		
		Scene scene = new Scene(root,1300,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Economy");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	try {
		    		cblMRID = FXCollections.observableArrayList(db.listAllEc());
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	cbMRID.setItems(cblMRID);
		    	cbMRID.setValue(cblMRID.get(0));
		    }
		});
	}
}

package application;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ContrForLogin {
	@FXML
	private TextField tfLogin;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button bLogin;
	@FXML
	private Button bReg;
	@FXML
	private Label lDeny;
	public static int level_accept = 0;
	private Database db = new Database();
	@FXML
	private void initialize() {
		lDeny.setVisible(false);
	}
	
	@FXML
	private void Log_In() throws IOException {
		boolean tr = db.login(tfLogin.getText(), tfPassword.getText());
		String nextfile = "";
		String namefile = "";
		boolean access = false;
		int x = 1200;
		int y = 700;
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        lvl.close();
        
        if (level_accept.equals("0")) {
			nextfile = "AdminPanel.fxml";
			namefile = "Admin Panel";
			access = true;
			x = 700;
			y = 700;
		}
        else if (level_accept.equals("1")) {
			nextfile = "StudentInfo.fxml";
			namefile = "StudInfo";
			access = true;
			y = 600;
		}
        else if (level_accept.equals("2")) {
			nextfile = "Student.fxml";
			namefile = "Student";
			access = true;
		}
        else if (level_accept.equals("3")) {
			nextfile = "Faculty.fxml";
			namefile = "Faculty";
			access = true;
			x = 1300;
		}
        else if (level_accept.equals("4")) {
			nextfile = "Economy.fxml";
			namefile = "Economy";
			x = 1300;
			y = 700;
			access = true;
		}
        else if (level_accept.equals("5")) {
			nextfile = "client.fxml";
			namefile = "Client";
			access = true;
		}
        else if (level_accept.equals("6")) {
			nextfile = "Organization.fxml";
			namefile = "Organization";
			access = true;
		}
        else if (level_accept.equals("7")) {
			nextfile = "Group.fxml";
			namefile = "Teacher's field";
			access = true;
		}
		if (tr && access) {
			lDeny.setVisible(false);
			
			Stage stage = (Stage) bLogin.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			AnchorPane root = new AnchorPane();
			
			root = FXMLLoader.load(getClass().getResource(nextfile));
			
			Scene scene = new Scene(root,x,y);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(namefile);
			primaryStage.show();
			
		}
		else {
			lDeny.setVisible(true);
		}
	}
	@FXML
	private void winReg() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Autorization.fxml"));
		
		Scene scene = new Scene(root,500,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Registred");
		primaryStage.show();
		
	}
	@FXML
	private void winCalc() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("kalculator.fxml"));
		
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculator");
		primaryStage.show();
		
	}
}

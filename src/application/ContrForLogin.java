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
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        lvl.close();
        
        if (level_accept.equals("0")) {
			nextfile = "Student.fxml";
			namefile = "Admin Panel";
			access = true;
		}
        
		if (tr && access) {
			lDeny.setVisible(false);
			
			Stage stage = (Stage) bLogin.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			AnchorPane root = new AnchorPane();
			
			root = FXMLLoader.load(getClass().getResource(nextfile));
			
			Scene scene = new Scene(root,1200,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(namefile);
			primaryStage.show();
			
		}
		else {
			lDeny.setVisible(true);
		}
	}

}

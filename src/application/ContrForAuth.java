package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ContrForAuth {
	@FXML private TextField tfId;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	
	@FXML private Label lAcc;
	@FXML private Button bEnter;
	@FXML private CheckBox check;
	private Database db = new Database();
	private String password = "";
	@FXML
	private void initialize() throws IOException {
		lAcc.setVisible(false);
	}
	
	@FXML
	private void registred() throws IOException {
		
		if (check.isSelected()) {
			password = db.chechAcc(tfId.getText(), 0, tfName.getText(), tfSurname.getText());
		}
		else {
			password = db.chechAcc(tfId.getText(), 1, tfName.getText(), tfSurname.getText());
		}
		if (password.equals("")){
			lAcc.setVisible(true);
			lAcc.setText("Запрещено!");
		}
		else {
			lAcc.setVisible(true);
			lAcc.setText(password);
		}
	}
}

package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class calculator {
	@FXML private ObservableList<String> cblDolg;
	@FXML private ObservableList<String> cblObuch;
	@FXML private ObservableList<String> cblGSS;
	@FXML private ComboBox<String> cbDolg = new ComboBox<String>();
	@FXML private ComboBox<String> cbObuch = new ComboBox<String>();
	@FXML private ComboBox<String> cbGSS = new ComboBox<String>();
	
	@FXML private CheckBox check;
	
	@FXML private TextField tfMoney;
	@FXML private TextField tfDate;
	@FXML private TextField tfPeriod;
	
	@FXML private Label lTotal;
	@FXML private Label l5;
	@FXML private Label l4and5;
	@FXML private Label l4;
	
	float st5 = 4500;
	float st5and4 = 4000;
	float st4 = 3500;
	
	int gss5 = 6800;
	int gss3 = 3800;
	Database db = new Database();
	List<Economy> ec = null;
	Date dateNow = new Date();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@FXML
	private void initialize() throws IOException {
		ec = db.getAllEconomy();
		st5 = ec.get(3).getStudent_scholarship();
		st4 = ec.get(1).getStudent_scholarship();
		st5and4 = ec.get(2).getStudent_scholarship();
		cblDolg = FXCollections.observableArrayList("����", "���", "��������");
		cbDolg.setItems(cblDolg);
		cblObuch = FXCollections.observableArrayList(db.listAllEc());
		cbObuch.setItems(cblObuch);
		cblGSS = FXCollections.observableArrayList("������ �������", "������� 1 �����", "�������� 2 ������", "�������� 3 ������", "�������� 4 ������", "�������� 5 �������",
				"�������� 6 �������", "�������� 7 �������", "�������� 8 �������", "�������� 9 �������", "�������� 10 �������", "�������� 11 �������");
		cbGSS.setItems(cblGSS);
		
		tfDate.setText(df.format(dateNow));
		cbDolg.setValue(cblDolg.get(0));
		cbObuch.setValue(cblObuch.get(0));
		cbGSS.setValue(cblGSS.get(0));
		tfMoney.setText("0");
		tfPeriod.setText("0");
		cbGSS.setVisible(false);
		tfDate.setEditable(true);
	}
	
	@FXML
	private void comboCheck() throws IOException {
		if (check.isSelected()) {
			cbGSS.setVisible(true);
		}
		else {
			cbGSS.setVisible(false);
		}
	}
	
	@FXML
	private void calculated() throws IOException {
		int month = 0;
		
		int period = 0;
		int monthgss = 0;
		int[] session = {3, 9};
		
		int total = 0, t5 = 0, t4 = 0, t4and5 = 0;
		
		try {
			df.parse(tfDate.getText()).getTime();
			month = Integer.parseInt(tfDate.getText(5, 7));
			
			period = Integer.parseInt(tfPeriod.getText());
			monthgss = (cblGSS.indexOf(cbGSS.getValue()) == 0 ? 12 : cblGSS.indexOf(cbGSS.getValue()));
			if (monthgss > period) {
				monthgss = period;
			}
			
			// ���� ����� � ��������� �� 3 �� 8...
			if (month >= 3 && month < 9) {
				int mon = month + period;
				//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
				if (mon-9 < 0) {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (1 + period);
							
						}
					}
				}
				else {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (9 - month);
							
						}
					}
					//...� ������� �� �������
					period -= 8-month;
					if (period >6) period = 6;
					t5 += period * st5;
					t4 += period * st4;
					t4and5 += period * st5and4;
				}
			}
			//���� ����� � ��������� �� 9 �� 12...
			else if (month >= 9 && month <=12) {
				int mon = month + period;
				//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
				if (mon-15 < 0) {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (1 + period);
							
						}
					}
				}
				else {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (15 - month);
							
						}
					}
					//...� ������� �� �������
					period -= 14-month;
					if (period >6) period = 6;
					t5 += period * st5;
					t4 += period * st4;
					t4and5 += period * st5and4;
				}
			}
			else if (month >= 1 && month <3) {
				int mon = month + period;
				//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
				if (mon-3 < 0) {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (1 + period);
							
						}
					}
				}
				else {
					for (int i = 0; i < ec.size(); i++) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals(ec.get(i).getType())){
							total += ec.get(i).getStudent_scholarship() * (3 - month);
							
						}
					}
					//...� ������� �� �������
					period -= 2-month;
					if (period >6) period = 6;
					t5 += period * st5;
					t4 += period * st4;
					t4and5 += period * st5and4;
				}
			}
			if (cbDolg.getValue().equals("����")) {
				total = 0;
				t5 = 0;
				t4 = 0;
				t4and5 = 0;
			}
			else if (cbDolg.getValue().equals("��������")) {
				total = 0;
			}
			total += Integer.parseInt(tfMoney.getText());
			if (check.isSelected()) {
				if (month >= 3 && month < 9) {
					int mon = month + monthgss;
					//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
					if (mon-9 < 0) {
						//...� ���� �������� �� 5 ��� 5 � 4...
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss5 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (8-month+1);
						}
						//...� ���� �����...
						else {
							total += gss5 * (8-month+1);
						}
						//...� ������� �� �������
						monthgss -= 8-month;
						if (monthgss >6) monthgss = 6;
						t5 += monthgss * gss5;
						t4 += monthgss * gss5;
						t4and5 += monthgss * gss5;
					}
				}
				//���� ����� � ��������� �� 9 �� 12...
				else if (month >= 9 && month <=12) {
					int mon = month + monthgss;
					//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
					if (mon-15 < 0) {
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss5 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (14-month+1);
						}
						//...� ���� �����...
						else {
							total += gss5 * (14-month+1);
						}
						//...� ������� �� �������
						monthgss -= 14-month;
						if (monthgss >6) monthgss = 6;
						t5 += monthgss * gss5;
						t4 += monthgss * gss5;
						t4and5 += monthgss * gss5;
					}
				}
				else if (month >= 1 && month <3) {
					int mon = month + monthgss;
					//...� ���� ������ ��������� �� ������� �� ������ ���������� ��������...
					if (mon-3 < 0) {
						//...� ���� �������� �� 5...
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss5 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if (cbObuch.getValue().substring(0, cbObuch.getValue().lastIndexOf(" ")).equals("���")) {
							total += gss3 * (2-month+1);
						}
						//...� ���� �����...
						else {
							total += gss5 * (2-month+1);
						}
						//...� ������� �� �������
						monthgss -= 2-month;
						if (monthgss >6) monthgss = 6;
						t5 += monthgss * gss5;
						t4 += monthgss * gss5;
						t4and5 += monthgss * gss5;
					}
				}
				
				
			}
			
		}
		catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Incorrect!!!");
			alert.setHeaderText("Redacted paramentr");
			alert.setContentText("We detected, that in some field enter incorrect value. Please try again.");
			alert.showAndWait();
		}
		
		lTotal.setText(Integer.toString(total));
		l5.setText(Integer.toString(t5));
		l4and5.setText(Integer.toString(t4and5));
		l4.setText(Integer.toString(t4));
	}
}

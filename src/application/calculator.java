package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	int st5 = 4500;
	int st5and4 = 4200;
	int st4 = 4000;
	
	int gss5 = 6800;
	int gss3 = 3800;
	
	Date dateNow = new Date();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@FXML
	private void initialize() throws IOException {
		cblDolg = FXCollections.observableArrayList("����", "���", "��������");
		cbDolg.setItems(cblDolg);
		cblObuch = FXCollections.observableArrayList("5", "5 � 4", "4", "� 3 ��� �� �������");
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
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (1 + period);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (1 + period);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (1 + period);
					}
					//...� ���� �����...
					else {
						total = 0;
					}
				}
				else {
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (8-month+1);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (8-month+1);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (8-month+1);
					}
					//...� ���� �����...
					else {
						total = 0;
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
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (1 + period);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (1 + period);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (1 + period);
					}
					//...� ���� �����...
					else {
						total = 0;
					}
				}
				else {
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (14-month+1);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (14-month+1);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (14-month+1);
					}
					//...� ���� �����...
					else {
						total = 0;
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
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (1 + period);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (1 + period);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (1 + period);
					}
					//...� ���� �����...
					else {
						total = 0;
					}
				}
				else {
					//...� ���� �������� �� 5...
					if (cbObuch.getValue().equals("5")) {
						total += st5 * (2-month+1);
					}
					//...� ���� �������� �� 4 � 5...
					else if (cbObuch.getValue().equals("5 � 4")) {
						total += st5and4 * (2-month+1);
					}
					//...� ���� �������� �� 4...
					else if (cbObuch.getValue().equals("4")) {
						total += st4 * (2-month+1);
					}
					//...� ���� �����...
					else {
						total = 0;
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
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss3 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (8-month+1);
						}
						//...� ���� �����...
						else {
							total += gss3 * (8-month+1);
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
						//...� ���� �������� �� 5...
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss3 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (14-month+1);
						}
						//...� ���� �����...
						else {
							total += gss3 * (14-month+1);
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
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (1 + monthgss);
						}
						//...� ���� �����...
						else {
							total += gss3 * (1 + monthgss);
						}
					}
					else {
						//...� ���� �������� �� 5...
						if ((cbObuch.getValue().equals("5") || cbObuch.getValue().equals("5 � 4") || cbObuch.getValue().equals("4")) && (!cbDolg.getValue().equals("����") && !cbDolg.getValue().equals("��������"))) {
							total += gss5 * (2-month+1);
						}
						//...� ���� �����...
						else {
							total += gss3 * (2-month+1);
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

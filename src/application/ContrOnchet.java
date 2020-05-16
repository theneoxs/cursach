package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContrOnchet extends JFrame{
	@FXML private Label lNum;
	private  JFileChooser fileChooser = new JFileChooser();
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
	
	@FXML private TextField tfNumGroup;
	@FXML private TextField tfStCount;
	@FXML private TextField tfStarosta;
	@FXML private TextField tfFaculty;
	@FXML private TextField tfGroupName;
	@FXML private TextField tfFacultyName;
	@FXML private TextField tfFacultyLead;
	
	@FXML private Button bText;
	@FXML private Button bEnter;
	private Database db = new Database();
	Date dateNow = new Date();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@FXML
	private void initialize() throws IOException {
		// Локализация компонентов окна JFileChooser
        UIManager.put(
                 "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                 "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                 "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                 "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                 "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                 "FileChooser.folderNameLabelText", "Путь директории");

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
		
		tvStudent.setItems(FXCollections.observableArrayList(db.getConcrStudentInGroup("0")));
	}
	
	@FXML
	private void inform() throws IOException{
		Group group = db.getConcrGroup(tfNumGroup.getText());
		Faculty faculty = db.getConcrFaculty(Integer.toString(group.getFaculty_idFaculty()));
		tfStCount.setText(Integer.toString(group.getNumber_of_group_members()));
		tfStarosta.setText(group.getName_of_head_of_group());;
		tfFaculty.setText(Integer.toString(group.getFaculty_idFaculty()));
		tfGroupName.setText(group.getNumber_of_group());
		tfFacultyName.setText(faculty.getName_of_faculty());
		tfFacultyLead.setText(faculty.getName_of_head_of_faculty());
		tvStudent.setItems(FXCollections.observableArrayList(db.getConcrStudentInGroup(Integer.toString(group.getIdGroup()))));
	}
	
	@FXML
	private void inFile() throws IOException{
		String path = "";
		Group group = db.getConcrGroup(tfNumGroup.getText());
		Faculty faculty = db.getConcrFaculty(Integer.toString(group.getFaculty_idFaculty()));
		fileChooser.setDialogTitle("Выбор директории");
        // Определение режима - только каталог
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(ContrOnchet.this);
        // Если директория выбрана, покажем ее в сообщении
        if (result == JFileChooser.APPROVE_OPTION ) {
        	path = fileChooser.getSelectedFile().toString();
        	System.out.println(path);
        	writeInFile(path, group, faculty);
        	JOptionPane.showMessageDialog(ContrOnchet.this, "Completed!");
        }
        
	}
	
	private void writeInFile(String path, Group group, Faculty faculty) throws IOException {

		String filename = "Отчет о группе " + tfNumGroup.getText() + " от " + df.format(dateNow) + ".txt";
		System.out.println(filename);
		System.out.println(path);
		File dir = new File(path, filename);
		FileWriter file = new FileWriter(path + "/"+filename);
		file.write("ОТЧЕТ ПО ГРУППЕ "+tfNumGroup.getText()+"\n--------------\n");
		file.write("Называние группы: "+group.getNumber_of_group()+"\n");
		file.write("Староста группы: "+group.getName_of_head_of_group()+"\n\n");
		file.write("ID факультета: "+group.getFaculty_idFaculty()+"\n");
		file.write("Название факультета: "+faculty.getName_of_faculty()+"\n");
		file.write("Декан факультета: "+faculty.getName_of_head_of_faculty()+"\n\n");
		file.write("Число студентов в группе: "+group.getNumber_of_group_members()+"\n\nСтуденты:\n");
		List<Student> st= db.getConcrStudentInGroup(Integer.toString(group.getIdGroup()));
		String str = "";
		for (Student i : st) {
			str = String.format("%10d, %s %s %s,  %s \n", i.getIdStudent(), i.getSurname(), i.getName(), i.getMiddle_name(), i.getStatus());
			file.write(str);
		}
		file.write("\nОтчет сгенерирован "+df.format(dateNow)+"\n\n");

		file.close();
	}
}

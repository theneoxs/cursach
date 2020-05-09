package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class Database {
	
	private Connection conn;
	public Database() {
		this.conn = null;
	}
	// Открывает соединение. Возвращает true если открылось
	public boolean openConnection(String login, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/cursach?user="+login+"&password="+password+"");
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("SQL error: " + e.getMessage());
			return false;
		}
		return true;
	}
	// Закрывает соединение
	public void closeConnection() {
		try {
			if (this.conn != null)
				this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn = null;
	}
	
	// Получает информацию обо всех экземплярах техники
	public List<Student> getAllStudent() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Student> lStudent = new ArrayList<Student>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.student");
				while (rs.next()) {
					lStudent.add(new Student(rs.getInt("idStudent"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Middle_name"),
							rs.getDate("Date_of_birth"), rs.getString("Sex"), rs.getDate("Year_of_enrollment"), rs.getString("Status"), rs.getString("Type"), rs.getInt("idGroup")));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lStudent;
	}
	public List<Organization> getAllOrg() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Organization> lStudent = new ArrayList<Organization>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.organization");
				while (rs.next()) {
					lStudent.add(new Organization(rs.getInt("idOrganization"), rs.getString("Name_of_organization"), rs.getString("Name_of_head_of_organization"), rs.getString("Organization_direction")));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lStudent;
	}
	public List<Group> getAllGroup() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Group> lStudent = new ArrayList<Group>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.group");
				while (rs.next()) {
					lStudent.add(new Group(rs.getInt("idGroup"), rs.getString("Number_of_group"), rs.getString("Name_of_head_of_group"), rs.getInt("Number_of_group_members"), rs.getInt("Faculty_idFaculty")));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lStudent;
	}
	public boolean newStudent(String Name, String Surname, String Middle_name, Date Date_of_birth, String Sex, Date Year_of_enrollment, String Status, String Type, int idGroup) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into cursach.student (Name, Surname, Middle_name, Date_of_birth, Sex, Year_of_enrollment, Status, Type, idGroup) values('"
						+ Name + "','" + Surname + "','" + Middle_name + "','" + Date_of_birth + "','" + Sex + "','" + Year_of_enrollment + "','" + Status + "','" + Type + "','" + idGroup + "');");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
			
		}
		return (res == 1);
	}
	
	public boolean updStudent(Integer idStudent, String Name, String Surname, String Middle_name, Date Date_of_birth, String Sex, Date Year_of_enrollment, String Status, String Type, int idGroup) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update cursach.student set Name ='"+Name+"', Surname = '"
				+Surname+"', Middle_name = '"+Middle_name+"', Date_of_birth = '" + Date_of_birth +"', Sex = '"+Sex+"', Year_of_enrollment = '"+Year_of_enrollment+"', Status = '"+Status+"', Type = '"+Type+"', idGroup = '"+idGroup+"' where idStudent = '"+idStudent+"';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception " + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	//удаление техники
	public boolean delStudent(Integer idStudent) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from cursach.student where idStudent = '" + idStudent + "';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	public boolean newOrg(String Name_of_organization, String Name_of_head_of_organization, String Organization_direction) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into cursach.organization (Name_of_organization, Name_of_head_of_organization, Organization_direction) values('"
						+ Name_of_organization + "','" + Name_of_head_of_organization + "','" + Organization_direction + "');");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
			
		}
		return (res == 1);
	}
	public boolean delOrg(Integer idOrganization) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from cursach.opranization where idOrganization = '" + idOrganization + "';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	public boolean updOrg(Integer idOrganization, String Name_of_organization, String Name_of_head_of_organization, String Organization_direction) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update cursach.organization set Name_of_organization = '"
				+Name_of_organization+"', Name_of_head_of_organization = '"+Name_of_head_of_organization+"', Organization_direction = '" + Organization_direction +"' where idOrganization = '"+idOrganization+"';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception " + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	public boolean newGroup(String Number_of_group, String Name_of_head_of_group, Integer Number_of_group_members, Integer Faculty_idFaculty) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into cursach.group (Number_of_group, Name_of_head_of_group, Number_of_group_members, Faculty_idFaculty) values('"
						+ Number_of_group + "','" + Name_of_head_of_group + "','" + Number_of_group_members + "','" + Faculty_idFaculty + "');");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
			
		}
		return (res == 1);
	}
	public boolean delGroup(Integer idGroup) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from cursach.group where idGroup = '" + idGroup + "';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	public boolean updGroup(Integer idGroup, String Number_of_group, String Name_of_head_of_group, Integer Number_of_group_members, Integer Faculty_idFaculty) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update cursach.group set Number_of_group = '"
				+Number_of_group+"', Name_of_head_of_group = '"+Name_of_head_of_group+"', Number_of_group_members = '" + Number_of_group_members +"', Faculty_idFaculty = '" + Faculty_idFaculty +"' where idGroup = '"+idGroup+"';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception " + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	//создание нового подразделения
	public List<String> listAllGroup() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<String> lGroup = new ArrayList<String>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.group");
				while (rs.next()) {
					lGroup.add(String.valueOf(rs.getInt("idGroup"))+" " +String.valueOf(rs.getInt("Number_of_group")));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lGroup;
	}
	public List<String> listAllEc() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String a;
		List<String> lEc = new ArrayList<String>();
		if (openConnection(login, password)) {
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.economy");
				while (rs.next()) {
					a = rs.getString("Type")+" " +String.valueOf(rs.getFloat("Student_scholarship"));
					lEc.add(a);
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lEc;
	}
	public String getStudent(int i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.student where idStudent = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getInt("idStudent") + " " + rs.getString("Name") + " " + rs.getString("Surname");
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return ls;
	}
	public String getEconomy(String i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.economy where Type = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getString("Type") + " " + rs.getString("Student_scholarship");
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return ls;
	}
	public String getGroup(int i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.group where idGroup = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getInt("idGroup") + " " + rs.getString("Number_of_group");
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return ls;
	}
	public boolean login(String login, String password) throws IOException {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		String lvl = "";
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.client where idclient = '"+ login + "';");
				while (rs.next()) {
					ls += password;
					lvl += rs.getString("Access_level");
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return false;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		if (ls.equals(password)) {
			FileWriter file = new FileWriter("lvl");
			file.write(lvl+"\n");
			file.write(login+"\n");
			file.write(ls+"\n");
			file.close();
			return true;
		}
		else {
			return false;
		}
	}
}

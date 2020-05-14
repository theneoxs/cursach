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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class Database {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
	public String chechAcc(String id, int ch, String Name, String Surname) throws IOException {
		System.out.println("Do it!");
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
        String i = "";
        ResultSet rs = null;
		if (openConnection("1", "admin")) {
			Statement st = null;
			try {
				st = conn.createStatement();
				if (ch == 0) {
					rs = st.executeQuery("select * from cursach.client where Name = '"+Name+"' and Surname = '"+Surname+"' and Info = '"+id+"';");
				}
				else {
					rs = st.executeQuery("select * from cursach.client where Name = '"+Name+"' and Surname = '"+Surname+"' and idclient = '"+id+"';");
				}
				while(rs.next()) {
					i = "Login: " + rs.getString("idclient") + ", Password: " + rs.getString("Password");
				}
			} 
			catch (SQLException e) {
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
		return i;
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
	public Student getConcrStudent(String id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		Student lStudent = new Student();
		int i = 0;
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.student where idStudent = " +id+";");
				while (rs.next()) {
					lStudent = new Student(rs.getInt("idStudent"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Middle_name"),
							rs.getDate("Date_of_birth"), rs.getString("Sex"), rs.getDate("Year_of_enrollment"), rs.getString("Status"), rs.getString("Type"), rs.getInt("idGroup"));
				}
				i = 1;
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
	public List<Organization> getAllConcrOrg(String info) throws IOException {
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
				rs = st.executeQuery("select * from cursach.organization where idOrganization = (select Organization_idOrganization from cursach.organization_has_student where Student_idStudent = '" + info +"');");
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
	public List<Organization> getAllExOrg(String info) throws IOException {
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
				rs = st.executeQuery("select * from cursach.organization where idOrganization = (select idOrganization from cursach.organization_has_student where Student_idStudent != '" + info +"');");
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
	public List<Faculty> getAllFaculty() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Faculty> lStudent = new ArrayList<Faculty>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.faculty");
				while (rs.next()) {
					lStudent.add(new Faculty(rs.getInt("idFaculty"), rs.getString("Name_of_faculty"), rs.getDate("Date_of_establishment_faculty"), rs.getString("Name_of_head_of_faculty"), rs.getInt("Number_of_faculty_members"),  rs.getInt("Members_now")));
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
	public List<Economy> getAllEconomy() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Economy> lStudent = new ArrayList<Economy>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.economy");
				while (rs.next()) {
					lStudent.add(new Economy(rs.getString("Type"), rs.getFloat("Student_scholarship")));
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
	public List<OrgStudent> getAllOrgS() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<OrgStudent> lStudent = new ArrayList<OrgStudent>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.organization_has_student");
				while (rs.next()) {
					lStudent.add(new OrgStudent(rs.getInt("Organization_idOrganization"), rs.getInt("Student_idStudent")));
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
	public List<Invite> getAllInvite() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Invite> lStudent = new ArrayList<Invite>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.invite");
				while (rs.next()) {
					lStudent.add(new Invite(rs.getInt("idInvite"), rs.getInt("idStudent"), rs.getInt("idOrganization"),rs.getString("Message"),rs.getDate("date")));
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
	public List<client> getAllClient() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<client> lStudent = new ArrayList<client>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.client");
				while (rs.next()) {
					lStudent.add(new client(rs.getInt("idclient"), rs.getString("Name"), rs.getString("Surname"),rs.getDate("Date_of_autorization"),rs.getInt("Access_level"), rs.getString("Info"), rs.getString("Password")));
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
	public boolean newClient(String Name, String Surname, Date Date_of_autorization, Integer Access_level, String Info, String Password) throws IOException {
		System.out.println("Do it!");
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
				res = st.executeUpdate("insert into cursach.client (Name, Surname, Date_of_autorization, Access_level, Info, Password) values('"
						+ Name + "','" + Surname +"','" + Date_of_autorization +"','" + Access_level +"','" + Info +"','" + Password +"');");
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
	public boolean delClient(Integer idclient) throws IOException {
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
				res = st.executeUpdate("delete from cursach.client where idclient = '" + idclient + "';");
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
	public boolean updClient(Integer idclient, String Name, String Surname, Date Date_of_autorization, Integer Access_level, String Info, String Password) throws IOException {
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
				res = st.executeUpdate("update cursach.client set Name = '"
				+Name+"', Surname = '"+Surname+"', Date_of_autorization = '"+Date_of_autorization+"', Access_level = '"+Access_level+"', Info = '"+Info+"', Password = '"+Password+"' where idclient = '"+idclient+"';");
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
	public boolean deniedInvite(Integer idInvite) throws IOException {
		System.out.println("Do it!");
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
				res = st.executeUpdate("delete from cursach.Invite where idInvite = '" + idInvite + "';");
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
	public boolean addInvite(Integer idStudent, Integer idOrganization, String Message, Date Date) throws IOException {
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
				res = st.executeUpdate("insert into cursach.invite (idStudent, idOrganization, Message, date) values('"
						+ idStudent + "','" + idOrganization +"','" + Message +"','" + Date +"');");
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
	public boolean acceptInvite(Integer idInvite, Integer idStudent, Integer idOrganization) throws IOException {
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
				res = st.executeUpdate("delete from cursach.Invite where idInvite = '" + idInvite + "';");
				res = st.executeUpdate("insert into cursach.organization_has_student (Organization_idOrganization, Student_idStudent) values('"
						+ idOrganization + "','" + idStudent +"');");
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
	public boolean newOrgS(Integer Organization_idOrganization, Integer Student_idStudent) throws IOException {
		System.out.println("Do it!");
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
				res = st.executeUpdate("insert into cursach.organization_has_student (Organization_idOrganization, Student_idStudent) values('"
						+ Organization_idOrganization + "','" + Student_idStudent +"');");
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
	public boolean delOrgS(Integer Organization_idOrganization, Integer Student_idStudent) throws IOException {
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
				res = st.executeUpdate("delete from cursach.organization_has_student where Organization_idOrganization = '" + Organization_idOrganization + "', Student_idStudent = '" +Student_idStudent+ "';");
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
	public boolean updOrgS(Integer Organization_idOrganization, Integer Student_idStudent) throws IOException {
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
				res = st.executeUpdate("update cursach.organization_has_student set Student_idStudent = '"
				+Student_idStudent+"' where Organization_idOrganization = '"+Organization_idOrganization+"';");
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
	public boolean newEconomy(String Type, Float Student_scholarship) throws IOException {
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
				res = st.executeUpdate("insert into cursach.economy (Type, Student_scholarship) values('"
						+ Type + "','" + Student_scholarship +"');");
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
	public boolean delEconomy(String Type) throws IOException {
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
				res = st.executeUpdate("delete from cursach.economy where Type = '" + Type + "';");
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
	public boolean updEconomy(String Type, Float Student_scholarship) throws IOException {
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
				res = st.executeUpdate("update cursach.economy set Student_scholarship = '"
				+Student_scholarship+"' where Type = '"+Type+"';");
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
	public boolean newFaculty(String Name_of_faculty, Date Date_of_establishment_faculty, String Name_of_head_of_faculty, Integer Number_of_faculty_members, Integer Members_now) throws IOException {
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
				res = st.executeUpdate("insert into cursach.faculty (Name_of_faculty, Date_of_establishment_faculty, Name_of_head_of_faculty, Number_of_faculty_members, Members_now) values('"
						+ Name_of_faculty + "','" + Date_of_establishment_faculty + "','" + Name_of_head_of_faculty + "','" + Number_of_faculty_members + "','" + Members_now + "');");
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
	public boolean delFaculty(Integer idFaculty) throws IOException {
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
				res = st.executeUpdate("delete from cursach.faculty where idFaculty = '" + idFaculty + "';");
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
	public boolean updFaculty(Integer idFaculty, String Name_of_faculty, Date Date_of_establishment_faculty, String Name_of_head_of_faculty, Integer Number_of_faculty_members, Integer Members_now) throws IOException {
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
				res = st.executeUpdate("update cursach.faculty set Name_of_faculty ='"+Name_of_faculty+"', Date_of_establishment_faculty = '"
				+Date_of_establishment_faculty+"', Name_of_head_of_faculty = '"+Name_of_head_of_faculty+"', Number_of_faculty_members = '" + Number_of_faculty_members +"', Members_now = '"+Members_now+"' where idFaculty = '"+idFaculty+"';");
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
				
				ResultSet id1 = st.executeQuery("SELECT MAX(idStudent) AS idStudent FROM cursach.student");
				id1.next();
				int id = id1.getInt("idStudent");
				res = st.executeUpdate("insert into cursach.client (Name, Surname, Date_of_autorization, Access_level, Info, Password) values('" + Name +"','"+Surname+"','"+Date_of_birth+"','1', '"+id+"','');");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception: " + e.getMessage());
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
				res = st.executeUpdate("delete from cursach.client where Info = '" + idStudent + "';");
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
		String text = "";
		if (openConnection("root", "admin")) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from cursach.client where idclient = '"+ login + "';");
				while (rs.next()) {
					ls += rs.getString("Password");
					System.out.println(ls);
					lvl += rs.getString("Access_level");
					text += rs.getString("Info");
					
					
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
						st.executeUpdate("update cursach.client set Date_of_autorization = '"+df.format(new java.util.Date())+"' where idclient = '"+login+"';");
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
			file.write("root\n");
			file.write("admin\n");
			file.write(text+"\n");
			file.write(login+"\n");
			file.close();
			return true;
		}
		else {
			return false;
		}
	}
}

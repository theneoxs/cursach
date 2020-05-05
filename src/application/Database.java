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
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/db2?user="+login+"&password="+password+"");
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
	/*public List<Tech> getAllTech() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Tech> lTech = new ArrayList<Tech>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.technic");
				while (rs.next()) {
					lTech.add(new Tech(rs.getInt("idtechnic"), rs.getString("name"), rs.getString("model"),
							rs.getDate("date"), rs.getFloat("cost"), rs.getInt("room_num"), rs.getInt("mat_resp_id"), rs.getInt("subdividion_id")));
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
		return lTech;
	}
	*/
	
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

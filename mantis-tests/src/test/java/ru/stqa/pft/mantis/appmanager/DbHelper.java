package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbHelper {
    public static UserData testDbConnection() {
        Connection conn = null;
        UserData result = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, email FROM `mantis_user_table` WHERE username != 'administrator' limit 1");
            while (rs.next()) {
                result = new UserData().withUsername("username").withEmail("email");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
        /*
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from mantis_user_table");
            Users users = new Users();
            while (rs.next()) {
                users.add(new UserData().withId(rs.getInt("id"))
                        .withUsername(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
        */
}
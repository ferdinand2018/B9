package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbConnectionTest {
    @Test
    public void testDbConnection(){
        UserData u = new UserData();
        String sql = "select id, username, email from mantis_user_table where id = (select max(id) from mantis_user_table)";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Users users = new Users();
            while(rs.next()){
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
    }
}

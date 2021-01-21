
import desserts.DessertDAO;
import desserts.DessertDAOImpl;
import desserts.DessertDTO;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OurApplication {

    static int counter = 0;

    static public void main (String[] args) throws ClassNotFoundException, SQLException {
//        dbConnectExample();

//       getOne();
        for (Object S: getOne()) {
            System.out.println(S);
        }
//        System.out.println(getOne());
    }

    public static List getOne() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        List<String> desserts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/desserts?createDatabaseIfNotExist=true", "root", "root")) {
        try(Statement stmt=conn.createStatement()) {
            ResultSet rs=stmt.executeQuery("select name from goodstuff where id=1");
            while (rs.next()){
                String name=rs.getString("name");
                desserts.add(name);
            }
        }catch (SQLException e){
            System.out.println("Unable to run");
        }

    }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desserts;
    }

    public static void dbConnectExample() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/desserts?createDatabaseIfNotExist=true", "root", "root")) {
            if (conn != null) {
                System.out.println("Connected to the database!");
                String query = "select id, name from goodstuff";
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println(id + ", " + name);
                    }
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}

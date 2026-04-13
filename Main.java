import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/TEST";
        String username = "root";
        String password = "root";

        Connection conn = null;
        PreparedStatement insertStmt = null;
        PreparedStatement updateStmt = null;
        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database!");


            String insertSQL = "INSERT INTO employee (id, name, department, salary) VALUES (?, ?, ?, ?)";
            insertStmt = conn.prepareStatement(insertSQL);


            insertStmt.setInt(1, 1);
            insertStmt.setString(2, "E1");
            insertStmt.setString(3, "HR");
            insertStmt.setInt(4, 30000);
            insertStmt.executeUpdate();


            insertStmt.setInt(1, 2);
            insertStmt.setString(2, "E2");
            insertStmt.setString(3, "IT");
            insertStmt.setInt(4, 40000);
            insertStmt.executeUpdate();

            System.out.println("Employees inserted successfully!");


            String updateSQL = "UPDATE employee SET salary = ? WHERE id = ?";
            updateStmt = conn.prepareStatement(updateSQL);

            updateStmt.setInt(1, 45000); // new salary
            updateStmt.setInt(2, 1);     // employee id
            updateStmt.executeUpdate();

            System.out.println("Employee salary updated!");


            String selectSQL = "SELECT * FROM employee";
            selectStmt = conn.prepareStatement(selectSQL);
            rs = selectStmt.executeQuery();

            System.out.println("\nEmployee Records:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                int salary = rs.getInt("salary");

                System.out.println("ID: " + id +
                        ", Name: " + name +
                        ", Dept: " + dept +
                        ", Salary: " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
                if (insertStmt != null) insertStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (selectStmt != null) selectStmt.close();
                if (conn != null) conn.close();
                System.out.println("\nResources closed successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

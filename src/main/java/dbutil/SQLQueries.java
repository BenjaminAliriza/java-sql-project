package dbutil;

import java.sql.*;

public class SQLQueries {

    // db parameters
    private final String url       = "jdbc:mysql://localhost:3306/veneciabankdb";
    private final String user      = "root";
    private final String password  = "password";

    public void queryCustomerAddress(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT address FROM customer WHERE customer_ID = " + ID;

        try(Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                int id = rs.getInt("customer_ID");
                String address = rs.getString("address");

                System.out.println("The following address pertains to the ID: " + id +
                        "\nAddress: " + address);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void queryAccountBalance(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT current_balance FROM account WHERE customer_ID = " + ID;

        try(Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                String accNumber = rs.getString("account_num");
                double balance = rs.getDouble("current_balance");

                System.out.println("The following balance pertains to account: " + accNumber +
                        "\nBalance: " + balance);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void queryCustomer(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT * FROM customer WHERE customer_ID = " + ID;

        try(Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                int id = rs.getInt("customer_ID");
                String name = rs.getString("first_name");
                String surname = rs.getString("last_name");
                Date d_o_b = rs.getDate("d_o_b");
                String address = rs.getString("address");

                System.out.println("The following information pertains to the ID: " + id +
                        "\nFirst Name: " + name +
                        "\nLast Name: " + surname +
                        "\nDate of birth: " + d_o_b.toString() +
                        "\nAddress: " + address);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

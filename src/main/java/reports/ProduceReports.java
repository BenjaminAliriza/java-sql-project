package reports;

import java.sql.*;
import java.util.Scanner;

public class ProduceReports {

    // db parameters
    private final String url       = "jdbc:mysql://localhost:3306/veneciabankdb";
    private final String user      = "root";
    private final String password  = "password";

    public static void main(String[] args) throws SQLException {

        ProduceReports pr = new ProduceReports();
        int ID = pr.getID();
        pr.queryCustomer(ID);



    }

    private void queryCustomer(int ID) throws SQLException{

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

    private int getID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the relevant ID below:");
        int id = sc.nextInt();
        return id;
    }

}

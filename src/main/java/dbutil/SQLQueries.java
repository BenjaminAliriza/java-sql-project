package dbutil;

import java.sql.*;

public class SQLQueries {

    // db parameters
    private final String url = "jdbc:mysql://localhost:3306/veneciabankdb";
    private final String user = "root";
    private final String password = "password";

    public void queryCustomerAddress(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT address FROM customer WHERE customer_ID = " + ID;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String address = rs.getString("address");

                System.out.println("The following address pertains to the ID: " + ID +
                        "\n\nAddress: " + address);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void queryAccountBalance(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT SUM(current_balance) FROM account WHERE customer_ID = " + ID;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

               double balance = rs.getDouble("SUM(current_balance)");


                System.out.println("The following total balance pertains to customer with ID: " + ID +
                        "\nBalance: " + balance + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void queryTransactions(int ID) throws SQLException {
        String sqlSelectFromCustomers = "SELECT account.account_num, transactions.date, transactions.amount, transactions.transaction_type  FROM account " +
                "inner JOIN transactions " +
                "ON account.account_num = transactions.account_num " +
                "WHERE account.account_type = 'Cheque' " +
                "AND account.customer_ID = " + ID;


        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("The following information pertains to the ID: " + ID);

            while (rs.next()) {
                String accountNumber = rs.getString("account_num");
                String date = rs.getDate("date").toString();
                double amount = rs.getDouble("amount");
                boolean transactionType = rs.getBoolean("transaction_type");

                //boolean = 0 means false and 1 means true
                String tType;
                if (transactionType) {
                    tType = "Withdrawal";
                } else tType = "Deposit";

                System.out.println("\nAccount Number: " + accountNumber +
                        "\nDate: " + date +
                        "\nAmount: " + amount +
                        "\nTransaction Type: " + tType + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int queryCustomerCount() throws SQLException {
        String sqlSelectFromCustomers = "SELECT count(*) FROM customer";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            count = rs.getInt("count(*)");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public int querySarahAccounts() throws SQLException {
        String sqlSelectFromCustomers = "SELECT count(account_num) FROM account where customer_id =1";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            count = rs.getInt("count(account_num)");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public double queryTotalDeposits(String accountNum) throws SQLException {
        String sqlSelectFromCustomers = "SELECT amount FROM transactions WHERE transaction_type =0 AND account_num = '" + accountNum + "'";

        double amount = 0;
        double total = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                amount = rs.getDouble("amount");

                total = total + amount;

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }


    public double queryTotalWithdrawals(String accountNum) throws SQLException {
        String sqlSelectFromCustomers = "SELECT amount FROM transactions WHERE transaction_type = 1 AND account_num = '" + accountNum + "'";

        double amount = 0;
        double total = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                amount = rs.getDouble("amount");

                total = total - amount;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;

    }

    //    public void queryCustomer(int ID) throws SQLException {
//        String sqlSelectFromCustomers = "SELECT * FROM customer WHERE customer_ID = " + ID;
//
//        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
//             PreparedStatement ps = conn.prepareStatement(sqlSelectFromCustomers);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                int id = rs.getInt("customer_ID");
//                String name = rs.getString("first_name");
//                String surname = rs.getString("last_name");
//                Date d_o_b = rs.getDate("d_o_b");
//                String address = rs.getString("address");
//
//                System.out.println("The following information pertains to the ID: " + id +
//                        "\nFirst Name: " + name +
//                        "\nLast Name: " + surname +
//                        "\nDate of birth: " + d_o_b.toString() +
//                        "\nAddress: " + address);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

}

package sql;

import dbutil.SQLQueries;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

public class SqlTest {

    // Retrieve the number of customers from the database
    // Check that the total number of customers is equal to 2
    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() throws SQLException {
        SQLQueries sq = new SQLQueries();

        assertEquals(2, sq.queryCustomerCount());
    }

    // Retrieve all accounts for customer Sarah from the database
    // Check that the total number of accounts for her is 2
    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() throws SQLException {

        SQLQueries sq = new SQLQueries();
        assertEquals(2, sq.querySarahAccounts());
    }

    // A test that checks that the total of all transactions is equal to 0 for a given account.
    // Doing this for a single account is enough,
    // and you’re allowed to use the account ID for that account in the query
    @Test
    public void retrieveTransactionsForAccount_checkTotalBalance_shouldBeTwenty() throws SQLException {
        SQLQueries sq = new SQLQueries();

        String accountNumber=  "SS9902150";

       double totalDeposits = sq.queryTotalDeposits(accountNumber);

       double totalWithdrawals = sq.queryTotalWithdrawals(accountNumber);

       double balance = totalDeposits + totalWithdrawals;

       assertEquals(20, balance, "Balance was not 20");
    }
}

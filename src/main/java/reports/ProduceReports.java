package reports;

import dbutil.SQLQueries;

import java.sql.*;
import java.util.Scanner;

public class ProduceReports {

    public static void main(String[] args) throws SQLException {

        SQLQueries sq = new SQLQueries();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the relevant ID below:");
        int id = sc.nextInt();
        sq.queryCustomer(id);



    }


}

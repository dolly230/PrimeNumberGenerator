package com.jspiders.prime.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrimeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/primes_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    public static void savePrimes(List<Integer> primes) {
        String sql = "INSERT INTO primes (prime_number) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int prime : primes) {
                pstmt.setInt(1, prime);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getPrimesFromDB() {
        List<Integer> primes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM primes")) {
            while (rs.next()) {
                primes.add(rs.getInt("prime_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primes;
    }
    
    private static final String SELECT_PRIMES_QUERY = "SELECT prime_number FROM primes";

    public static List<Integer> getPrimesFromDB_new() {
        List<Integer> primes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRIMES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                primes.add(resultSet.getInt("prime_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return primes;
    }
}

package com.sid.repository;

import com.sid.model.BankMaster;
import com.sid.model.BankTransacation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BankSqlRepository implements IBankSqlRepository {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public List<BankTransacation> getAllBankTransactions() {
        List<BankTransacation> bankTransacations = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM BANK_TRANSACTION";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                BankTransacation bankTransacation = new BankTransacation();
                bankTransacation.setId(Integer.parseInt(resultSet.getString("ID")));
                bankTransacation.setName(resultSet.getString("NAME"));
                bankTransacation.setType(resultSet.getString("TYPE"));
                bankTransacation.setDate(resultSet.getString("DATE"));
                bankTransacation.setAmount(Double.parseDouble(resultSet.getString("AMOUNT")));

                bankTransacations.add(bankTransacation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return bankTransacations;

    }

    @Override
    public void register(BankMaster bankMaster, BankTransacation bankTransacation) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String insertIntoBankMaster =
                    "INSERT INTO bank_transaction VALUES (?,?,?, ?,?)";
            PreparedStatement preparedStatementBankTransaction = connection.prepareStatement(insertIntoBankMaster);
            preparedStatementBankTransaction.setInt(1,bankTransacation.getId());
            preparedStatementBankTransaction.setString(2,bankTransacation.getName());
            preparedStatementBankTransaction.setString(3,bankTransacation.getType());
            preparedStatementBankTransaction.setString(4,bankTransacation.getDate());
            preparedStatementBankTransaction.setDouble(5,bankTransacation.getAmount());

            String insertIntoBankTransaction =
                    "INSERT INTO bank_master (NAME,BALANCE,PASSWORD) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatementBankMaster = connection.prepareStatement(insertIntoBankTransaction);
            preparedStatementBankMaster.setString(1,bankMaster.getName());
            preparedStatementBankMaster.setDouble(2,bankMaster.getBalance());
            preparedStatementBankMaster.setString(3,bankMaster.getPassword());

            preparedStatementBankTransaction.executeQuery();
            preparedStatementBankMaster.executeQuery();

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

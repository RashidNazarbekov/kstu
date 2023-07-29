package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Accounting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountingDBHandler implements DAO<Accounting, Long> {
    private static Connection connection;
    @Override
    public List<Accounting> getAll() {
        String selectAll = "SELECT * FROM accountings";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Accounting> accountings = new ArrayList<Accounting>();
            while (rs.next()) {
                Accounting accounting = new Accounting();
                accounting.setAccountingId(rs.getLong("accounting_id"));
                accounting.setClientId(rs.getLong("client_id"));
                accounting.setAbonementId(rs.getLong("abonement_id"));
                accounting.setMonth(rs.getString("month"));
                accounting.setStatus_oplaty(rs.getBoolean("status_oplaty"));
                accountings.add(accounting);
            }
            return accountings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(connection != null)
                    connection.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Accounting getById(Long id) {
        String selectById = "SELECT * FROM accountings WHERE accounting_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Accounting accounting = new Accounting();
            while (rs.next()) {
                accounting.setAccountingId(rs.getLong("accounting_id"));
                accounting.setClientId(rs.getLong("client_id"));
                accounting.setAbonementId(rs.getLong("abonement_id"));
                accounting.setMonth(rs.getString("month"));
                accounting.setStatus_oplaty(rs.getBoolean("status_oplaty"));
            }
            return accounting;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(connection != null)
                    connection.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Accounting accounting) {
        String insert = "INSERT INTO accountings (abonement_id, month, status_oplaty, client_id) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(insert);
            ps.setLong(1, accounting.getAbonementId());
            ps.setString(2, accounting.getMonth());
            ps.setBoolean(3, accounting.getStatus_oplaty());
            ps.setLong(4, accounting.getClientId());
            ps.executeUpdate();
            System.out.println("The accounting inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null)
                    connection.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void update(Accounting accounting) {
        String update = "UPDATE accountings SET abonement_id=?, month=?, status_oplaty=?, client_id=? WHERE accounting_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(update);
            ps.setLong(1, accounting.getAbonementId());
            ps.setString(2, accounting.getMonth());
            ps.setBoolean(3, accounting.getStatus_oplaty());
            ps.setLong(4, accounting.getClientId());
            ps.setLong(5, accounting.getAccountingId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing accounting was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null)
                    connection.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        String delete = "DELETE FROM accountings WHERE accounting_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A accounting was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null)
                    connection.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}

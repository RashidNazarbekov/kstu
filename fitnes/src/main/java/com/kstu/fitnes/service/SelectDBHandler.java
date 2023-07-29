package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Accounting;
import com.kstu.fitnes.model.AccountingClient;
import com.kstu.fitnes.model.InstructorClients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectDBHandler {
    private static Connection connection;

    public List<InstructorClients> getInstructorClients() {
        String selectAll = "SELECT instructors.instructor_id, instructors.last_name, instructors.first_name, Count(clients.client_id) AS amount FROM instructors\n" +
                "INNER JOIN clients ON instructors.instructor_id = clients.instructor_id\n" +
                "GROUP BY instructors.instructor_id, instructors.last_name, instructors.first_name;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<InstructorClients> instructorClients = new ArrayList<InstructorClients>();
            while (rs.next()) {
                InstructorClients instructorClients1 = new InstructorClients();
                instructorClients1.setInstructorId(rs.getLong("instructors.instructor_id"));
                instructorClients1.setLastName(rs.getString("instructors.last_name"));
                instructorClients1.setFirstName(rs.getString("instructors.first_name"));
                instructorClients1.setCount(rs.getInt("amount"));
                instructorClients.add(instructorClients1);
            }
            return instructorClients;
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

    public List<InstructorClients> getInstructorPremium() {
        String selectAll = "SELECT instructors.instructor_id, instructors.last_name, instructors.first_name, Count(clients.client_id) AS amount, instructors.oklad*0.4 AS premium\n" +
                "FROM instructors \n" +
                "INNER JOIN clients ON instructors.instructor_id=clients.instructor_id\n" +
                "GROUP BY instructors.instructor_id, instructors.last_name, instructors.first_name, instructors.oklad*0.4\n" +
                "HAVING (((Count(clients.client_id))>1));";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<InstructorClients> instructorClients = new ArrayList<InstructorClients>();
            while (rs.next()) {
                InstructorClients instructorClients1 = new InstructorClients();
                instructorClients1.setInstructorId(rs.getLong("instructors.instructor_id"));
                instructorClients1.setLastName(rs.getString("instructors.last_name"));
                instructorClients1.setFirstName(rs.getString("instructors.first_name"));
                instructorClients1.setCount(rs.getInt("amount"));
                instructorClients1.setPremium(rs.getDouble("premium"));
                instructorClients.add(instructorClients1);
            }
            return instructorClients;
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

    public List<AccountingClient> getBadClients() {
        String selectAll = "SELECT clients.client_id,  clients.last_name, clients.first_name, accountings.status_oplaty FROM clients \n" +
                "INNER JOIN accountings ON clients.client_id = accountings.client_id\n" +
                "WHERE (((accountings.status_oplaty)=0));";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<AccountingClient> accountingClients = new ArrayList<AccountingClient>();
            while (rs.next()) {
                AccountingClient accountingClient = new AccountingClient();
                accountingClient.setClientId(rs.getLong("clients.client_id"));
                accountingClient.setLastName(rs.getString("clients.last_name"));
                accountingClient.setFirstName(rs.getString("clients.first_name"));
                accountingClient.setStatus_oplaty(rs.getBoolean("accountings.status_oplaty"));
                accountingClients.add(accountingClient);
            }
            return accountingClients;
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
}

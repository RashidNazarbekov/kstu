package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDBHandler implements DAO<Client, Long> {
    private static Connection connection = null;

    @Override
    public List<Client> getAll() {
        String selectAllClients = "SELECT * FROM clients";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAllClients);
            rs = ps.executeQuery();
            List<Client> clients = new ArrayList<Client>();
            while (rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getLong("client_id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setInstructor(rs.getLong("instructor_id"));
                clients.add(client);
            }
            return clients;
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
    public Client getById(Long id) {
        String selectClientById = "SELECT * FROM clients WHERE client_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectClientById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Client client = new Client();
            while (rs.next()) {
                client.setClientId(rs.getLong("client_id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setInstructor(rs.getLong("instructor_id"));
            }
            return client;
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
    public void add(Client client) {
        String insertClient = "INSERT INTO clients (last_name, first_name, phone_number, instructor_id) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(insertClient);
            ps.setString(1, client.getLastName());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getPhoneNumber());
            ps.setLong(4, client.getInstructor());
            ps.executeUpdate();
            System.out.println("The client inserted successfully!");
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
    public void update(Client client) {
        String updateClient = "UPDATE clients SET last_name=?, first_name=?, phone_number=?, instructor_id=? WHERE client_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(updateClient);
            ps.setString(1, client.getLastName());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getPhoneNumber());
            ps.setLong(4, client.getInstructor());
            ps.setLong(5, client.getClientId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing client was updated successfully!");
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
        String deleteClient = "DELETE FROM clients WHERE client_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(deleteClient);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A client was deleted successfully!");
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

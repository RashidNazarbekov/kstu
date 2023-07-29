package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Client;
import com.kstu.fitnes.model.Hall;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HallDBHandler implements DAO<Hall, Long> {
    private static Connection connection;
    @Override
    public List<Hall> getAll() {
        String selectAllHalls = "SELECT * FROM halls";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAllHalls);
            rs = ps.executeQuery();
            List<Hall> halls = new ArrayList<Hall>();
            while (rs.next()) {
                Hall hall = new Hall();
                hall.setHallId(rs.getLong("hall_id"));
                hall.setName(rs.getString("name"));
                halls.add(hall);
            }
            return halls;
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
    public Hall getById(Long id) {
        String selectHallById = "SELECT * FROM halls WHERE hall_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectHallById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Hall hall = new Hall();
            while (rs.next()) {
                hall.setHallId(rs.getLong("hall_id"));
                hall.setName(rs.getString("name"));
            }
            return hall;
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
    public void add(Hall hall) {
        String insertHall = "INSERT INTO halls (hall_id, name) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(insertHall);
            ps.setLong(1, hall.getHallId());
            ps.setString(2, hall.getName());
            ps.executeUpdate();
            System.out.println("The hall inserted successfully!");
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
    public void update(Hall hall) {
        String updateHall = "UPDATE halls SET name=? WHERE hall_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(updateHall);
            ps.setString(1, hall.getName());
            ps.setLong(2, hall.getHallId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing hall was updated successfully!");
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
        String deleteHall= "DELETE FROM halls WHERE hall_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(deleteHall);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A hall was deleted successfully!");
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

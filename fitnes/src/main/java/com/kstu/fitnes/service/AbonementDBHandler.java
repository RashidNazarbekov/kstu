package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Abonement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbonementDBHandler implements DAO<Abonement, Long> {
    private static Connection connection;
    @Override
    public List<Abonement> getAll() {
        String selectAll = "SELECT * FROM abonements";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Abonement> abonements = new ArrayList<Abonement>();
            while (rs.next()) {
                Abonement abonement = new Abonement();
                abonement.setAbonementId(rs.getLong("abonement_id"));
                abonement.setDescription(rs.getString("description"));
                abonement.setPrice(rs.getDouble("price"));
                abonement.setHall(rs.getLong("hall_id"));
                abonements.add(abonement);
            }
            return abonements;
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
    public Abonement getById(Long id) {
        String selectById = "SELECT * FROM abonements WHERE abonement_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Abonement abonement = new Abonement();
            while (rs.next()) {
                abonement.setAbonementId(rs.getLong("abonement_id"));
                abonement.setDescription(rs.getString("description"));
                abonement.setPrice(rs.getDouble("price"));
                abonement.setHall(rs.getLong("hall_id"));
            }
            return abonement;
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
    public void add(Abonement abonement) {
        String insert = "INSERT INTO abonements (description, price, hall_id) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(insert);
            ps.setString(1, abonement.getDescription());
            ps.setDouble(2, abonement.getPrice());
            ps.setLong(3, abonement.getHall());
            ps.executeUpdate();
            System.out.println("The abonement inserted successfully!");
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
    public void update(Abonement abonement) {
        String update = "UPDATE abonements SET description=?, price=?, hall_id=? WHERE abonement_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(update);
            ps.setString(1, abonement.getDescription());
            ps.setDouble(2, abonement.getPrice());
            ps.setLong(3, abonement.getHall());
            ps.setLong(4, abonement.getAbonementId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing abonement was updated successfully!");
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
        String delete = "DELETE FROM abonements WHERE abonement_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A abonement was deleted successfully!");
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

package com.kstu.fitnes.service;

import com.kstu.fitnes.model.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDBHandler implements DAO<Instructor, Long> {
    private static Connection connection;

    @Override
    public List<Instructor> getAll() {
        String selectAllInstructors = "SELECT * FROM instructors";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectAllInstructors);
            rs = ps.executeQuery();
            List<Instructor> instructors = new ArrayList<Instructor>();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setInstructorId(rs.getLong("instructor_id"));
                instructor.setFirstName(rs.getString("first_name"));
                instructor.setLastName(rs.getString("last_name"));
                instructor.setOklad(rs.getDouble("oklad"));
                instructors.add(instructor);
            }
            return instructors;
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
    public Instructor getById(Long id) {
        String selectInstructorById = "SELECT * FROM instructors WHERE instructor_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(selectInstructorById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Instructor instructor = new Instructor();
            while (rs.next()) {
                instructor.setInstructorId(rs.getLong("instructor_id"));
                instructor.setFirstName(rs.getString("first_name"));
                instructor.setLastName(rs.getString("last_name"));
                instructor.setOklad(rs.getDouble("oklad"));
            }
            return instructor;
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
    public void add(Instructor instructor) {
        String insertInstructor = "INSERT INTO instructors (last_name, first_name, oklad) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(insertInstructor);
            ps.setString(1, instructor.getLastName());
            ps.setString(2, instructor.getFirstName());
            ps.setDouble(3, instructor.getOklad());
            ps.executeUpdate();
            System.out.println("The instructor inserted successfully!");
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
    public void update(Instructor instructor) {
        String updateInstructor = "UPDATE instructors SET last_name=?, first_name=?, oklad=? WHERE instructor_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(updateInstructor);
            ps.setString(1, instructor.getLastName());
            ps.setString(2, instructor.getFirstName());
            ps.setDouble(3, instructor.getOklad());
            ps.setLong(4, instructor.getInstructorId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing instructor was updated successfully!");
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
        String deleteInstructor = "DELETE FROM instructors WHERE instructor_id=?";
        PreparedStatement ps = null;
        try {
            connection = DBCPDataSource.getConnection();
            ps = connection.prepareStatement(deleteInstructor);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A instructor was deleted successfully!");
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

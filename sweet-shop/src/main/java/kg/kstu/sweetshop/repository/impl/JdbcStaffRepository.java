package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Staff;
import kg.kstu.sweetshop.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcStaffRepository implements StaffRepository {

    private final Connector connector;

    @Override
    public List<Staff> getAll() {
        String selectAll = "exec select_staff";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Staff> staffs = new ArrayList<Staff>();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getLong("id"));
                staff.setFio(rs.getString("fio"));
                staff.setPositionId(rs.getLong("position_id"));
                staff.setPosition(rs.getString("position"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staffs.add(staff);
            }
            return staffs;
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
    public Staff getById(Long id) {
        String selectById = "exec get_staff_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Staff staff = new Staff();
            while (rs.next()) {
                staff.setId(rs.getLong("id"));
                staff.setFio(rs.getString("fio"));
                staff.setPositionId(rs.getLong("position_id"));
                staff.setPosition(rs.getString("position"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phone_number"));
            }
            return staff;
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
    public void add(Staff staff) {
        String insert = "exec insert_staff @fio=?, @position_id=?, @salary=?, @address=?, @phone_number=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setString(1, staff.getFio());
            ps.setLong(2, staff.getPositionId());
            ps.setDouble(3, staff.getSalary());
            ps.setString(4, staff.getAddress());
            ps.setString(5, staff.getPhoneNumber());
            ps.executeUpdate();
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
    public void update(Staff staff) {
        String update = "exec update_staff @id=?, @fio=?, @position_id=?, @salary=?, @address=?, @phone_number=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, staff.getId());
            ps.setString(2, staff.getFio());
            ps.setLong(3, staff.getPositionId());
            ps.setDouble(4, staff.getSalary());
            ps.setString(5, staff.getAddress());
            ps.setString(6, staff.getPhoneNumber());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing staff was updated successfully!");
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
        String delete = "exec delete_staff @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A staff was deleted successfully!");
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

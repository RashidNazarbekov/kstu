package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Measurement;
import kg.kstu.sweetshop.repository.MeasurementRepository;
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
public class JdbcMeasurementRepository implements MeasurementRepository {

    private final Connector connector;

    @Override
    public List<Measurement> getAll() {
        String selectAll = "exec select_measurement";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Measurement> measurements = new ArrayList<Measurement>();
            while (rs.next()) {
                Measurement measurement = new Measurement();
                measurement.setId(rs.getLong("id"));
                measurement.setName(rs.getString("name"));
                measurements.add(measurement);
            }
            return measurements;
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
    public Measurement getById(Long id) {
        String selectById = "exec get_measurement_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Measurement measurement = new Measurement();
            while (rs.next()) {
                measurement.setId(rs.getLong("id"));
                measurement.setName(rs.getString("name"));
            }
            return measurement;
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
    public void add(Measurement measurement) {
        String insert = "exec insert_measurement @name=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setString(1, measurement.getName());
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
    public void update(Measurement measurement) {
        String update = "exec update_measurement @id=?, @name=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, measurement.getId());
            ps.setString(2, measurement.getName());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing measurement was updated successfully!");
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
        String delete = "exec delete_measurement @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A measurement was deleted successfully!");
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

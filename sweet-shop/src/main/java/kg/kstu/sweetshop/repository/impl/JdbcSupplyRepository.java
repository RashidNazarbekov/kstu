package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Product;
import kg.kstu.sweetshop.models.Supply;
import kg.kstu.sweetshop.repository.SupplyRepository;
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
public class JdbcSupplyRepository implements SupplyRepository {

    private final Connector connector;

    @Override
    public List<Supply> getAll() {
        String selectAll = "exec select_supply";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Supply> supplies = new ArrayList<Supply>();
            while (rs.next()) {
                Supply supply = new Supply();
                supply.setId(rs.getLong("id"));
                supply.setName(rs.getString("name"));
                supply.setMeasurementId(rs.getLong("measurement_id"));
                supply.setMeasurement(rs.getString("measurement"));
                supply.setAmount(rs.getDouble("amount"));
                supply.setSum(rs.getDouble("sum"));
                supplies.add(supply);
            }
            return supplies;
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
    public Supply getById(Long id) {
        String selectById = "exec get_supply_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Supply supply = new Supply();
            while (rs.next()) {
                supply.setId(rs.getLong("id"));
                supply.setName(rs.getString("name"));
                supply.setMeasurementId(rs.getLong("measurement_id"));
                supply.setMeasurement(rs.getString("measurement"));
                supply.setAmount(rs.getDouble("amount"));
                supply.setSum(rs.getDouble("sum"));
            }
            return supply;
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
    public void add(Supply supply) {
        String insert = "exec insert_supply @name=?, @measurement_id=?, @amount=?, @sum=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setString(1, supply.getName());
            ps.setLong(2, supply.getMeasurementId());
            ps.setDouble(3, supply.getAmount());
            ps.setDouble(4, supply.getSum());
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
    public void update(Supply supply) {
        String update = "exec update_supply @id=?, @name=?, @measurement_id=?, @amount=?, @sum=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, supply.getId());
            ps.setString(2, supply.getName());
            ps.setLong(3, supply.getMeasurementId());
            ps.setDouble(4, supply.getAmount());
            ps.setDouble(5, supply.getSum());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing supply was updated successfully!");
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
        String delete = "exec delete_supply @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A supply was deleted successfully!");
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

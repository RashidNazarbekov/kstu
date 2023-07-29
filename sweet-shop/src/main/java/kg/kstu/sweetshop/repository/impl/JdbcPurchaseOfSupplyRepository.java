package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.PurchaseOfSupply;
import kg.kstu.sweetshop.models.Supply;
import kg.kstu.sweetshop.repository.PurchaseOfSupplyRepository;
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
public class JdbcPurchaseOfSupplyRepository implements PurchaseOfSupplyRepository {

    private final Connector connector;

    @Override
    public List<PurchaseOfSupply> getAll() {
        String selectAll = "exec select_purchase_of_supply";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<PurchaseOfSupply> purchaseOfSupplies = new ArrayList<PurchaseOfSupply>();
            while (rs.next()) {
                PurchaseOfSupply purchase = new PurchaseOfSupply();
                purchase.setId(rs.getLong("id"));
                purchase.setSupplyId(rs.getLong("supply_id"));
                purchase.setSupply(rs.getString("supply"));
                purchase.setAmount(rs.getDouble("amount"));
                purchase.setSum(rs.getDouble("sum"));
                purchase.setDate(rs.getDate("date"));
                purchase.setStaffId(rs.getLong("staff_id"));
                purchase.setStaff(rs.getString("staff"));
                purchaseOfSupplies.add(purchase);
            }
            return purchaseOfSupplies;
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
    public PurchaseOfSupply getById(Long id) {
        String selectById = "exec get_purchase_of_supply_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            PurchaseOfSupply purchase = new PurchaseOfSupply();
            while (rs.next()) {
                purchase.setId(rs.getLong("id"));
                purchase.setSupplyId(rs.getLong("supply_id"));
                purchase.setSupply(rs.getString("supply"));
                purchase.setAmount(rs.getDouble("amount"));
                purchase.setSum(rs.getDouble("sum"));
                purchase.setDate(rs.getDate("date"));
                purchase.setStaffId(rs.getLong("staff_id"));
                purchase.setStaff(rs.getString("staff"));
            }
            return purchase;
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
    public String add(PurchaseOfSupply purchase) {
        String insert = "exec insert_purchase_of_supply @supply_id=?, @amount=?, @sum=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setLong(1, purchase.getSupplyId());
            ps.setDouble(2, purchase.getAmount());
            ps.setDouble(3, purchase.getSum());
            ps.setLong(4, purchase.getStaffId());
            rs = ps.executeQuery();
            String response = "";
            while (rs.next()) {
                response = rs.getString("response");
            }
            System.out.println(response);
            return response;
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
        return null;
    }

    @Override
    public String update(PurchaseOfSupply purchase) {
        String update = "exec update_purchase_of_supply @id=?, @supply_id=?, @amount=?, @sum=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, purchase.getId());
            ps.setLong(2, purchase.getSupplyId());
            ps.setDouble(3, purchase.getAmount());
            ps.setDouble(4, purchase.getSum());
            ps.setLong(5, purchase.getStaffId());
            rs = ps.executeQuery();
            String response = "";
            while (rs.next()) {
                response = rs.getString("response");
            }
            return response;
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
        return null;
    }

    @Override
    public void delete(Long id) {
        String delete = "exec delete_purchase_of_supply @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A purchase of supply was deleted successfully!");
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

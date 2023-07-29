package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.SaleOfProduct;
import kg.kstu.sweetshop.repository.SaleOfProductRepository;
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
public class JdbcSaleOfProductRepository implements SaleOfProductRepository {

    private final Connector connector;

    @Override
    public List<SaleOfProduct> getAll() {
        String selectAll = "exec select_sale_of_product";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<SaleOfProduct> saleOfProducts = new ArrayList<SaleOfProduct>();
            while (rs.next()) {
                SaleOfProduct saleOfProduct = new SaleOfProduct();
                saleOfProduct.setId(rs.getLong("id"));
                saleOfProduct.setProductId(rs.getLong("product_id"));
                saleOfProduct.setProduct(rs.getString("product"));
                saleOfProduct.setAmount(rs.getDouble("amount"));
                saleOfProduct.setDate(rs.getDate("date"));
                saleOfProduct.setStaffId(rs.getLong("staff_id"));
                saleOfProduct.setStaff(rs.getString("staff"));
                saleOfProducts.add(saleOfProduct);
            }
            return saleOfProducts;
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
    public SaleOfProduct getById(Long id) {
        String selectById = "exec get_sale_of_product_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            SaleOfProduct saleOfProduct = new SaleOfProduct();
            while (rs.next()) {
                saleOfProduct.setId(rs.getLong("id"));
                saleOfProduct.setProductId(rs.getLong("product_id"));
                saleOfProduct.setProduct(rs.getString("product"));
                saleOfProduct.setAmount(rs.getDouble("amount"));
                saleOfProduct.setDate(rs.getDate("date"));
                saleOfProduct.setStaffId(rs.getLong("staff_id"));
                saleOfProduct.setStaff(rs.getString("staff"));
            }
            return saleOfProduct;
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
    public String add(SaleOfProduct saleOfProduct) {
        String insert = "exec insert_sale_of_product @product_id=?, @amount=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setLong(1, saleOfProduct.getProductId());
            ps.setDouble(2, saleOfProduct.getAmount());
            ps.setLong(3, saleOfProduct.getStaffId());
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
    public String update(SaleOfProduct saleOfProduct) {
        String update = "exec update_sale_of_product @id=?, @product_id=?, @amount=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, saleOfProduct.getId());
            ps.setLong(2, saleOfProduct.getProductId());
            ps.setDouble(3, saleOfProduct.getAmount());
            ps.setLong(4, saleOfProduct.getStaffId());
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
        String delete = "exec delete_sale_of_product @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A sale of product was deleted successfully!");
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

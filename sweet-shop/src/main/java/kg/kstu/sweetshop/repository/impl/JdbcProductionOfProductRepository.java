package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.ProductionOfProduct;
import kg.kstu.sweetshop.repository.ProductionOfProductRepository;
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
public class JdbcProductionOfProductRepository implements ProductionOfProductRepository {

    private final Connector connector;

    @Override
    public List<ProductionOfProduct> getAll() {
        String selectAll = "exec select_production_of_product";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<ProductionOfProduct> productionOfProducts = new ArrayList<ProductionOfProduct>();
            while (rs.next()) {
                ProductionOfProduct product = new ProductionOfProduct();
                product.setId(rs.getLong("id"));
                product.setProductId(rs.getLong("product_id"));
                product.setProduct(rs.getString("product"));
                product.setAmount(rs.getDouble("amount"));
                product.setDate(rs.getDate("date"));
                product.setStaffId(rs.getLong("staff_id"));
                product.setStaff(rs.getString("staff"));
                productionOfProducts.add(product);
            }
            return productionOfProducts;
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
    public ProductionOfProduct getById(Long id) {
        String selectById = "exec get_production_of_product_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            ProductionOfProduct product = new ProductionOfProduct();
            while (rs.next()) {
                product.setId(rs.getLong("id"));
                product.setProductId(rs.getLong("product_id"));
                product.setProduct(rs.getString("product"));
                product.setAmount(rs.getDouble("amount"));
                product.setDate(rs.getDate("date"));
                product.setStaffId(rs.getLong("staff_id"));
                product.setStaff(rs.getString("staff"));
            }
            return product;
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
    public String add(ProductionOfProduct product) {
        String insert = "exec insert_production_of_product @product_id=?, @amount=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setLong(1, product.getProductId());
            ps.setDouble(2, product.getAmount());
            ps.setLong(3, product.getStaffId());
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
    public String update(ProductionOfProduct product) {
        String update = "exec update_production_of_product @id=?, @product_id=?, @amount=?, @staff_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, product.getId());
            ps.setLong(2, product.getProductId());
            ps.setDouble(3, product.getAmount());
            ps.setLong(4, product.getStaffId());
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
        String delete = "exec delete_production_of_product @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A production of product was deleted successfully!");
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

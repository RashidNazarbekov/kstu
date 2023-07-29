package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Ingredient;
import kg.kstu.sweetshop.repository.IngredientRepository;
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
public class JdbcIngredientRepository implements IngredientRepository {

    private final Connector connector;

    @Override
    public List<Ingredient> getAll() {
        String selectAll = "exec select_ingredient";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getLong("id"));
                ingredient.setProductId(rs.getLong("product_id"));
                ingredient.setProduct(rs.getString("product"));
                ingredient.setSupplyId(rs.getLong("supply_id"));
                ingredient.setSupply(rs.getString("supply"));
                ingredient.setAmount(rs.getDouble("amount"));
                ingredients.add(ingredient);
            }
            return ingredients;
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
    public List<Ingredient> getAllByProductId(Long id) {
        String selectAll = "exec get_ingredient_by_product_id @product_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getLong("id"));
                ingredient.setProductId(rs.getLong("product_id"));
                ingredient.setProduct(rs.getString("product"));
                ingredient.setSupplyId(rs.getLong("supply_id"));
                ingredient.setSupply(rs.getString("supply"));
                ingredient.setAmount(rs.getDouble("amount"));
                ingredients.add(ingredient);
            }
            return ingredients;
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
    public Ingredient getById(Long id) {
        String selectById = "exec get_ingredient_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Ingredient ingredient = new Ingredient();
            while (rs.next()) {
                ingredient.setId(rs.getLong("id"));
                ingredient.setProductId(rs.getLong("product_id"));
                ingredient.setProduct(rs.getString("product"));
                ingredient.setSupplyId(rs.getLong("supply_id"));
                ingredient.setSupply(rs.getString("supply"));
                ingredient.setAmount(rs.getDouble("amount"));
            }
            return ingredient;
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
    public String add(Ingredient ingredient) {
        String insert = "exec insert_ingredient @product_id=?, @supply_id=?, @amount=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setLong(1, ingredient.getProductId());
            ps.setLong(2, ingredient.getSupplyId());
            ps.setDouble(3, ingredient.getAmount());
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
        return "Непредвиденная ошибка на сервере";
    }

    @Override
    public String update(Ingredient ingredient) {
        String update = "exec update_ingredient @id=?, @product_id=?, @supply_id=?, @amount=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, ingredient.getId());
            ps.setLong(2, ingredient.getProductId());
            ps.setLong(3, ingredient.getSupplyId());
            ps.setDouble(4, ingredient.getAmount());
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
        String delete = "exec delete_ingredient @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A ingredient was deleted successfully!");
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

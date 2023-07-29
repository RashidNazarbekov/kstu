package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Budget;
import kg.kstu.sweetshop.models.Ingredient;
import kg.kstu.sweetshop.repository.BudgetRepository;
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
public class JdbcBudgetRepository implements BudgetRepository {

    private final Connector connector;

    @Override
    public List<Budget> getAll() {
        String selectAll = "exec select_budget";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Budget> budgets = new ArrayList<Budget>();
            while (rs.next()) {
                Budget budget = new Budget();
                budget.setId(rs.getLong("id"));
                budget.setSumOfBudget(rs.getDouble("sum_of_budget"));
                budget.setPercent(rs.getDouble("percent"));
                budget.setBonus(rs.getDouble("bonus"));
                budgets.add(budget);
            }
            return budgets;
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
    public Budget getById(Long id) {
        String selectById = "exec get_budget_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Budget budget = new Budget();
            while (rs.next()) {
                budget.setId(rs.getLong("id"));
                budget.setSumOfBudget(rs.getDouble("sum_of_budget"));
                budget.setPercent(rs.getDouble("percent"));
                budget.setBonus(rs.getDouble("bonus"));
            }
            return budget;
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
    public int update(Budget budget) {
        String update = "exec update_budget @id=?, @sumOfBudget=?, @percent=?, @bonus=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, budget.getId());
            ps.setDouble(2, budget.getSumOfBudget());
            ps.setDouble(3, budget.getPercent());
            ps.setDouble(4, budget.getBonus());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing budget was updated successfully!");
                return 1;
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
        return 0;
    }
}

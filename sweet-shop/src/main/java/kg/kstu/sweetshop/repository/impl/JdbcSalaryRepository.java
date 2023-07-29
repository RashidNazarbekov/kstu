package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Salary;
import kg.kstu.sweetshop.models.YearMonth;
import kg.kstu.sweetshop.repository.SalaryRepository;
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
public class JdbcSalaryRepository implements SalaryRepository {

    private final Connector connector;

    @Override
    public List<Salary> getAll() {
        String selectAll = "exec select_salary";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Salary> salaries = new ArrayList<Salary>();
            while (rs.next()) {
                Salary salary = new Salary();
                salary.setId(rs.getLong("id"));
                salary.setYear(rs.getInt("year"));
                salary.setMonth(rs.getString("month"));
                salary.setStaffId(rs.getLong("staff_id"));
                salary.setStaff(rs.getString("staff"));
                salary.setPurchaseAmount(rs.getDouble("purchase_amount"));
                salary.setProductionAmount(rs.getDouble("production_amount"));
                salary.setSaleAmount(rs.getDouble("sale_amount"));
                salary.setSumAmount(rs.getDouble("sum_amount"));
                salary.setOklad(rs.getDouble("oklad"));
                salary.setSumSalary(rs.getDouble("sum_salary"));
                salary.setIsIssued(rs.getBoolean("is_issued"));
                salaries.add(salary);
            }
            return salaries;
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
    public List<Salary> getAllByYearAndMonth(Integer year, String month) {
        String selectAll = "exec select_salary_by_year_month @year=?, @month=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            ps.setInt(1, year);
            ps.setString(2, month);
            rs = ps.executeQuery();
            List<Salary> salaries = new ArrayList<Salary>();
            while (rs.next()) {
                Salary salary = new Salary();
                salary.setId(rs.getLong("id"));
                salary.setYear(rs.getInt("year"));
                salary.setMonth(rs.getString("month"));
                salary.setStaffId(rs.getLong("staff_id"));
                salary.setStaff(rs.getString("staff"));
                salary.setPurchaseAmount(rs.getDouble("purchase_amount"));
                salary.setProductionAmount(rs.getDouble("production_amount"));
                salary.setSaleAmount(rs.getDouble("sale_amount"));
                salary.setSumAmount(rs.getDouble("sum_amount"));
                salary.setOklad(rs.getDouble("oklad"));
                salary.setSumSalary(rs.getDouble("sum_salary"));
                salary.setIsIssued(rs.getBoolean("is_issued"));
                salaries.add(salary);
            }
            return salaries;
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
    public Salary getById(Long id) {
        String selectById = "exec get_salary_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Salary salary = new Salary();
            while (rs.next()) {
                salary.setId(rs.getLong("id"));
                salary.setYear(rs.getInt("year"));
                salary.setMonth(rs.getString("month"));
                salary.setStaffId(rs.getLong("staff_id"));
                salary.setStaff(rs.getString("staff"));
                salary.setPurchaseAmount(rs.getDouble("purchase_amount"));
                salary.setProductionAmount(rs.getDouble("production_amount"));
                salary.setSaleAmount(rs.getDouble("sale_amount"));
                salary.setSumAmount(rs.getDouble("sum_amount"));
                salary.setOklad(rs.getDouble("oklad"));
                salary.setSumSalary(rs.getDouble("sum_salary"));
                salary.setIsIssued(rs.getBoolean("is_issued"));
            }
            return salary;
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
    public void add(YearMonth yearMonth) {
        String insert = "exec insert_salary @year=?, @month=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setInt(1, yearMonth.getYear());
            ps.setInt(2, yearMonth.getMonth());
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
    public void update(Salary salary) {
        String update = "exec update_salary @id=?, @sum_salary=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setDouble(1, salary.getId());
            ps.setDouble(2, salary.getSumSalary());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Salary was updated successfully!");
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
        String delete = "exec delete_salary @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A salary was deleted successfully!");
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
    public String issueSalary(YearMonth yearMonth) {
        String insert = "exec issue_salary @year=?, @month=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setInt(1, yearMonth.getYear());
            ps.setInt(2, yearMonth.getMonth());
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
}

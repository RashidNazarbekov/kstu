package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Credit;
import kg.kstu.sweetshop.repository.CreditRepository;
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
public class JdbcCreditRepository implements CreditRepository {

    private final Connector connector;

    @Override
    public List<Credit> getAll() {
        String selectAll = "exec select_credit";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Credit> credits = new ArrayList<Credit>();
            while (rs.next()) {
                Credit credit = new Credit();
                credit.setId(rs.getLong("id"));
                credit.setSum(rs.getDouble("sum"));
                credit.setYears(rs.getInt("years"));
                credit.setDate(rs.getDate("date"));
                credit.setStatus(rs.getBoolean("status"));
                credits.add(credit);
            }
            return credits;
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
    public Credit getById(Long id) {
        String selectById = "exec get_credit_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Credit credit = new Credit();
            while (rs.next()) {
                credit.setId(rs.getLong("id"));
                credit.setSum(rs.getDouble("sum"));
                credit.setYears(rs.getInt("years"));
                credit.setDate(rs.getDate("date"));
                credit.setStatus(rs.getBoolean("status"));
            }
            return credit;
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
    public String add(Credit Credit) {
        String insert = "exec insert_credit @sum=?, @years=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setDouble(1, Credit.getSum());
            ps.setInt(2, Credit.getYears());
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
    public void delete(Long id) {
        String delete = "exec delete_credit @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(delete);
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A credit was deleted successfully!");
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

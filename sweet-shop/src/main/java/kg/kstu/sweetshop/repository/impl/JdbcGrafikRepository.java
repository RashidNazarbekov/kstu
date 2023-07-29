package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Grafik;
import kg.kstu.sweetshop.models.Ingredient;
import kg.kstu.sweetshop.models.PurchaseOfSupply;
import kg.kstu.sweetshop.repository.GrafikRepository;
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
public class JdbcGrafikRepository implements GrafikRepository {

    private final Connector connector;

    @Override
    public List<Grafik> getAll() {
        String selectAll = "exec select_grafik";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Grafik> grafiks = new ArrayList<Grafik>();
            while (rs.next()) {
                Grafik grafik = new Grafik();
                grafik.setId(rs.getLong("id"));
                grafik.setSum(rs.getDouble("sum"));
                grafik.setPart(rs.getDouble("part"));
                grafik.setPercent(rs.getDouble("percent"));
                grafik.setFine(rs.getDouble("fine"));
                grafik.setDate(rs.getDate("date"));
                grafik.setStatus(rs.getBoolean("status"));
                grafiks.add(grafik);
            }
            return grafiks;
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
    public List<Grafik> getAllByCreditId(Long creditId) {
        String selectAll = "exec get_grafik_by_credit @credit_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            ps.setLong(1, creditId);
            rs = ps.executeQuery();
            List<Grafik> grafiks = new ArrayList<Grafik>();
            while (rs.next()) {
                Grafik grafik = new Grafik();
                grafik.setId(rs.getLong("id"));
                grafik.setSum(rs.getDouble("sum"));
                grafik.setPart(rs.getDouble("part"));
                grafik.setPercent(rs.getDouble("percent"));
                grafik.setFine(rs.getDouble("fine"));
                grafik.setDate(rs.getDate("date"));
                grafik.setStatus(rs.getBoolean("status"));
                grafiks.add(grafik);
            }
            return grafiks;
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
    public String payCredit(Long id) {
        String insert = "exec pay_credit @grafik_id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(insert);
            ps.setLong(1, id);
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
}

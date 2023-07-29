package kg.kstu.sweetshop.repository.impl;

import kg.kstu.sweetshop.Connector;
import kg.kstu.sweetshop.models.Bank;
import kg.kstu.sweetshop.models.Budget;
import kg.kstu.sweetshop.repository.BankRepository;
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
public class JdbcBankRepository implements BankRepository {

    private final Connector connector;

    @Override
    public List<Bank> getAll() {
        String selectAll = "exec select_bank";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectAll);
            rs = ps.executeQuery();
            List<Bank> banks = new ArrayList<Bank>();
            while (rs.next()) {
                Bank bank = new Bank();
                bank.setId(rs.getLong("id"));
                bank.setInterestRate(rs.getDouble("interest_rate"));
                bank.setFine(rs.getDouble("fine"));
                banks.add(bank);
            }
            return banks;
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
    public Bank getById(Long id) {
        String selectById = "exec get_bank_by_id @id=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectById);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Bank bank = new Bank();
            while (rs.next()) {
                bank.setId(rs.getLong("id"));
                bank.setInterestRate(rs.getDouble("interest_rate"));
                bank.setFine(rs.getDouble("fine"));
            }
            return bank;
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
    public int update(Bank bank) {
        String update = "exec update_bank @id=?, @interest_rate=?, @fine=?";
        Connection connection = connector.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(update);
            ps.setLong(1, bank.getId());
            ps.setDouble(2, bank.getInterestRate());
            ps.setDouble(3, bank.getFine());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing bank was updated successfully!");
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

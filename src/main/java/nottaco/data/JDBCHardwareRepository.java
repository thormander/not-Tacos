package nottaco.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.stereotype.Repository;

import nottaco.Hardware;


@Repository
public class JDBCHardwareRepository implements HardwareRepository {
    private JdbcTemplate jdbcTemplate;
    
    public JDBCHardwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Iterable<Hardware> findAll() {
        return jdbcTemplate.query("select id, name, type from Hardware",this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findById(String id) {
        List<Hardware> results = jdbcTemplate.query("select id, name, type from Hardware where id=?",this::mapRowToHardware,id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Hardware save(Hardware ingredient) {
        jdbcTemplate.update("insert into Hardware (id, name, type) values (?, ?, ?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType().toString());
        return ingredient;
    }
    private Hardware mapRowToHardware(ResultSet row, int rowNum) throws SQLException {
    return new Hardware(
        row.getString("id"),
        row.getString("name"),
        Hardware.Type.valueOf(row.getString("type")));
    }
    
}

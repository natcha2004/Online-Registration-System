package th.ac.tu.cs.services.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.ac.tu.cs.services.model.Request;

@Repository
public class JdbcRequestRepository implements RequestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Request request) {
        return jdbcTemplate.update("INSERT INTO request (username, password) VALUES(?,?,?)",
                new Object[] { request.getUsername(), request.getPassword() });
    }

    @Override
    public Request findByStudentId(String studentId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM forms WHERE studentId=?",
                    BeanPropertyRowMapper.newInstance(Request.class), studentId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

}

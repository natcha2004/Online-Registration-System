package th.ac.tu.cs.services.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.ac.tu.cs.services.model.FreeTime;

import java.util.List;
@Repository
public class JdbcFreeTimeRepository implements FreeTimeRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int save(FreeTime time) {
        return jdbcTemplate.update("INSERT INTO FreeTime (Date, FirstName, LastName, StartTime, EndTime, Annotation) VALUES(?,?,?,?,?,?)",
                new Object[] { time.getDate(), time.getFirstName(), time.getLastName(), time.getStartTime(), time.getEndTime(), time.getAnnotation() });
    }

    @Override
    public int update(FreeTime time) {
        return jdbcTemplate.update("UPDATE FreeTime SET Date=?, FirstName=?, LastName=?, StartTime=?, EndTime=?, Annotation=? WHERE Id=?",
                new Object[] { time.getDate(), time.getFirstName(), time.getLastName(), time.getStartTime(), time.getEndTime(), time.getAnnotation(), time.getId() });
    }

    @Override
    public FreeTime findById(int id) {
        try {
            FreeTime freeTime = jdbcTemplate.queryForObject("SELECT * FROM FreeTime WHERE Id=?",
                    BeanPropertyRowMapper.newInstance(FreeTime.class), id);

            return freeTime;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM FreeTime WHERE Id=?", id);
    }

    @Override
    public FreeTime findByFirstName(String firstName) {
        String q = "SELECT * from FreeTime WHERE FirstName LIKE '%" + firstName + "%'";
        try {
            FreeTime freeTime = jdbcTemplate.queryForObject(q, BeanPropertyRowMapper.newInstance(FreeTime.class));

            return freeTime;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        //return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(FreeTime.class));
    }

    @Override
    public List<FreeTime> findAll() {
        return jdbcTemplate.query("SELECT * from FreeTime", BeanPropertyRowMapper.newInstance(FreeTime.class));
    }

    /*@Override
    public FreeTime findByFirstName(String firstname) {
        return null;
    }*/

    /*@Override
    public FreeTime findByLastName(String lastName) {
        return null;
    }*/

    @Override
    public List<FreeTime> findByFirstNameContaining(String firstName) {
        String q = "SELECT * from FreeTime WHERE FirstName LIKE '%" + firstName + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(FreeTime.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from FreeTime");
    }
}

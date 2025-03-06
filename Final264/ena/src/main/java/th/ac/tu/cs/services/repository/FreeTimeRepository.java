package th.ac.tu.cs.services.repository;

import th.ac.tu.cs.services.model.FreeTime;

import java.util.List;

public interface FreeTimeRepository {
    int save(FreeTime time);
    int update(FreeTime time);
    FreeTime findById(int id);
    int deleteById(int id);

    FreeTime findByFirstName(String firstName);

    List<FreeTime> findAll();
    //FreeTime findByFirstName(String firstname);
   // FreeTime findByLastName(String firstName);
    List<FreeTime> findByFirstNameContaining(String firstName);
    int deleteAll();
}

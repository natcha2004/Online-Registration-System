package th.ac.tu.cs.services.repository;

import th.ac.tu.cs.services.model.Request;

public interface RequestRepository {
    int save(Request request);
    Request findByStudentId(String studentId);

}

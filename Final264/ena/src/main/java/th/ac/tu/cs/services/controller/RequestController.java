package th.ac.tu.cs.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.tu.cs.services.model.Request;
import th.ac.tu.cs.services.repository.RequestRepository;

@CrossOrigin(origins = "http://localhost:5678")
@RestController
@RequestMapping("/users")
public class RequestController {
    @Autowired
    RequestRepository requestRepository;
    @PostMapping(value = "/save")
    public ResponseEntity<String> createForm(@RequestBody Request request) {
        try {
            requestRepository.save(new Request( request.getUsername(), request.getPassword() ));
            return new ResponseEntity<>("Form was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/Forms/{studentId}")
    public ResponseEntity<Request> getFormByStudentId(@PathVariable("studentId") String studentId) {
        Request request = requestRepository.findByStudentId(studentId);

        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

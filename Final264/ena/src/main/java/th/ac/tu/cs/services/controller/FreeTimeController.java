package th.ac.tu.cs.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import th.ac.tu.cs.services.model.FreeTime;
import th.ac.tu.cs.services.repository.FreeTimeRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FreeTimeController {
    @Autowired
    FreeTimeRepository freeTimeRepository;
    @GetMapping("/freetimes")
    public ResponseEntity<List<FreeTime>> getAllFreeTimes(@RequestParam(required = false) String firstName) {
        try {
            List<FreeTime> freeTimes = new ArrayList<FreeTime>();

            if (firstName == null)
                freeTimeRepository.findAll().forEach(freeTimes::add);
            else
                freeTimeRepository.findByFirstNameContaining(firstName).forEach(freeTimes::add);

            if (freeTimes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(freeTimes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/freetimes/searchId/{Id}")
    public ResponseEntity<FreeTime> getFreeTimeById(@PathVariable("Id") int id) {
        FreeTime freeTime = freeTimeRepository.findById(id);

        if (freeTime != null) {
            return new ResponseEntity<>(freeTime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/freetimes/searchFirstName/{FirstName}")
    public ResponseEntity<FreeTime> getFreeTimeByFirstName(@PathVariable("FirstName") String firstName) {
        FreeTime freeTime = freeTimeRepository.findByFirstName(firstName);

        if (freeTime != null) {
            return new ResponseEntity<>(freeTime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/freetimes")
    public ResponseEntity<String> createFreeTime(@RequestBody FreeTime freeTime) {
        try {
            freeTimeRepository.save(new FreeTime(freeTime.getDate(), freeTime.getFirstName(), freeTime.getLastName(), freeTime.getStartTime(), freeTime.getEndTime(), freeTime.getAnnotation()));
            return new ResponseEntity<>("FreeTime was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/freetimes/{Id}")
    public ResponseEntity<String> updateFreeTime(@PathVariable("Id") int id, @RequestBody FreeTime freeTime) {
        FreeTime _freeTime = freeTimeRepository.findById(id);

        if (_freeTime != null) {
            _freeTime.setId(id);
            _freeTime.setFirstName(freeTime.getFirstName());
            _freeTime.setLastName(freeTime.getLastName());
            _freeTime.setStartTime(freeTime.getStartTime());
            _freeTime.setEndTime(freeTime.getEndTime());
            _freeTime.setAnnotation(freeTime.getAnnotation());

            freeTimeRepository.update(_freeTime);
            return new ResponseEntity<>("FreeTime was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find FreeTime with Id=" + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/freetimes/{Id}")
    public ResponseEntity<String> deleteFreeTime(@PathVariable("Id") int id) {
        try {
            int result = freeTimeRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find FreeTime with Id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("FreeTime was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete freetimes.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/freetimes")
    public ResponseEntity<String> deleteAllFreeTime() {
        try {
            int numRows = freeTimeRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " FreeTime(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete freetimes", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

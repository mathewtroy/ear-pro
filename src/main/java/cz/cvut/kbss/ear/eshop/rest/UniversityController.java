package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.EducationalInstitution;
import cz.cvut.kbss.ear.eshop.model.University;
import cz.cvut.kbss.ear.eshop.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalInstitution> getUniversityById(@PathVariable Integer id) {
        EducationalInstitution university = universityService.find(id);
        return university != null ? ResponseEntity.ok(university) : ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<EducationalInstitution> insertUniversity(@RequestBody University university) {
        universityService.persist(university);
        return ResponseEntity.ok(university);
    }
}

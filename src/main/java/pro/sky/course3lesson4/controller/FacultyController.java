package pro.sky.course3lesson4.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.course3lesson4.model.Faculty;
import pro.sky.course3lesson4.service.FacultyService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return facultyService.getFaculties();
    }

    @GetMapping(path = "/{id}")
    public Faculty getFacultyById(@PathVariable long id) {
        long facultyId = id;
        return facultyService.getById(facultyId);
    }

    @GetMapping(path = "/color/{color}")
    public List<Faculty> getByColor(@PathVariable String color) {
        return facultyService.getFacultiesByColor(color);
    }

    @PostMapping()
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty.getId(), faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/delete")
    public Faculty delete(@RequestParam("id") long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping(path = "/load")
    public HashMap<Long, Faculty> load() {
        return facultyService.loadExampleFaculties();
    }
}
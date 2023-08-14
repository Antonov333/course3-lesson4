package pro.sky.course3lesson4.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.course3lesson4.model.Student;
import pro.sky.course3lesson4.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService students;

    public StudentController(StudentService students) {
        this.students = students;
    }

    @GetMapping
    public List<Student> viewStudents() {
        return students.getStudents();
    }

    @GetMapping(path = "/load/{number}")
    public List<Student> loadStudents(@PathVariable int number) {
        return students.loadExampleStudents(number);
    }

    @PostMapping(path = "/post")
    public Student create(@RequestBody Student student) {
        Student createdStudent = students.createStudent(student);
        return createdStudent;
    }

    @PutMapping(path = "/put")
    public Student putStudent(@RequestBody Student student) {
        Student updatedStudent = students.updateStudent(student.getId(), student.getName(), student.getAge());
        return updatedStudent;
    }

    @DeleteMapping(path = "/delete")
    public Student expel(Student student) {
        Student expelledStudent = students.sendDown(student);
        return expelledStudent;
    }

    @GetMapping("/age/{age}")
    public List<Student> selectByAge(@PathVariable int age) {
        List<Student> selected = students.selectedByAge(age);
        return selected;
    }
}

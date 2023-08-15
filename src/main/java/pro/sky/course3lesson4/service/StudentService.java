package pro.sky.course3lesson4.service;

//import org.springframework.http.HttpStatusCode;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pro.sky.course3lesson4.exception.StudentAlreadyExistsException;
import pro.sky.course3lesson4.exception.StudentNotFoundException;
import pro.sky.course3lesson4.model.Faculty;
import pro.sky.course3lesson4.model.Student;
import pro.sky.course3lesson4.repository.StudentRepository;

import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        Example<Student> studentExample = Example.of(student);
        if (studentRepository.exists(studentExample)) {
            throw new StudentAlreadyExistsException();
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(long id, String newName, int newAge) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (newName != null) {
            existingStudent.setName(newName);
        }
        if (newAge > 15) {
            existingStudent.setAge(newAge);
        }
        return studentRepository.save(existingStudent);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> loadExampleStudents(int number) {
        for (int i = 0; i < number; i++) {
            studentRepository.save(new Student(i + 1, randomName(), randomAge(18, 25)));
        }
        return studentRepository.findAll();
    }

    public Student sendDown(Student student) {
        studentRepository.delete(student);
        return student;
    }

    public List<Student> selectedByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> selectedByAgeBetween(int a, int b) {

        if (a == b) {
            return studentRepository.findAllByAge(a);
        }

        int min, max;
        if (a < b) {
            min = a;
            max = b;
        } else {
            min = b;
            max = a;
        }

        return studentRepository.findByAgeBetween(min, max);

    }

    public Faculty getStudentFaculty(long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.getFaculty();
    }

    private String randomName() {
        Lorem lorem = new LoremIpsum();
        return lorem.getFirstName();
    }

    private int randomAge(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }

}

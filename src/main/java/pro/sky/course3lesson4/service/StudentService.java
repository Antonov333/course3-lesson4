package pro.sky.course3lesson4.service;

//import org.springframework.http.HttpStatusCode;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pro.sky.course3lesson2.exception.StudentAlreadyExistsException;
import pro.sky.course3lesson2.exception.StudentNotFoundException;
import pro.sky.course3lesson2.model.Student;
import pro.sky.course3lesson2.repository.StudentRepository;

import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //    CRUD

    public Student createStudent(Student student) {
        Example<Student> studentExample = Example.of(student);
        if (studentRepository.exists(studentExample)) {
            throw new StudentAlreadyExistsException();
        }
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        return studentRepository.findById(Long.valueOf(id)).orElseThrow(StudentNotFoundException::new);
    }

    public Student updateStudent(long id, String newName, int newAge) {
        Long idLong = Long.valueOf(id);
        Student existingStudent = studentRepository.findById(idLong).orElseThrow(StudentNotFoundException::new);
        if (newName != null) {
            existingStudent.setName(newName);
        }
        if (newAge > 15) {
            existingStudent.setAge(newAge);
        }
        return studentRepository.save(existingStudent);
    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
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
        Student expelledStudent = student;
        studentRepository.delete(student);
        return expelledStudent;
    }

    public List<Student> selectedByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    private String randomName() {
        Lorem lorem = new LoremIpsum();
        return lorem.getFirstName();
    }

    private int randomAge(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }

    private boolean checkStudentData(String name, int age) {
        return !name.equals("") & name != null & age > 16;
    }

}

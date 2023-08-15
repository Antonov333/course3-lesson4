package pro.sky.course3lesson4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.course3lesson4.model.Faculty;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findAllByColor(String color);

    List<Faculty> findAllByNameIgnoreCase(String name);

//    Optional<Faculty> findByStudent_Id(Long studentId);

}

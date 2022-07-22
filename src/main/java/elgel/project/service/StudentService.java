package elgel.project.service;

import elgel.project.model.Student;
import elgel.project.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    public Student saveStudent(Student student){
        if(student.getId() == null) {
            return iStudentRepository.save(student);
        }
        return null;
        }
//paginacion #de pagina
    public Page<Student> getAllStudent (Integer page, Integer size, boolean enablePagination){
        return iStudentRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
    }

    public Optional<Student> findById(Long id){
        return iStudentRepository.findById(id);
    }

    public void deleteStudent(Long id){
        iStudentRepository.deleteById(id);
    }
    public Student editStudent(Student student){
        if(student.getId() != null && iStudentRepository.existsById(student.getId())) {
            return iStudentRepository.save(student);
        }
        return null;
    }

    public Object existById(Long id) {
        return iStudentRepository.existsById(id);
    }
}

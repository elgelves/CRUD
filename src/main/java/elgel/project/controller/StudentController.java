package elgel.project.controller;

import elgel.project.model.Student;
import elgel.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student){
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(student));
    }
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") boolean enablePagination){

    return ResponseEntity.ok(studentService.getAllStudent(page, size, enablePagination));
    }
    @DeleteMapping(value="/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
    studentService.deleteStudent(id);
    ResponseEntity.ok(studentService.existById(id));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@Valid @RequestBody Student student) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.editStudent(student));
    }
}

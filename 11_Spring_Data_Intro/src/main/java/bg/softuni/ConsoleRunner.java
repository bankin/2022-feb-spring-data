package bg.softuni;

import bg.softuni.services.MajorService;
import bg.softuni.services.StudentService;
import bg.softuni.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Override
    public void run(String... strings) throws Exception {
        this.studentService.register();
        this.majorService.register();

//        Major major = new Major("Java DB Fundamentals");
//        example.entities.Student student = new example.entities.Student("John",new Date(), major);
//        majorService.create(major);
//        studentService.register(student);
    }
}
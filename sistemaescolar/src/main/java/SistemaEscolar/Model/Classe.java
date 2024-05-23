package SistemaEscolar.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Classe {
    @Id
    private String id;
    private List<String> listStudentsId;
    private String idTeacher;
    private String title;
    private String description;
    private String idMatter;
}

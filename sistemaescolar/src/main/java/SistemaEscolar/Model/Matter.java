package SistemaEscolar.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Matter {
    @Id
    private String id;
}

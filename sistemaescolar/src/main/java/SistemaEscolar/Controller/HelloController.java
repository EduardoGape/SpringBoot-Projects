package SistemaEscolar.Controller;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MongoTemplate mongoTemplate; // Inject the MongoDB template


    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            // Testar a conexão obtendo o nome do banco de dados
            String databaseName = mongoTemplate.getDb().getName();
            return "Conexão com MongoDB estabelecida. Banco de dados: " + databaseName;
        } catch (Exception e) {
            return "Erro ao conectar com MongoDB: " + e.getMessage();
        }
    }
}

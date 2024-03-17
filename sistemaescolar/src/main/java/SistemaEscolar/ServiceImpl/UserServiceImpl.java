package SistemaEscolar.ServiceImpl;

import SistemaEscolar.Model.User;
import SistemaEscolar.IService.UserService;
import SistemaEscolar.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            String teste = generateToken(email);
            return generateToken(email);
        }
        return null;
    }

    private String generateToken(String email) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            String base64Header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
            String base64Payload = Base64.getUrlEncoder().encodeToString(("{\"sub\":\"" + email + "\",\"iat\":" + nowMillis / 1000 + ",\"exp\":" + ((nowMillis / 1000) + 3600) + "}").getBytes());

            String dataToSign = base64Header + "." + base64Payload;
            String signature = signData(dataToSign, jwtSecret);

            return dataToSign + "." + signature;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String signData(String data, String secret) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] hashBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(hashBytes);
    }
    
    
}

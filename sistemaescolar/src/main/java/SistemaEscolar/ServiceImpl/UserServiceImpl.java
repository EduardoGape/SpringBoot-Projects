package SistemaEscolar.ServiceImpl;

import SistemaEscolar.Model.User;
import SistemaEscolar.IService.UserService;
import SistemaEscolar.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        user.setId(id); // Setar o ID recebido no objeto User
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

package group.boboaigaowei.dao.mysql.repo.impl;

import group.boboaigaowei.dao.mysql.entity.User;
import group.boboaigaowei.dao.mysql.repo.AbstractSqlRepo;
import group.boboaigaowei.dao.mysql.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao implements AbstractSqlRepo<User> {

    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Data Not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(Iterable<User> iterable) {
        return userRepository.saveAll(iterable);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll(Iterable<User> iterable) {
        userRepository.deleteAll(iterable);
    }

}

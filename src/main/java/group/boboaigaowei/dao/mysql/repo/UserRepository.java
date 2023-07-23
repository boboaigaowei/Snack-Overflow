package group.boboaigaowei.dao.mysql.repo;

import group.boboaigaowei.dao.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

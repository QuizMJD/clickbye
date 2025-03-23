package vn.hub.clickbye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hub.clickbye.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailOrUsername(String email, String username);

}
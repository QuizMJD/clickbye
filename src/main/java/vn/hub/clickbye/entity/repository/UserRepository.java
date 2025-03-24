package vn.hub.clickbye.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hub.clickbye.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
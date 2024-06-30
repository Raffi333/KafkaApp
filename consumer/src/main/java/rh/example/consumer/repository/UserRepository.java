package rh.example.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rh.example.consumer.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}

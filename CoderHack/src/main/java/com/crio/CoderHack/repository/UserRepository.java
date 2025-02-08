package com.crio.CoderHack.repository;

import com.crio.CoderHack.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
        List<User> findAllByOrderByScoreDesc();
        boolean existsByUsername(String username);
}

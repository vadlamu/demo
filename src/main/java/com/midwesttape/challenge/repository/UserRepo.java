package com.midwesttape.challenge.repository;

import com.midwesttape.challenge.model.IProfileProjection;
import com.midwesttape.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Optional<IProfileProjection> findProfileById(UUID id);

}

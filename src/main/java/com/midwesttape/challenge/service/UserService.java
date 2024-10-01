package com.midwesttape.challenge.service;

import com.midwesttape.challenge.converter.ProfileProjectionConverter;
import com.midwesttape.challenge.model.IProfileProjection;
import com.midwesttape.challenge.model.User;
import com.midwesttape.challenge.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private ProfileProjectionConverter profileProjectionConverter;

    private final UserRepo userRepo;

    public User getUser(final UUID userId) {
        return userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Do data for  " + userId));
    }

    public List<User> listUsers(final String currentUserId) {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream().map(user -> restrictUserInfoToOthers(currentUserId, user)).toList();
    }

    public User saveUser(final User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserByUsername(final String username) {
        return userRepo.findByUsername(username);
    }

    public void deleteUser(final UUID userId) {
        userRepo.deleteById(userId);
    }

    public User getProfile(final UUID userId) {
        Optional<IProfileProjection> profileProjection = userRepo.findProfileById(userId);
        return profileProjectionConverter.fromProjection(profileProjection
            .orElseThrow(() -> new EntityNotFoundException("No data available for userid:" + userId.toString())));
    }

    private User restrictUserInfoToOthers(final String currentUserId, final User user) {

        return currentUserId.equals(user.getId().toString()) ? user : mapOnlyBioAndNickNameToUser(user);

    }

    private User mapOnlyBioAndNickNameToUser(User user) {
        User u = new User();
        u.setBio(user.getBio());
        u.setNickName(user.getNickName());
        return u;
    }

}

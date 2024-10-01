package com.midwesttape.challenge.controller;

import com.midwesttape.challenge.model.User;
import com.midwesttape.challenge.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final String noAccessMessage = """
        "Message":"Please use appropriate access token.",
        "ErrorCode":"403"
        """;

    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(
        @CurrentSecurityContext(expression = "authentication") final JwtAuthenticationToken authentication,
        @PathVariable final String userId
    ) {
        final var currentUserId = authentication.getTokenAttributes().get("userId");
        final var authorized = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("SCOPE_ADMIN"));

        if (!authorized) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, noAccessMessage);
        }

        return getUserResponse((String) currentUserId, userId);

    }

    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers(@CurrentSecurityContext(expression = "authentication") final JwtAuthenticationToken authentication) {
        final var currentUserId = authentication.getTokenAttributes().get("userId");
        return new ResponseEntity<>(userService.listUsers(currentUserId.toString()), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public User updateUser(
        @CurrentSecurityContext(expression = "authentication") final JwtAuthenticationToken authentication,
        @PathVariable final String userId,
        @Valid @RequestBody final User user) {
        final var currentUserId = authentication.getTokenAttributes().get("userId");
        // make sure the current user is the same as the user being updated
        if (!currentUserId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        // set user id to current user to prevent allowing other users to be updated
        user.setId(UUID.fromString(userId));
        return userService.saveUser(user);
    }

    @PostMapping
    @PermitAll
    public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public void deleteUser(
        @CurrentSecurityContext(expression = "authentication") final JwtAuthenticationToken authentication,
        @PathVariable final String userId
    ) {
        final var currentUserId = authentication.getTokenAttributes().get("userId");
        final var authorized = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("SCOPE_ADMIN"));
        if (currentUserId.equals(userId) || authorized) {
            userService.deleteUser(UUID.fromString(userId));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private ResponseEntity<User> getUserResponse(String currentUserId, String userId) {
        UUID userUUID = UUID.fromString(userId);

        if (currentUserId.equals(userId)) {
            return new ResponseEntity<>(userService.getUser(userUUID), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.getProfile(userUUID), HttpStatus.OK);
        }
    }
}

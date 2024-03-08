package com.aggr.invest.service;

import com.aggr.invest.controller.CreateUserDTO;
import com.aggr.invest.controller.UpdateUserDTO;
import com.aggr.invest.entity.User;
import com.aggr.invest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDTO dto) {

        var u = new User(
                UUID.randomUUID(),
                dto.username(),
                dto.email(),
                dto.password(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(u);

        return userSaved.getUserId();

    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));

    }

    public void deleteById(String userId) {

        UUID uId = UUID.fromString(userId);
        var userExists = userRepository.existsById(uId);

        if (userExists)
            userRepository.deleteById(uId);

    }

    public void updateUserById(String userId, UpdateUserDTO dto) {

        UUID uId = UUID.fromString(userId);
        var userEntity =userRepository.findById(uId);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (user.getUsername() != null) {
                user.setUsername(dto.username());
            }

            if (user.getPassword() != null) {
                user.setPassword(dto.password());
            }

            userRepository.save(user);
        }


    }

    public List<User> listUsers() {

        return userRepository.findAll();
    }
}

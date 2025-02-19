package com.Messaging_System.infrastructure.adapter;

import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;
import com.Messaging_System.infrastructure.mapper.UserMapper;
import com.Messaging_System.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPort {

    private final UserRepository repository;

    @Override
    public UserModel save(UserModel model) {
        UserEntity entity = UserMapper.toEntity(model);
        return UserMapper.toModel(repository.save(entity));
    }

    @Override
    public void changeUserInformation(UserModel model) {
        UserEntity entity = UserMapper.toEntity(model);
        repository.save(entity);
    }

    @Override
    public UserModel findByEmail(String email) {
        UserEntity user = repository.findByEmail(email);
        return UserMapper.toModel(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public UserModel findById(UUID id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with specified ID not found"));
        return UserMapper.toModel(entity);
    }
}

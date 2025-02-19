package com.Messaging_System.application.service;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepositoryPort repository;



}

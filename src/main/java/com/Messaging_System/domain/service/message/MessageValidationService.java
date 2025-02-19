package com.Messaging_System.domain.service.message;

import com.Messaging_System.application.port.MessageRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageValidationService {

    private final MessageRepositoryPort repository;



}

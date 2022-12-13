package com.mk.nbp_api.service;

import com.mk.nbp_api.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final ComputerRepository computerRepository;

}

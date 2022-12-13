package com.mk.nbp_api.controller;


import com.mk.nbp_api.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

}

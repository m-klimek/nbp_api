package com.mk.nbp_api.controller;


import com.mk.nbp_api.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(path = "/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public String getComputers(Model model){
        model.addAttribute("allComputers", computerService.getAllComputers());
        return "computers";
    }

    @PostMapping("/convert")
    public String saveComputers(){
        computerService.saveComputersToDatabaseAfterConversion();
        return "redirect:/computers";
    }

    @GetMapping("/search")
    public String getComputersByKeyword(Model model, @Param("keyword") String keyword){
        model.addAttribute("allComputers", computerService.getSearchedComputers(keyword));
        model.addAttribute("keyword", keyword);
        return "computers";
    }

    @GetMapping("sort-name-asc")
    public String getComputersSortedByNameAscending(Model model){
        model.addAttribute("allComputers", computerService.getComputerSortedByNameAscending());
        return "computers";
    }

    @GetMapping("sort-name-desc")
    public String getComputersSortedByNameDescending(Model model){
        model.addAttribute("allComputers", computerService.getComputerSortedByNameDescending());
        return "computers";
    }

    @GetMapping("sort-date-asc")
    public String getComputersSortedByDateAscending(Model model){
        model.addAttribute("allComputers", computerService.getComputerSortedByDateAscending());
        return "computers";
    }

    @GetMapping("sort-date-desc")
    public String getComputersSortedByDateDescending(Model model){
        model.addAttribute("allComputers", computerService.getComputerSortedByDateDescending());
        return "computers";
    }

    @PostMapping("/save-to-xml")
    public String saveDatabaseToXmlFile() throws IOException {
        computerService.saveDatabaseToXml();
        return "redirect:/computers";
    }


}

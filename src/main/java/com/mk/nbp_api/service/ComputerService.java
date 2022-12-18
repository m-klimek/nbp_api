package com.mk.nbp_api.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mk.nbp_api.model.computer.Computer;
import com.mk.nbp_api.model.computer.Computers;
import com.mk.nbp_api.repository.ComputerRepository;
import jakarta.xml.bind.JAXB;
import lombok.RequiredArgsConstructor;
import org.apache.commons.httpclient.URIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final JsonService jsonService;

    public List<Computer> getAllComputers(){
        return computerRepository.findAll();
    }

    public List<Computer> getAllComputersWithConversionFromUsdToPln(){
        List<Computer> computerList = computerRepository.findAll();
        computerList.stream().forEach(computer -> {
            try {
                computer.setPlnCost((computer.getUsdCost())*(jsonService.getBidFromCurrency(computer.getPostingDate().toString())));
            } catch (URIException e) {
                throw new RuntimeException(e);
            }
        });
        return computerList;
    }

    public void saveComputersToDatabaseAfterConversion(){
        List<Computer> computerList = getAllComputersWithConversionFromUsdToPln();
        for(Computer computer : computerList){
            computerRepository.save(computer);
        }
    }

    public List<Computer> getSearchedComputers(String keyword){
        List<Computer> computerList = computerRepository.findAll();
        Predicate<Computer> byName = computer -> computer.getComputerName().toLowerCase().contains(keyword.toLowerCase());
        Predicate<Computer> byDate = computer -> computer.getPostingDate().toString().contains(keyword);
        if(keyword != null){
            computerList = computerList.stream().filter(byName.or(byDate)).collect(Collectors.toList());
        }
        return computerList;
    }

    public List<Computer> getComputerSortedByNameAscending(){
        List<Computer> computerList = computerRepository.findAll();

        List<Computer> sortedList = computerList.stream()
                .sorted(Comparator.comparing(Computer::getComputerName))
                .collect(Collectors.toList());
        return sortedList;
    }

    public List<Computer> getComputerSortedByNameDescending(){
        List<Computer> computerList = computerRepository.findAll();

        List<Computer> sortedList = computerList.stream()
                .sorted(Comparator.comparing(Computer::getComputerName).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    public List<Computer> getComputerSortedByDateAscending(){
        List<Computer> computerList = computerRepository.findAll();

        List<Computer> sortedList = computerList.stream()
                .sorted(Comparator.comparing(Computer::getPostingDate))
                .collect(Collectors.toList());
        return sortedList;
    }

    public List<Computer> getComputerSortedByDateDescending(){
        List<Computer> computerList = computerRepository.findAll();

        List<Computer> sortedList = computerList.stream()
                .sorted(Comparator.comparing(Computer::getPostingDate).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    public void saveDatabaseToXml() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<Computer> computerList = new ArrayList<>();
        Computers computers = new Computers();
        computers.setComputers(computerList);

        List<Computer> computerRepositoryAll = computerRepository.findAll();
        for(Computer computer : computerRepositoryAll){
            computers.getComputers().add(computer);
        }

        xmlMapper.findAndRegisterModules();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File("faktura.xml"), computers.getComputers());

    }



}

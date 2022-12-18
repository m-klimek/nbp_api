package com.mk.nbp_api.model.computer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
@JacksonXmlRootElement
public class Computers implements Serializable {


    @JacksonXmlElementWrapper(localName = "faktura")
    @JacksonXmlProperty(localName = "komputer")
    private List<Computer> computers;

}

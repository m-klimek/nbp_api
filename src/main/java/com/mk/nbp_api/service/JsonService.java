package com.mk.nbp_api.service;

import com.mk.nbp_api.model.json_response.Currency;
import lombok.RequiredArgsConstructor;
import org.apache.commons.httpclient.URIException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class JsonService {

    private final RestTemplate restTemplate;


    public Currency getCurrencyJson(String date) throws URIException {
        return restTemplate.getForObject(("https://api.nbp.pl/api/exchangerates/rates/C/USD/" + date + "/"), Currency.class);
    }

    public Double getBidFromCurrency(String date) throws URIException {
        Double bid = getCurrencyJson(date).getRates().get(0).getBid();
        return bid;
    }


}

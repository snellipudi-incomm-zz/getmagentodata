package com.incomm.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * this is service is for hitting magento API to get unauthorized transactions
 * in the specified date and time.
 */
@RestController
@RequestMapping("/payment")
public class MagentoService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ServicesUtils servicesUtils;

    protected Logger logger = LoggerFactory.getLogger(getClass());
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();

    @GetMapping(value = "/getMagentoData")
    public List<MagentoResponse> getMagentoData()
            throws MalformedURLException, UnknownHostException, UnsupportedEncodingException{

            MagentoDateRequest magentoDateRequest = new MagentoDateRequest();
            magentoDateRequest.setEndTime("2018-03-02 23:59:59");
            magentoDateRequest.setStartTime("2018-03-02 00:00:00");
        //logger.info("Entered getMagentoData method to it Magento API, userEvents" ,UserEvents.MAGENTOSERVICE);
       try {

           HttpEntity<String> httpEntity = new HttpEntity<>(mapper.writeValueAsString(magentoDateRequest), servicesUtils.getMagentoHeader());

           /*
           calling Magento API
            */
           //logger.info("message = calling magento API with start time "+ magentoDateRequest.getStartTime()+" and end date "+ magentoDateRequest.getEndTime());
           /*ResponseEntity<List<MagentoResponse>> magentoResponseList = restTemplate.exchange(servicesUtils.magentoEndpoint(magentoDateRequest), HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<MagentoResponse>>() {
           });*/
           ResponseEntity<List<MagentoResponse>> magentoResponseList = restTemplate.exchange(servicesUtils.magentoEndpoint(magentoDateRequest), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<MagentoResponse>>(){} );

           return magentoResponseList.getBody();

       }catch (JsonProcessingException jpe){

           //logger.error("message = Unexpected Exception Occurred while calling Magento API, userEvent=" + UserEvents.MAGENTOSERVICE);
           //logger.error(" error ", jpe + " userEvent " + UserEvents.MAGENTOSERVICE);
           return null;
       }
    }
}

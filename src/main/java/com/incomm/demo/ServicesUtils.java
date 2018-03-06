package com.incomm.demo;

//import com.incomm.paymentsettlement.settlementService.model.MagentoDateRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Date;
import java.util.Random;


@ConfigurationProperties(prefix = "magento")
@Configuration
public class ServicesUtils {

     //String magentoEndpoint;

     String securePayEndpoint;

     String tx_config;

     String tx_payment_config;

     String tx_sequence;

     String secret;

     int timeDifference;

     String magentobaseurl;

     String magentobatchurl;

     String bearerToken;



    /*
    creating http header for magento API
     */
    public HttpHeaders getMagentoHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+getBearerToken());
        return httpHeaders;
    }

    /*
    calculating start date for magento request.
     */
    public Date getStartDate(int timeDifference){
        Date date = new Date(System.currentTimeMillis() - (timeDifference * 60 * 60 * 1000) );
        return date;

    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    /*
    constructing magento endpoint url
     */
    public String magentoEndpoint(MagentoDateRequest magentoDateRequest){
        String magentoEndpoint = magentobaseurl+magentobatchurl+"?startTime="+magentoDateRequest.getStartTime()+"&endTime="+magentoDateRequest.getEndTime();
        return magentoEndpoint;
    }

    public int getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(int timeDifference) {
        this.timeDifference = timeDifference;
    }

    public String getMagentobaseurl() {
        return magentobaseurl;
    }

    public void setMagentobaseurl(String magentobaseurl) {
        this.magentobaseurl = magentobaseurl;
    }

    public String getMagentobatchurl() {
        return magentobatchurl;
    }

    public void setMagentobatchurl(String magentobatchurl) {
        this.magentobatchurl = magentobatchurl;
    }

    public String getTx_config() {
        return tx_config;
    }

    public void setTx_config(String tx_config) {
        this.tx_config = tx_config;
    }

    public String getTx_payment_config() {
        return tx_payment_config;
    }

    public void setTx_payment_config(String tx_payment_config) {
        this.tx_payment_config = tx_payment_config;
    }

    public String getTx_sequence() {
        Random random = new Random();
        tx_sequence = String.valueOf(random.nextInt(1000));
        return tx_sequence;
    }

    public void setTx_sequence(String tx_sequence) {
        this.tx_sequence = tx_sequence;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecurePayEndpoint() {
        return securePayEndpoint;
    }

    public void setSecurePayEndpoint(String securePayEndpoint) {
        this.securePayEndpoint = securePayEndpoint;
    }

/*    public String getMagentoEndpoint() {
        return magentoEndpoint;
    }

    public void setMagentoEndpoint(String magentoEndpoint) {
        this.magentoEndpoint = magentoEndpoint;
    }*/
}

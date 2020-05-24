package com.weatherinfoservice.exceptions;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateExceptionHandler implements ResponseErrorHandler  {

	public static final Logger logger= LoggerFactory.getLogger(RestTemplateExceptionHandler.class);
	private static final String BAD_REQUEST_RESPONSE_MESSAGE= "Please Make Sure You Spell the Country/City Name Correctly";

	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus status = response.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		/*
		 * String result = new BufferedReader(new InputStreamReader(response.getBody()))
		 * .lines().collect(Collectors.joining("\n"));
		 */
		ObjectMapper errorResponseMapper= new ObjectMapper();
		Map<String, String> responseErrorMap=  errorResponseMapper.readValue(response.getBody(), Map.class);
		//System.out.println("responseErrorMap:: " +responseErrorMap);
		
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST || response.getStatusCode()==HttpStatus.NOT_FOUND ) {
			logger.error("ResponseBody: {}", BAD_REQUEST_RESPONSE_MESSAGE);
	        throw new BadServiceRequestException(BAD_REQUEST_RESPONSE_MESSAGE);
		}
		else
		{
			String responseMessage= responseErrorMap.get("message");
			logger.error("ResponseBody: {}", responseMessage);
	        throw new HttpServerErrorException(response.getStatusCode(),  responseMessage);
		}
    }
	
	

	
		
}



package com.weatherinfoservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

//@RestController
@ApiIgnore
public class ApplicationErrorController extends AbstractErrorController {

	private static final String ERROR_PATH=  "/error";
	
	public ApplicationErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(value=ERROR_PATH)
	public String handleError(HttpServletRequest request) {
	    String errorResponseMessage = null;
		Map<String, Object> responseErrorMap= super.getErrorAttributes(request, false);
		final Integer responseCode= (Integer) responseErrorMap.get("status");
		final String errorMessage= (String) responseErrorMap.get("error");
		final String errorPath= (String) responseErrorMap.get("path");
		
		if(responseCode==HttpStatus.INTERNAL_SERVER_ERROR.value() || responseCode==HttpStatus.BAD_GATEWAY.value()) {
			errorResponseMessage= "Internal Server Error Occured "+errorMessage;
		}
		else if(responseCode==HttpStatus.NOT_FOUND.value()) {
			errorResponseMessage= "Http Status 404 (Not Found) for uri path:: " +errorPath;	
		}
		else if (responseCode==HttpStatus.BAD_REQUEST.value()) {
			errorResponseMessage= errorMessage;	
		}
			                                          
		return errorResponseMessage;
		
		/*
		 * "<html><center><b>"+"<h3 " + "style=" + "color:Red;" + ">"
		 * +"uri path:: "+"<i>"+responseStatus.get("path")+"</i>"
		 * +" is not mapped || failed with response code:: " +"<i>"+response+ " ("
		 * +responseStatus.get("error")+ ")"+"</i>" +"</h3>"+"<br/>" +"<p " + "style=" +
		 * "color:DodgerBlue;" + ">"+
		 * "for service uri mapping info please access the uri path as :: "
		 * +"/rest-info-service/swagger-ui.html"+"</p>" +"</b></center></html>"
		 */	
		}
		
	

	  @Override
	  public String getErrorPath() {
	      return ERROR_PATH;
	  }
}

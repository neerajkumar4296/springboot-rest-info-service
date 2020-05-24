package com.weatherinfoservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.weatherinfoservice.delegate.ApplicationDelegate;
import com.weatherinfoservice.services.MathsOperationService;


@WebMvcTest(ApplicationsUtiltyController.class)
public class ApplicationsUtiltyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApplicationDelegate applicationDelegate;
	
	@MockBean
	private MathsOperationService mathsOperationService;
	
	private static final String  attribute="hobby";
	private static final Optional<String> empty_languageCode=Optional.empty();
	private static final Optional<String> french_languageCode=Optional.of("FR");
	private static final String  french_value_hobby="loisir";

	
	@Test
	public void test_getLocalisedContentBasedOnDefaultLanguage() throws Exception {
		
		when(this.applicationDelegate.getLocalisedResponse(attribute, empty_languageCode)).thenReturn(attribute);
		
		this.mockMvc.perform( MockMvcRequestBuilders.get("/utilities/locale/value")
				.param("attributename", attribute))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString(attribute.substring(0, 2))));
	}
	
	@Test
	public void test_getLocalisedContentBasedOnFrenchLanguage() throws Exception {
		
		when(this.applicationDelegate.getLocalisedResponse(attribute, french_languageCode)).thenReturn(french_value_hobby);
		
		this.mockMvc.perform( MockMvcRequestBuilders.get("/utilities/locale/value")
				.param("attributename", attribute)
				.param("languagecode", french_languageCode.get()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString(french_value_hobby.substring(0, 2))));
	}

}

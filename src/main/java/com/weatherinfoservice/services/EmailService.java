package com.weatherinfoservice.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.util.WeatherInfoUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	FreeMarkerConfigurationFactoryBean emailConfigBean;

	public void sendEmail(String toEmailAdresses, WeatherReport weatherReport) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			Template template = emailConfigBean.createConfiguration().getTemplate("email-weatherreport.ftl");
			String htmlTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(template, prepareWeatherReportBodyMap(weatherReport));

			// helper.setTo(toEmailAdresses);
			helper.setTo(InternetAddress.parse(toEmailAdresses));
			// helper.setTo(new String[]{"email1@test.com", "email2@test.com"});
			helper.setSubject("weather report || hometown || " + weatherReport.getReportDate());
			// text = text/html
			// helper.setText(getEmailBodyText(weatherReport), true);
			helper.setText(htmlTemplate, true);
			// hard coded a file path
			// FileSystemResource file = new FileSystemResource(new
			// File("path/android.png"));
			// helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
			javaMailSender.send(mimeMessage);
		} catch (MessagingException | IOException | TemplateException ex) {
			throw new RuntimeException("Error Sending Email: " +ex.getMessage());
		}
	}
	
	private Object prepareWeatherReportBodyMap(WeatherReport weatherReport) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
		
		Map<String, String> emailBodyMap= new HashMap<>();
		emailBodyMap.put("cityName", weatherReport.getCityName());
		emailBodyMap.put("countryCode", weatherReport.getSystemDetails().getCountry());
		emailBodyMap.put("weatherType", weatherReport.getWeather().stream().map(weather -> weather.getWeatherDesc()).findFirst().get());
		emailBodyMap.put("currentTemperature", weatherReport.getWeatherInfo().getTemperature() + "\u2103");
		emailBodyMap.put("maxmTemperature", weatherReport.getWeatherInfo().getMaxTemperature() + "\u2103");
		emailBodyMap.put("MinTemperature", weatherReport.getWeatherInfo().getMinTemperature() + "\u2103");
		emailBodyMap.put("actualFeelTemperature", weatherReport.getWeatherInfo().getActualFeel() + "\u2103");
		emailBodyMap.put("humdity", weatherReport.getWeatherInfo().getHumidity() + " grams/cubic meter");
		emailBodyMap.put("windSpeed", weatherReport.getWind().getWindSpeed() + " km/h");
		emailBodyMap.put("sunrise", weatherReport.getSystemDetails().getSunriseTime().format(timeFormatter));
		emailBodyMap.put("sunset", weatherReport.getSystemDetails().getSunsetTime().format(timeFormatter));
		return emailBodyMap;
	}

	private String getEmailBodyText(WeatherReport weatherReport) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return "<h4>greetings,</h4><br>" + "<h2 " + "style=" + "color:DodgerBlue;" + ">" + "Weather Report for:: "
				+ weatherReport.getCityName() + "(" + weatherReport.getSystemDetails().getCountry() + ")" + "</h2>"
				+ "<h3 " + "style=" + "color:Gray;" + ">" + "<br>weather type:: "
				+ weatherReport.getWeather().stream().map(weather -> weather.getWeatherDesc()).findFirst().get()
				+ "<br>current temperature:: " + weatherReport.getWeatherInfo().getTemperature() + "\u2103"
				+ "<br>maximum temperature:: " + weatherReport.getWeatherInfo().getMaxTemperature() + "\u2103"
				+ "<br>minimum temperature:: " + weatherReport.getWeatherInfo().getMinTemperature() + "\u2103"
				+ "<br>actual temperature feel:: " + weatherReport.getWeatherInfo().getActualFeel() + "\u2103"
				+ "<br>humidity:: " + weatherReport.getWeatherInfo().getHumidity() + " grams/cubic meter"
				+ "<br>visibility:: " + WeatherInfoUtil.getVisibilityFromReport(weatherReport.getVisibility())
				+ "<br>wind spped:: " + weatherReport.getWind().getWindSpeed() + " km/h" + "<br>sunrise:: "
				+ weatherReport.getSystemDetails().getSunriseTime().format(timeFormatter) + "<br>sunset:: "
				+ weatherReport.getSystemDetails().getSunsetTime().format(timeFormatter) + "</h3>"
				+ "<br><h4>Thanks for using weather service</h4>";
	}

}

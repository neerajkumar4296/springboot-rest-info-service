package com.weatherinfoservice.services;

import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.weatherinfoservice.model.WeatherReport;
import com.weatherinfoservice.util.WeatherInfoUtil;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String toEmailAdresses, WeatherReport weatherReport) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		// helper.setTo(toEmailAdresses);

		helper.setTo(InternetAddress.parse(toEmailAdresses));
		// helper.setTo(new String[]{"email1@test.com", "email2@test.com"});

		helper.setSubject("weather report || hometown || " + weatherReport.getReportDate());

		// text = text/html
		helper.setText(getEmailBodyText(weatherReport), true);

		// hard coded a file path
		// FileSystemResource file = new FileSystemResource(new
		// File("path/android.png"));

		// helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

		javaMailSender.send(msg);

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
				+ "<br>humidity:: " + weatherReport.getWeatherInfo().getHumidity()+ " grams/cubic meter" 
				+ "<br>visibility:: " +WeatherInfoUtil.getVisibilityFromReport(weatherReport.getVisibility())
				+ "<br>wind spped:: "+ weatherReport.getWind().getWindSpeed() + " km/h" 
				+ "<br>sunrise:: "+ weatherReport.getSystemDetails().getSunriseTime().format(timeFormatter)
				+ "<br>sunset:: "+ weatherReport.getSystemDetails().getSunsetTime().format(timeFormatter) + "</h3>"
				+ "<br><h4>Thanks for using weather service</h4>";
	}
	
	

}

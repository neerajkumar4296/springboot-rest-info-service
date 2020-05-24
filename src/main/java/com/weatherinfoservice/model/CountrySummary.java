package com.weatherinfoservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "countryName", "countryCode", "simpleName", "confirmed", "newlyConfirmed",  "deaths", "newDeaths", "recovered", "newlyRecovered", "updatedTime"  })
public class CountrySummary {

	
	    private String countryName;

	    private String countryCode;

	    private String simpleName;

	    private int newlyConfirmed;

	    private int confirmed;

	    private int newDeaths;

	    private int deaths;

	    private int newlyRecovered;

	    private int recovered;

	    private LocalDateTime updatedTime;

		@JsonProperty("countryName")
		public String getCountryName() {
			return countryName;
		}
		
        @JsonProperty("Country")
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
        @JsonProperty("countryCode")
		public String getCountryCode() {
			return countryCode;
		}
		@JsonProperty("CountryCode")
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		
		@JsonProperty("simpleName")
		public String getSimpleName() {
			return simpleName;
		}
		@JsonProperty("Slug")
		public void setSimpleName(String simpleName) {
			this.simpleName = simpleName;
		}
		@JsonProperty("newlyConfirmed")
		public int getNewConfirmed() {
			return newlyConfirmed;
		}
		@JsonProperty("NewConfirmed")
		public void setNewConfirmed(int newConfirmed) {
			this.newlyConfirmed = newConfirmed;
		}
		@JsonProperty("confirmed")
		public int getTotalConfirmed() {
			return confirmed;
		}
		@JsonProperty("TotalConfirmed")
		public void setTotalConfirmed(int totalConfirmed) {
			this.confirmed = totalConfirmed;
		}
		@JsonProperty("newDeaths")
		public int getNewDeaths() {
			return newDeaths;
		}
		@JsonProperty("NewDeaths")
		public void setNewDeaths(int newDeaths) {
			this.newDeaths = newDeaths;
		}
		@JsonProperty("deaths")
		public int getTotalDeaths() {
			return deaths;
		}
		@JsonProperty("TotalDeaths")
		public void setTotalDeaths(int totalDeaths) {
			this.deaths = totalDeaths;
		}
		@JsonProperty("newlyRecovered")
		public int getNewRecovered() {
			return newlyRecovered;
		}
		@JsonProperty("NewRecovered")
		public void setNewRecovered(int newRecovered) {
			this.newlyRecovered = newRecovered;
		}
		@JsonProperty("recovered")
		public int getTotalRecovered() {
			return recovered;
		}
		@JsonProperty("TotalRecovered")
		public void setTotalRecovered(int totalRecovered) {
			this.recovered = totalRecovered;
		}
		
		@JsonProperty("updatedTime")
		public LocalDateTime getUpdatedTime() {
			return updatedTime;
		}
		@JsonProperty("Date")
		public void setUpdatedTime(LocalDateTime updatedTime) {
			this.updatedTime = updatedTime;
		}

	  
		
}

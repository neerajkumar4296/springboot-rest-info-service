package com.weatherinfoservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "countryName", "countryCode", "simpleName", "confirmed", "recovered",  "active",  "deaths", "newlyConfirmed",  "newDeaths", "newlyRecovered", "updatedTime"  })
public class CountrySummary extends GlobalSummary {

	
	    private String countryName;

	    private String countryCode;

	    private String simpleName;

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
		
		
		@JsonProperty("updatedTime")
		public LocalDateTime getUpdatedTime() {
			return updatedTime;
		}
		@JsonProperty("Date")
		public void setUpdatedTime(LocalDateTime updatedTime) {
			this.updatedTime = updatedTime;
		}

	  
		
}

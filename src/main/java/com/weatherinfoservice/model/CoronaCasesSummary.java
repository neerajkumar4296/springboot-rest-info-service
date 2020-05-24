package com.weatherinfoservice.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "worldSummary", "countriesSummary", "lastUpdatedTime" })
public class CoronaCasesSummary {

	
	    private GlobalSummary worldSummary;

	    private List<CountrySummary> countriesSummary;

	    private LocalDateTime lastUpdatedTime;
	    
	    @JsonProperty("worldSummary")
		public GlobalSummary getGlobalSummary() {
			return worldSummary;
		}
		@JsonProperty("Global")
		public void setGlobalSummary(GlobalSummary globalSummary) {
			this.worldSummary = globalSummary;
		}
		@JsonProperty("countriesSummary")
		public List<CountrySummary> getCountrySummaries() {
			return countriesSummary;
		}
		@JsonProperty("Countries")
		public void setCountrySummaries(List<CountrySummary> countrySummaries) {
			this.countriesSummary = countrySummaries;
		}
		@JsonProperty("lastUpdatedTime")
		public LocalDateTime getLocalDateTime() {
			return lastUpdatedTime;
		}
		@JsonProperty("Date")
		public void setLocalDateTime(LocalDateTime localDateTime) {
			this.lastUpdatedTime = localDateTime;
		}

	   

}

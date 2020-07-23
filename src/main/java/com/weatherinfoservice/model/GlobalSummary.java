package com.weatherinfoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "confirmed", "recovered", "active", "deaths", "newlyConfirmed", "newDeaths",  "newlyRecovered", })
public class GlobalSummary {

	private int newlyConfirmed;

	private int confirmed;
	
	private int active;

	private int newDeaths;

	private int deaths;

	private int newlyRecovered;

	private int recovered;

	@JsonProperty("newlyConfirmed")
	public int getNewlyConfirmed() {
		return newlyConfirmed;
	}

	@JsonProperty("NewConfirmed")
	public void setNewlyConfirmed(int newlyConfirmed) {
		this.newlyConfirmed = newlyConfirmed;
	}

	@JsonProperty("confirmed")
	public int getTotalConfirmed() {
		return confirmed;
	}

	@JsonProperty("TotalConfirmed")
	public void setTotalConfirmed(int totalConfirmed) {
		this.confirmed = totalConfirmed;
	}

	public int getActive() {
		return this.getTotalConfirmed()-this.getTotalRecovered()-this.getTotalDeaths();
	}

	public void setActive(int active) {
		this.active = active;
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
	public int getNewlyRecovered() {
		return newlyRecovered;
	}

	@JsonProperty("NewRecovered")
	public void setNewlyRecovered(int newlyRecovered) {
		this.newlyRecovered = newlyRecovered;
	}

	@JsonProperty("recovered")
	public int getTotalRecovered() {
		return recovered;
	}

	@JsonProperty("TotalRecovered")
	public void setTotalRecovered(int totalRecovered) {
		this.recovered = totalRecovered;
	}

}

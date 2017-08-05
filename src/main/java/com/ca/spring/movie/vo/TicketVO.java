package com.ca.spring.movie.vo;

import java.util.Date;

public class TicketVO {
	private String movieCode;
	private String ticketClass;
	private String numberOfSeats;
	private Date date;
	private String time;
	public String getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	public String getTicketClass() {
		return ticketClass;
	}
	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketVO [movieCode=");
		builder.append(movieCode);
		builder.append(", ticketClass=");
		builder.append(ticketClass);
		builder.append(", numberOfSeats=");
		builder.append(numberOfSeats);
		builder.append(", date=");
		builder.append(date);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}

package com.ca.spring.movie.delegate;

import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.facade.MovieWorldTicketFacade;

public class MovieWorldTicketManager {

	private MovieWorldTicketFacade movieWorldTicketFacade;

	public MovieWorldTicketManager(MovieWorldTicketFacade movieWorldTicketFacade) {
		this.movieWorldTicketFacade = movieWorldTicketFacade;
	}

	public int reserveSeats(String movieCode, String ticketClass, String numberOfSeats, String date, String time)
			throws MovieWorldException {
		return movieWorldTicketFacade.reserveSeats(movieCode, ticketClass, numberOfSeats, date, time);
	}

}

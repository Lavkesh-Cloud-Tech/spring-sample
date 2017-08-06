package com.ca.spring.movie.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.facade.MovieWorldTicketFacade;

@Component
public class MovieWorldTicketManager {

	private MovieWorldTicketFacade movieWorldTicketFacade;

	@Autowired
	public MovieWorldTicketManager(MovieWorldTicketFacade movieWorldTicketFacade) {
		this.movieWorldTicketFacade = movieWorldTicketFacade;
	}

	public int reserveSeats(String movieCode, String ticketClass, String numberOfSeats, String date, String time)
			throws MovieWorldException {
		return movieWorldTicketFacade.reserveSeats(movieCode, ticketClass, numberOfSeats, date, time);
	}

}

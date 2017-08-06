package com.ca.spring.movie.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ca.spring.movie.bo.MovieWorldTicketBO;
import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

@Component
public class MovieWorldTicketFacade {

	private MovieWorldTicketBO movieWorldTicketBO;

	@Autowired
	public MovieWorldTicketFacade(MovieWorldTicketBO movieWorldTicketBO) {
		this.movieWorldTicketBO = movieWorldTicketBO;
	}

	public int reserveSeats(String movieCode, String ticketClass, String numberOfSeats, String date, String time)
			throws MovieWorldException {

		//Validate Number of seats
		numberOfSeats = numberOfSeats != null ? numberOfSeats.trim() : "";
		if (numberOfSeats.length() > 0 && Integer.valueOf(numberOfSeats).intValue() < 1) {
			throw new MovieWorldException("Number of seat should be greater than or equal to 1");
		}

		//Validate date
		Date movieShowDate = validateAndParseDate(date);

		TicketVO ticketVO = new TicketVO();
		ticketVO.setMovieCode(movieCode);
		ticketVO.setTicketClass(ticketClass);
		ticketVO.setNumberOfSeats(numberOfSeats);
		ticketVO.setDate(movieShowDate);
		ticketVO.setTime(time);

		return movieWorldTicketBO.reserveSeats(ticketVO);
	}

	private Date validateAndParseDate(String date) throws MovieWorldException {
		try {
			date = date != null ? date.trim() : "";
			if (date.length() > 0) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				return df.parse(date);
			}
			throw new MovieWorldException("Invalid date. Date must not be empty.");
		} catch (Exception e) {
			throw new MovieWorldException("Invalid date. It must be in dd-MM-yyyy format");
		}
	}

}

package com.ca.spring.movie.bo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ca.spring.movie.dao.MovieWorldTicketDAO;
import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

@Service
@Transactional(rollbackFor=Throwable.class)
public class MovieWorldTicketBO {
	
	private MovieWorldTicketDAO movieWorldTicketDAO;
	private Map<String, Integer> ticketPassValueMap;
	
	public MovieWorldTicketBO(){}
	
	@Autowired
	public MovieWorldTicketBO(MovieWorldTicketDAO movieWorldTicketDAO, @Value("#{ticketPassValueMap}") Map<String, Integer> ticketPassValueMap){
		this.movieWorldTicketDAO = movieWorldTicketDAO;
		this.ticketPassValueMap = ticketPassValueMap;
	}

	public int reserveSeats(TicketVO ticketVO) throws MovieWorldException {
		String movieCode = ticketVO.getMovieCode();
		String ticketClass = ticketVO.getTicketClass();
		String numberOfSeatsStr = ticketVO.getNumberOfSeats();
		Integer numberOfSeats = Integer.valueOf(numberOfSeatsStr);
		Integer ticketPrice = ticketPassValueMap.get(ticketClass);
		if(ticketPrice == null){
			String str = "Invalid ticket class selected for movie " + movieCode;
			throw new MovieWorldException(str);
		}
		
		movieWorldTicketDAO.saveReservationDetails(ticketVO);
		
		movieWorldTicketDAO.updateAvailableSeats(ticketVO);
		
		return numberOfSeats * ticketPrice;
	}

}

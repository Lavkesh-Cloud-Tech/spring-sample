package com.ca.spring.movie.dao;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

@Repository
public class MovieWorldTicketDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MovieWorldTicketDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private void checkMovieExistWhileBooking(String movieCode, String ticketClass, Date date, String time)
			throws MovieWorldException {
		// Check movie ticket class exist
		String findMovieTicketClassExist = "select count(*) from TICKET_AVAILABILITY ta join MOVIE_TIME mt on ta.MOVIE_TIME_ID = mt.MOVIE_TIME_ID and mt.MOVIE_CODE = ? and ta.TICKET_CLASS = ? and mt.SHOW_DATE = ? and mt.SHOW_TIME = ?";
		Object[] findMovieTicketClassExistArgs = new Object[] { movieCode, ticketClass, date, time };
		Integer movieTicketClassExist = this.jdbcTemplate.queryForObject(findMovieTicketClassExist, Integer.class,
				findMovieTicketClassExistArgs);
		if (movieTicketClassExist == 0) {
			String str = "Invalid movie time selected. Movie code: " + movieCode;
			throw new MovieWorldException(str);
		}
	}

	public void saveReservationDetails(final TicketVO ticketVO) throws MovieWorldException {
		String movieCode = ticketVO.getMovieCode();
		String ticketClass = ticketVO.getTicketClass();
		String numberOfSeatsStr = ticketVO.getNumberOfSeats();
		Integer requiredSeat = Integer.valueOf(numberOfSeatsStr);
		Date date = ticketVO.getDate();
		String time = ticketVO.getTime();

		// Check movie ticket class exist
		checkMovieExistWhileBooking(movieCode, ticketClass, date, time);

		// Get Ticket availability
		String ticketAvailableSql = "select ta.TICKET_AVAILABILITY_ID, ta.AVAILABLE_TICKETS from TICKET_AVAILABILITY ta join MOVIE_TIME mt on ta.MOVIE_TIME_ID = mt.MOVIE_TIME_ID and mt.MOVIE_CODE = ? and ta.TICKET_CLASS = ? and mt.SHOW_DATE = ? and mt.SHOW_TIME = ?";
		Object[] ticketAvailableSqlArgs = new Object[] { movieCode, ticketClass, date, time };
		Map<String, Object> ticketAvailability = this.jdbcTemplate.queryForMap(ticketAvailableSql,
				ticketAvailableSqlArgs);
		if (ticketAvailability == null || ticketAvailability.size() == 0) {
			String str = "There is no show of movie " + movieCode + " running at " + date + " " + time;
			throw new MovieWorldException(str);
		}

		Object[] ticketAvailabilityRowValues = ticketAvailability.values().toArray(new Object[0]);
		Integer ticketAvailabilityId = (Integer) ticketAvailabilityRowValues[0];
		Integer numberOfAvailableSeats = (Integer) ticketAvailabilityRowValues[1];
		if (numberOfAvailableSeats == 0 || numberOfAvailableSeats < requiredSeat) {
			String str = "Required number of seat not available either movie is full or available seat is less than required seat.";
			throw new MovieWorldException(str);
		}

		String saveReservationDetailSql = "insert into RESERVATION_DETAILS(TICKET_AVAILABILITY_ID, NUMBER_OF_SEATS) values (?, ?)";
		Object[] saveReservationDetailArgs = new Object[] { ticketAvailabilityId, requiredSeat };
		this.jdbcTemplate.update(saveReservationDetailSql, saveReservationDetailArgs);
	}

	public void updateAvailableSeats(final TicketVO ticketVO) throws MovieWorldException {
		String movieCode = ticketVO.getMovieCode();
		String ticketClass = ticketVO.getTicketClass();
		String numberOfSeatsStr = ticketVO.getNumberOfSeats();
		Integer requiredSeat = Integer.valueOf(numberOfSeatsStr);
		Date date = ticketVO.getDate();
		String time = ticketVO.getTime();

		// Check movie ticket class exist
		checkMovieExistWhileBooking(movieCode, ticketClass, date, time);

		String updateAvailableSeatsSql = "update TICKET_AVAILABILITY ta, (select ta1.TICKET_AVAILABILITY_ID as TICKET_AVAILABILITY_ID from TICKET_AVAILABILITY ta1 join MOVIE_TIME mt on ta1.MOVIE_TIME_ID = mt.MOVIE_TIME_ID and mt.MOVIE_CODE = ? and ta1.TICKET_CLASS = ? and mt.SHOW_DATE = ? and mt.SHOW_TIME = ?) as p set ta.AVAILABLE_TICKETS = (ta.AVAILABLE_TICKETS - ?) where ta.TICKET_AVAILABILITY_ID = p.TICKET_AVAILABILITY_ID and (ta.AVAILABLE_TICKETS - ?) >= 0";
		Object[] updateAvailableSeatsArgs = new Object[] { movieCode, ticketClass, date, time, requiredSeat,
				requiredSeat };
		int i = this.jdbcTemplate.update(updateAvailableSeatsSql, updateAvailableSeatsArgs);
		if (i == 0) {
			String str = "Required number of seat not available either movie is full or available seat is less than required seat for movie "
					+ movieCode + " running at " + date + " " + time;
			throw new MovieWorldException(str);
		}
	}

}

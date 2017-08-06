package com.ca.spring.movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ca.spring.movie.delegate.MovieWorldTicketManager;
import com.ca.spring.movie.exception.MovieWorldException;

public class App {
	public static void main(String[] args) throws MovieWorldException {
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

		MovieWorldTicketManager obj = (MovieWorldTicketManager) context.getBean("movieWorldTicketManager");
		obj.toString();

		String movieCode = "KHNH_2000";
		String ticketClass = "ECONOMY";
		String numberOfSeats = "1";
		String date = "2000-07-13";
		String time = "12:00";
		try {
			obj.reserveSeats(movieCode, ticketClass, numberOfSeats, date, time);
		} finally {
			((AnnotationConfigApplicationContext) context).close();
		}
	}
}

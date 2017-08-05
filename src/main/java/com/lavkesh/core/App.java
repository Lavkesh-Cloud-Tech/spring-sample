package com.lavkesh.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ca.spring.movie.delegate.MovieWorldTicketManager;
import com.ca.spring.movie.exception.MovieWorldException;

public class App {
	public static void main(String[] args) throws MovieWorldException {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");

		MovieWorldTicketManager obj = (MovieWorldTicketManager) context.getBean("movieWorldTicketManager");
		obj.toString();

		String movieCode = "KHNH_2000";
		String ticketClass = "ECONOMY";
		String numberOfSeats = "2";
		String date = "2000-07-13";
		String time = "21:00";
		try {
			obj.reserveSeats(movieCode, ticketClass, numberOfSeats, date, time);
		} finally {
			((ClassPathXmlApplicationContext) context).close();
		}
	}
}

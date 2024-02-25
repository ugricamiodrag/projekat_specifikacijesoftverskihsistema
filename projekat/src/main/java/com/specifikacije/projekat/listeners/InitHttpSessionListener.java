package com.specifikacije.projekat.listeners;


import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class InitHttpSessionListener implements HttpSessionListener{
	/** kod koji se izvrsava po kreiranju sesije */
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Inicijalizacija sesisje HttpSessionListener...");

		// pri kreiranju sesije inicijalizujemo je ili radimo neke dodatne aktivnosti	
		HttpSession session  = event.getSession();
		System.out.println("Session id korisnika je "+ session.getId());

	}
	
	/** kod koji se izvrsava po brisanju sesije */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Brisanje sesije HttpSessionListener...");
		
	}

}

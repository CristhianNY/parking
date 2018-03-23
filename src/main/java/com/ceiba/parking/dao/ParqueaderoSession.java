package com.ceiba.parking.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ParqueaderoSession {
	Session session;
	
	
	public ParqueaderoSession() {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory	sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	public Session getSession() {

		
		return session;
	}
}

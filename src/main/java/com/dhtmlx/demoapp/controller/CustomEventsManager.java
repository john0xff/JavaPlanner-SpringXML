package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;


import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;

import java.util.ArrayList;
import java.util.List;

public class CustomEventsManager extends DHXEventsManager {

	public CustomEventsManager(HttpServletRequest request) {
		super(request);
	}

	public Iterable<DHXEv> getEvents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<DHXEv> evs = new ArrayList<DHXEv>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			evs = session.createCriteria(Event.class).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		
    	return evs;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			if (status == DHXStatus.UPDATE)
				session.update(event);
			else if (status == DHXStatus.DELETE)
				session.delete(event);
			else if (status == DHXStatus.INSERT)
				session.save(event);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		return status;
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new Event();
	}
}

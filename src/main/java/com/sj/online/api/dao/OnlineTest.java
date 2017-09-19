package com.sj.online.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sj.online.api.model.OnlineQA;

@Repository
public class OnlineTest {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	// Avoid duplicate session creation
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@SuppressWarnings("unchecked")
	public List<OnlineQA> findAll() {
		return getSession().createCriteria(OnlineQA.class).list();
	}
}

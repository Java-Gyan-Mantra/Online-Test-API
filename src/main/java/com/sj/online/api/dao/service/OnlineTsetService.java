package com.sj.online.api.dao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.online.api.dao.OnlineTest;
import com.sj.online.api.model.OnlineQA;

@Service
@Transactional
public class OnlineTsetService {
	@Autowired
	private OnlineTest onlineTest;

	public List<OnlineQA> getALLQA() {
		return (List<OnlineQA>) onlineTest.findAll();
	}
}

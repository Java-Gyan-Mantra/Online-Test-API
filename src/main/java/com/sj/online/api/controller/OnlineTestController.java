package com.sj.online.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.online.api.dao.service.OnlineTsetService;
import com.sj.online.api.dto.QAForm;
import com.sj.online.api.model.ExamResponse;
import com.sj.online.api.model.OnlineQA;
import com.sj.online.api.util.PdfGeneratortUtil;

@Controller
@RequestMapping("/Java_Gyan_Mantra")
public class OnlineTestController {
	@Autowired
	private OnlineTsetService service;

	private static List<OnlineQA> dbResults = null;
	String userName = "";

	ExamResponse pdfResponse = null;
	@Autowired
	private PdfGeneratortUtil util;

	@RequestMapping("/start")
	public String startTest(Model model, @RequestParam("name") String name) {
		List<OnlineQA> questions = service.getALLQA();
		dbResults = questions;
		userName = name;
		model.addAttribute("questions", questions);
		return "questionSet";
	}

	@RequestMapping("/submitTest")
	public String submitTest(@ModelAttribute("form") QAForm form, Model model) {
		List<String> submitResult = new ArrayList<>();
		List<String> actualResult = new ArrayList<>();
		int correctCount = 0;
		int wrongCount = 0;
		String message = "";
		ExamResponse examResponse = new ExamResponse();
		submitResult.add(form.getQ1());
		submitResult.add(form.getQ2());
		submitResult.add(form.getQ3());
		submitResult.add(form.getQ4());
		submitResult.add(form.getQ5());
		for (OnlineQA answer : dbResults) {
			actualResult.add(answer.getValidAnswer());
		}
		correctCount = countScore(actualResult, submitResult);
		wrongCount = submitResult.size() - correctCount;
		message = (correctCount >= 3) ? message = "!!! Congrats You are Qualified the test"
				: "Sorry you are not Qualified please re-try again";

		examResponse.setCorrect(correctCount);
		examResponse.setWrong(wrongCount);
		examResponse.setName(userName);
		examResponse.setDate(new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date()));
		examResponse.setPercent(correctCount * (100 / actualResult.size()));
		examResponse.setCourse("Java_Gyan_Mantra Online Test");
		examResponse.setMessage(message);
		pdfResponse = examResponse;
		model.addAttribute("examResponse", examResponse);
		return "status";

	}

	@RequestMapping("/downloadPDF")
	public String downloadPDF(Model model) throws Exception {
		Map<Object, Object> data = new HashMap<>();
		data.put("pdfResponse", pdfResponse);
		model.addAttribute("pdfResponse", pdfResponse);
		util.createPdf("resultPDF", data);
		model.addAttribute("message", "PDF Downloaded successfully..");
		return "home";
	}

	private int countScore(List<String> list1, List<String> list2) {
		int totalScore = 0;
		totalScore = list1.stream().filter(list2::contains)
				.collect(Collectors.toList()).size();
		return totalScore;
	}
}

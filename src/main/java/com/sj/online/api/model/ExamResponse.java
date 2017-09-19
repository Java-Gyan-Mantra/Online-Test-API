package com.sj.online.api.model;

public class ExamResponse {
	private int correct;
	private int wrong;
	private double percent;
	private String course;
	private String name;
	private String date;
	private String message;

	public ExamResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExamResponse [correct=" + correct + ", wrong=" + wrong
				+ ", percent=" + percent + ", course=" + course + ", name="
				+ name + ", date=" + date + ", message=" + message + "]";
	}

}

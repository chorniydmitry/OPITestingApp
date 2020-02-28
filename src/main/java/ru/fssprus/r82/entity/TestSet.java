package ru.fssprus.r82.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tests_sets")
public class TestSet extends Model {
	@NotNull
	@Min(5)
	@Column(name = "questionsamount")
	private int questionsAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_id")
	private Test test;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "questionset_id")
	private QuestionSet questionset;

	public int getQuestionsAmount() {
		return questionsAmount;
	}

	public void setQuestionsAmount(int questionsAmount) {
		this.questionsAmount = questionsAmount;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public QuestionSet getQuestionSet() {
		return questionset;
	}

	public void setQuestionSet(QuestionSet questionSet) {
		this.questionset = questionSet;
	}

}

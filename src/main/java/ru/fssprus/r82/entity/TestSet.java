package ru.fssprus.r82.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import ru.fssprus.r82.utils.AppConstants;

@Entity
@Table(name = "tests_sets")
public class TestSet extends Model {

	@NotNull(message = AppConstants.VALID_TESTSET_NAME_NOTNULL)
	@Range(min = AppConstants.VALID_TESTSET_NAME_SIZE_MIN, message = AppConstants.VALID_TESTSET_NAME_SIZE)
	@Column(name = "questionsamount")
	private int questionsAmount;

	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	@ManyToOne
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

	@Override
	public String toString() {
		return "TestSet [questionsAmount=" + questionsAmount + ", test=" + test.getName() + ", questionset="
				+ questionset + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + questionsAmount;
		result = prime * result + ((questionset == null) ? 0 : questionset.hashCode());
		result = prime * result + ((test == null) ? 0 : test.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestSet other = (TestSet) obj;
		if (questionsAmount != other.questionsAmount)
			return false;
		if (questionset == null) {
			if (other.questionset != null)
				return false;
		} else if (!questionset.equals(other.questionset))
			return false;
		if (test == null) {
			if (other.test != null)
				return false;
		} else if (!test.equals(other.test))
			return false;
		return true;
	}
}

package ru.fssprus.r82.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

/**
 * @author Chernyj Dmitry
 *
 */
@Entity
@Table(name = "tests")
public class Test extends Model {
	@NotNull
	@Size(min=5)
	@Column(name = "name", length = 2048, unique = true, nullable = false, updatable = false)
	private String name;
	
	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TestSet> testSets = new HashSet<>();
	
	@NotNull
	@Range(min=300, max=7200)
	@Column(name = "testing_time")
	private int testTimeSec;
	
	@Column(name = "isactive")
	private boolean isActive;
	
	@NotNull
	@Range(min=20)
	@Column(name = "questionsamount")
	private int amountOfQuestions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TestSet> getTestSets() {
		return testSets;
	}

	public void setTestSets(Set<TestSet> testSets) {
		this.testSets = testSets;
	}

	public int getTestTimeSec() {
		return testTimeSec;
	}

	public void setTestTimeSec(int testTimeSec) {
		this.testTimeSec = testTimeSec;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getAmountOfQuestions() {
		return amountOfQuestions;
	}

	public void setAmountOfQuestions(int amountOfQuestions) {
		this.amountOfQuestions = amountOfQuestions;
	}
		
}

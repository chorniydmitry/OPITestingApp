package ru.fssprus.r82.entity;

import java.util.HashSet;
/**
 * @author Chernyj Dmitry
 *
 */
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questionset")
public class QuestionSet extends Model {
	
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy="questionset")
	private Set<Question> questionSets;

	@NotNull
	@Size(min=5)
	@Column(name = "name", length = 2048, unique = true, nullable = false, updatable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionset", fetch = FetchType.EAGER)
	private Set<Result> resultList;
	
	@OneToMany(mappedBy = "questionset", cascade = CascadeType.ALL)
	private Set<TestSet> testSets = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setQuestionSets(Set<Question> questionSets) {
		this.questionSets = questionSets;
	}

	@Override
	public String toString() {
		return "QuestionSet [name=" + name + ", testList=" + resultList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((resultList == null) ? 0 : resultList.hashCode());
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
		QuestionSet other = (QuestionSet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (resultList == null) {
			if (other.resultList != null)
				return false;
		} else if (!resultList.equals(other.resultList))
			return false;
		return true;
	}

	public Set<Question> getQuestionSets() {
		return questionSets;
	}
	
	
	
	

}

package ru.fssprus.r82.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Chernyj Dmitry
 *
 */
@Entity
@Table(name = "test")
public class Test extends Model {
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "test_set", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Specification> specs = new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private int questionsAmount;

	public Set<Specification> getSpecs() {
		return specs;
	}

	public void setSpecs(Set<Specification> specs) {
		this.specs = specs;
	}

	public int getQuestionsAmount() {
		return questionsAmount;
	}

	public void setQuestionsAmount(int questionsAmount) {
		this.questionsAmount = questionsAmount;
	}

	

}

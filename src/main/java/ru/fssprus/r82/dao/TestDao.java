package ru.fssprus.r82.dao;

import java.util.List;

import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Test;

public interface TestDao extends ItemDao<Test> {
	
	public int countAll();
	
	public List<Test> getByQuestionSet(QuestionSet questionSet);

	public Test getByName(String name);

	public int countByQuestionSet(QuestionSet questionSet);

	public List<Test> getAllByName(String name);

	public List<Test> getActive();

}

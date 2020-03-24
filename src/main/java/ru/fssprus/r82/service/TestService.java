package ru.fssprus.r82.service;

import java.util.List;

import ru.fssprus.r82.dao.TestDao;
import ru.fssprus.r82.dao.impl.TestDatabaseDao;
import ru.fssprus.r82.entity.Test;

public class TestService {
	private TestDao testDao = new TestDatabaseDao();
	
	public void add(Test test) {
		testDao.add(test);
	}
	
	public List<Test> getAll(int fromId, int toId) {
		return testDao.getAll(fromId, toId);
	}
	
	public List<Test> getAll() {
		return testDao.getAll();
	}
	
	public Test getById(Long id) {
		return testDao.getById(id);
	}
	
	public void delete(Test test) {
		testDao.remove(test);
	}
	
	public void delete(Long id) {
		testDao.remove(id);
	}
	
	public int countAll() {
		return testDao.countAll();
	}
	
	public void update(Test test) {
		testDao.update(test);
	}

	public Test getByName(String name) {
		return testDao.getByName(name);
	}

}

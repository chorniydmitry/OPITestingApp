package ru.fssprus.r82.dao;

import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;

public interface TestDao extends ItemDao<Test> {
	
	public int countAll();

}

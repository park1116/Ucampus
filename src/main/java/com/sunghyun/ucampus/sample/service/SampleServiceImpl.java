package com.sunghyun.ucampus.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunghyun.ucampus.sample.dao.SampleDao;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	@Autowired
	protected SampleDao sampleDao;
	
	@Override
	public String selectSampleData() throws Exception {
		String serviceTest = sampleDao.selectSampleData();
		// Business Logic
		serviceTest = "가공된(SampleServiceImpl을 거친) " + serviceTest;
		return serviceTest;
	}
}

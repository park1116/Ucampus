package com.sunghyun.ucampus.common.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunghyun.ucampus.common.service.SampleService;

@Controller
public class SampleController {
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping("/sample")
	public ModelAndView sampleView(ModelAndView mv) throws Exception {
		mv.addObject("dbResult", sampleService.selectSampleData());
		mv.setViewName("sample");
		return mv;
	}
}
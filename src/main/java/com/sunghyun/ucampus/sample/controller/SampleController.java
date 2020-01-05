package com.sunghyun.ucampus.sample.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunghyun.ucampus.sample.service.SampleService;

@Controller
public class SampleController {

	@Resource(name = "sampleService")
	private SampleService sampleService;

	@RequestMapping("/sample.bgn")
	public ModelAndView sampleView(ModelAndView mv) throws Exception {
		mv.addObject("sampleAttribute", sampleService.selectSampleData());
		mv.setViewName("sample");
		return mv;
	}
}
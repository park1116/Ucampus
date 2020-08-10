package com.sunghyun.ucampus.sample.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sunghyun.ucampus.sample.service.SampleService;

@Controller
public class SampleController {

	@Resource(name = "sampleService")
	private SampleService sampleService;

//	@RequestMapping("/sample.bgn")
//	public ModelAndView sampleView(ModelAndView mv) throws Exception {
//		mv.addObject("sampleAttribute", sampleService.selectSampleData());
//		mv.setViewName("userMonitoring");
//		return mv;
//	}

	@RequestMapping("/sample.bgn")
	public ModelAndView treeView(ModelAndView mav, HttpServletRequest request) throws Exception {
		File f = new File("D:\\schemaInfo");
		ArrayList<File> subFiles = new ArrayList<File>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (!f.exists()) {
			System.out.println("디렉토리가 존재하지 않습니다.");
		}

		findSubFiles(f, subFiles,1);
		int i = 0;
		for (File file : subFiles) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			int dirCnt = 0, fileCnt = 0;
			String name = file.getName();
			dirCnt = countFiles(file, fileCnt, dirCnt);
			if (file.isFile()) {
				System.out.println("파일 이름: " + name);
				System.out.println(dirCnt);
			} else if (file.isDirectory()) {
				System.out.println("디렉토리 이름: " + name);
				System.out.println(dirCnt);
			}
			resultMap.put("name", name);
			resultMap.put("dirCnt", dirCnt);
			resultMap.put("depth", depthList.get(i++).get("depth"));
			resultList.add(resultMap);
		}
		mav.addObject("resultList", resultList);
		mav.setViewName("sample");
		return mav;
	}

	List<Map<String, Object>> depthList = new ArrayList<Map<String,Object>>();
	
	public void findSubFiles(File parentFile, ArrayList<File> subFiles,int depth) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = parentFile.getName();
		map.put("name", name);
		map.put("depth", depth);
		depthList.add(map);
		depth++;
		if (parentFile.isFile()) {
			subFiles.add(parentFile);
		} else if (parentFile.isDirectory()) {
			subFiles.add(parentFile);
			File[] childFiles = parentFile.listFiles();
			for (File childFile : childFiles) {
				findSubFiles(childFile, subFiles,depth);
			}
		}
	}

	/**
	 * directory의 경우 하위 폴더의 갯수를 세고 xml의 경우 interface의 개수가 출력된다
	 */
	public int countFiles(File nowFile, int fileCnt, int dirCnt) throws ParserConfigurationException, SAXException, IOException{
		if (nowFile.isFile()) {
			// XML 문서 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			if(nowFile.getName().substring(nowFile.getName().length()-3, nowFile.getName().length()).equals("xml")) {
				Document document = documentBuilder.parse(nowFile.getCanonicalPath());
				
				// root 구하기
				Element root = document.getDocumentElement();
				
				NodeList childeren = root.getChildNodes(); // 자식 노드 목록 get
				for(int i = 0; i < childeren.getLength(); i++){
					Node node = childeren.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE){ // 해당 노드의 종류 판정(Element일 때)
						Element ele = (Element)node;
						String nodeName = ele.getNodeName();
						if(nodeName.equals("interface")) {
							dirCnt++;
						}
					}
				}
			}
		}
		else if (nowFile.isDirectory()) {
				File[] files = nowFile.listFiles();
				for (File dirFile : files) {
					if (dirFile.isDirectory())
						dirCnt++;
				}
			}
		return dirCnt;
	}
}
package com.myproject.service.xml;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.myproject.util.XmlUtil;

@Service
public class XmlService {
	
	
	/**
	 * 获取XML的节点
	 * @param fileName
	 * @return
	 */
	public Map<String, String> getXmlNodes(String fileName){
		return XmlUtil.readXml(fileName);
	}
}

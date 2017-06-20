package com.myproject.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.myproject.emun.ResultType;

/**
 * xml文件读写工具类
 * @author hutao
 *
 */
public class XmlUtil {
	
	
	private static SAXReader saxReader = new SAXReader();
	private static Document document = null;
	private static Element rootNode = null;
	private static int i = 0;
	private static XMLWriter xmlWriter = null;
	private static FileOutputStream out = null;  
	
	/**
	 * 读取指定名称的xml文件
	 * @param fileName
	 * @return
	 */
	public static Map<String, String> readXml(String fileName){
		File file = new File(PathUtil.getXmlConfigPath()+fileName);
		if(!file.isFile() || !file.exists()){
			return null;
		}
		try {
			document = saxReader.read(file);
			if(document!=null){
				rootNode = document.getRootElement();
				return getAllNodeList(rootNode);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取该节点下的所有子节点
	 * @param node
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> getAllNodeList(Element node){
		if(node==null){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(node.getName(), node.getText());
		Iterator<Element> it = node.elementIterator();
		while(it.hasNext()){
			Element e = it.next();
			map.putAll(getAllNodeList(e));
		}
		return map;
	}
	
	/**
	 * 获取指定节点的值
	 * @param nodeName
	 * @return
	 */
	public static String getNodeValue(String fileName,String nodeName){
		Map<String, String> map = readXml(fileName);
		if(map.containsKey(nodeName)){
			return map.get(nodeName);
		}
		return null;
	}
	
	/**
	 * 设置指定xml文件的指定节点的值(不管是父节点还是子节点)
	 * @param fileName
	 * @param nodeName
	 * @param value
	 * @return
	 */
	public static int setNodeValue(String fileName,String nodeName,String value){
		Map<String, String> map = readXml(fileName);
		if(map==null || map.size()==0){
			return ResultType.Fail.getValue();
		}
		if(map.containsKey(nodeName)){
			return setValue(rootNode,fileName,nodeName,value);
		}
		return ResultType.Fail.getValue();
	}
	
	/**
	 * 设置指定节点的值
	 * @param e
	 * @param fileName
	 * @param nodeName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static int setValue(Element element,String fileName,String nodeName,String value){
		if(element==null){
			return ResultType.Fail.getValue();
		}
		Iterator<Element> it = element.elementIterator();
		while(it.hasNext()){
			Element e = it.next();
			setValue(e, fileName, nodeName, value);
			if(e.getName().equals(nodeName)){
				e.setText(value);
				try {
					out = new FileOutputStream(PathUtil.getXmlConfigPath()+fileName);
					xmlWriter = new XMLWriter(out);
					xmlWriter.write(document);
					i++;
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					try {
						xmlWriter.close();
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if(i!=0){
			return ResultType.Success.getValue();
		}
		return ResultType.Fail.getValue();
	}
}

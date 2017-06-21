package com.myproject.service.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

@Service
public class I18nService {
	
	public ResourceBundle getBundle(String language){
		ResourceBundle bd = null;
		if("1".equals(language)){
			//1.中文
			bd = ResourceBundle.getBundle("i18n_zh_CN", Locale.SIMPLIFIED_CHINESE);
		}else{
			//0.英文
			bd = ResourceBundle.getBundle("i18n_en_US", Locale.US);
		}
		return bd;
	}
}

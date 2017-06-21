package myproject;

import java.util.Locale;
import java.util.ResourceBundle;


public class I18nTest {
	
	public static void main(String[] args) {
		Locale currrentLocale = new Locale("zh", "CN");
		ResourceBundle rb = ResourceBundle.getBundle("i18n_zh_CN", currrentLocale);
		System.out.println(rb.getString("I18N_USER"));
	}
}	

package com.base;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

public class MyEditor extends PropertyEditorSupport {
	public void setAsText(String text) throws IllegalArgumentException {
		if ((text == null) || (text.equals(""))) {
			text = "0";
		}
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Integer.valueOf(Integer.parseInt(text)));
		}
	}

	public String getAsText() {
		return getValue().toString();
	}
}

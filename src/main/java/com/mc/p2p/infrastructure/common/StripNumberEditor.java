package com.mc.p2p.infrastructure.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public class StripNumberEditor extends CustomNumberEditor {

    public StripNumberEditor(Class<? extends Number> numberClass) {
        super(numberClass, true);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setAsText(StringUtils.stripToEmpty(text));
    }
}

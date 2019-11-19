package com.mc.p2p.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @Auther: 谢星星
 * @Date: 2019/11/19 14:14
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

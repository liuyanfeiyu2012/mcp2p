package com.mc.p2p.infrastructure.common;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @auther: 谢星星
 * @Date: 2019/11/29 14:15
 * @Description:
 */
public class MillisDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text)) {
            super.setValue(null);
        } else {
            super.setValue(new Date(Long.valueOf(StringUtils.stripToEmpty(text))));
        }
    }
}
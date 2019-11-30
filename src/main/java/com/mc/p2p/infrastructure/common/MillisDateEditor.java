package com.mc.p2p.infrastructure.common;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
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
package com.mc.p2p.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public class SJacksonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SJacksonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final TypeFactory TYPE_FACTORY = TypeFactory.defaultInstance();

    static {
        MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.configure(Feature.STRICT_DUPLICATE_DETECTION, true);
        MAPPER.setSerializationInclusion(Include.NON_NULL);

        // 根据字段名序列化
        MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        MAPPER.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
    }

    public static JsonNode read(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return MAPPER.readTree(json);
        } catch (IOException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public static String compress(Object object) {
        if (null == object) {
            return null;
        }

        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public static <T> List<T> extractList(String jacksonStr, Class<T> elementClass) {
        return extractList(jacksonStr, ArrayList.class, elementClass);
    }

    public static <T, C extends List<T>> C extractList(String jacksonStr,
                                                       Class<C> listClass,
                                                       Class<T> elementClass) {
        if (StringUtils.isBlank(jacksonStr)
                || null == listClass
                || null == elementClass) {
            return null;
        }

        try {
            return MAPPER.readValue(jacksonStr,
                    TYPE_FACTORY.constructCollectionType(listClass, elementClass));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public static <K, V> Map<K, V> extractMap(String jacksonStr,
                                              Class<K> keyClass,
                                              Class<V> valueClass) {
        return extractMap(jacksonStr, HashMap.class, keyClass, valueClass);
    }

    public static <K, V, M extends Map<K, V>> M extractMap(String jacksonStr,
                                                           Class<M> mapClass,
                                                           Class<K> keyClass,
                                                           Class<V> valueClass) {
        if (StringUtils.isBlank(jacksonStr)
                || null == mapClass
                || null == keyClass
                || null == valueClass) {
            return null;
        }

        try {
            return MAPPER.readValue(jacksonStr,
                    TYPE_FACTORY.constructMapType(mapClass, keyClass, valueClass));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public static <T> T extractPojo(String jacksonStr, Class<T> objectClass) {
        if (StringUtils.isBlank(jacksonStr) || null == objectClass) {
            return null;
        }

        try {
            return MAPPER.readValue(jacksonStr, objectClass);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }


    static class TestPo {
        private List<MyPo> poList;

        public List<MyPo> getPoList() {
            return poList;
        }

        public void setPoList(List<MyPo> poList) {
            this.poList = poList;
        }
    }

    static class MyPo {
        private Map<String, String> a;

        public Map<String, String> getA() {
            return a;
        }

        public void setA(Map<String, String> a) {
            this.a = a;
        }
    }

}

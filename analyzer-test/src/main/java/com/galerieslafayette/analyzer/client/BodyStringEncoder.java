package com.galerieslafayette.analyzer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.jackson.JacksonEncoder;

import java.lang.reflect.Type;

public class BodyStringEncoder extends JacksonEncoder {

    public BodyStringEncoder(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (String.class.equals(bodyType)) {
            template.body((String) object);
        } else {
            super.encode(object, bodyType, template);
        }
    }
}

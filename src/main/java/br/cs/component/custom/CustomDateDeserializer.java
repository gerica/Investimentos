package br.cs.component.custom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class CustomDateDeserializer
extends JsonDeserializer<Date> {
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = (JsonNode)oc.readTree(jsonParser);
            String data = node.asText();
            return formatter.parse(data);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
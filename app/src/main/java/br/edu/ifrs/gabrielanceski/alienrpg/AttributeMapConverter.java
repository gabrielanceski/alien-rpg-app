package br.edu.ifrs.gabrielanceski.alienrpg;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;

public class AttributeMapConverter {
    private static final Type attributeMapType = new TypeToken<Map<Attribute, Integer>>() {}.getType();;

    @TypeConverter
    public static String fromAttributeMap(Map<Attribute, Integer> map) {
        return map == null ? "" : new Gson().toJson(map);
    }

    @TypeConverter
    public static Map<Attribute, Integer> stringToAttributeMap(String string) {
        return string == null ? new HashMap<>() : new Gson().fromJson(string, attributeMapType);
    }
}

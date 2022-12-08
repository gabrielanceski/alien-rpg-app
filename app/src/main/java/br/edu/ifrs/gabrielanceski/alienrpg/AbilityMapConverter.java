package br.edu.ifrs.gabrielanceski.alienrpg;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifrs.gabrielanceski.alienrpg.model.Ability;
import br.edu.ifrs.gabrielanceski.alienrpg.model.Attribute;

public class AbilityMapConverter {
    private static final Type abilityMapType = new TypeToken<Map<Ability, Integer>>() {}.getType();;

    @TypeConverter
    public static String fromAbilityMap(Map<Ability, Integer> map) {
        return map == null ? "" : new Gson().toJson(map);
    }

    @TypeConverter
    public static Map<Ability, Integer> stringToAbilityMap(String string) {
        return string == null ? new HashMap<>() : new Gson().fromJson(string, abilityMapType);
    }
}

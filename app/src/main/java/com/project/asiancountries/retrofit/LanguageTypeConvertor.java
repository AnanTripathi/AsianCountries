package com.project.asiancountries.retrofit;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.asiancountries.util.Language;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LanguageTypeConvertor {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Language> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Language>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Language> someObjects) {
        return gson.toJson(someObjects);
    }
}

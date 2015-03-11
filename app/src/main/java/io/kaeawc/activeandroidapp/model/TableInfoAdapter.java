package io.kaeawc.activeandroidapp.model;

import com.activeandroid.TableInfo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.kaeawc.activeandroidapp.json.GsonSingleton;

public class TableInfoAdapter implements JsonDeserializer<TableInfo> {

    @Override
    public TableInfo deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        return GsonSingleton.get().fromJson(je, TableInfo.class);
    }
}

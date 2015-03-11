package io.kaeawc.activeandroidapp.model;

import com.activeandroid.TableInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class ActiveAndroidModelGson {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(TableInfo.class, new TableInfoAdapter())
            .setDateFormat(DATE_FORMAT)
            .create();

    public static Gson get() {
        return GSON;
    }
}

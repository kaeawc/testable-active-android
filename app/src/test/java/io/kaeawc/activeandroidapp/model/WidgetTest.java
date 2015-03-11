package io.kaeawc.activeandroidapp.model;

import android.test.suitebuilder.annotation.SmallTest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

@SmallTest
public class WidgetTest extends ActiveAndroidModelSpec {

    private static final Gson GSON = ActiveAndroidModelGson.get();
    private static final int DEFAULT_AGE = 0;
    private static final long DEFAULT_POINTS = 0L;
    private static final String DUMMY_NAME = "Asdf Fdsa";
    private static final int DUMMY_AGE = 21;
    private static final long DUMMY_POINTS = 573729L;
    private static final long TWENTY_ONE_YEARS_AGO = (new Date().getTime()) - (1000L * 60L * 60L * 24L * 365L * 21L);
    private static final Date DUMMY_BORN = new Date(TWENTY_ONE_YEARS_AGO);

    @Test
    public void deserialize() {

        String expected = DUMMY_NAME;
        String serialized = String.format("{\"name\":\"%s\"}", expected);

        JsonParser jsonParser = new JsonParser();
        JsonObject json = (JsonObject) jsonParser.parse(serialized);

        Widget widget = GSON.fromJson(json, Widget.class);

        if (widget == null) {
            fail("Widget should not be null after being parsed from JSON");
        }

        assertEquals(expected, widget.getName());
    }

    @Test
    public void serializeDefaultConstructor() {

        Widget widget = new Widget();

        String json = GSON.toJson(widget);
        JsonParser jsonParser = new JsonParser();
        JsonObject actual = (JsonObject) jsonParser.parse(json);

        JsonObject expected = new JsonObject();
        expected.addProperty("age", DEFAULT_AGE);
        expected.addProperty("points", DEFAULT_POINTS);
        expected.add("m_table_info", new JsonObject());

        assertEquals(expected, actual);

        assertNull(actual.get("name"));
        assertEquals(DEFAULT_AGE, actual.get("age").getAsInt());
        assertEquals(DEFAULT_POINTS, actual.get("points").getAsLong());
        assertNull(actual.get("birth_date"));
    }

    @Test
    public void serializeWithName() {

        Widget widget = new Widget();
        widget.setName(DUMMY_NAME);

        String json = GSON.toJson(widget);
        JsonParser jsonParser = new JsonParser();
        JsonObject actual = (JsonObject) jsonParser.parse(json);

        JsonObject expected = new JsonObject();
        expected.addProperty("name", DUMMY_NAME);
        expected.addProperty("age", DEFAULT_AGE);
        expected.addProperty("points", DEFAULT_POINTS);
        expected.add("m_table_info", new JsonObject());

        assertEquals(expected, actual);
    }

    @Test
    public void serializeWithBirthDate() {

        Widget widget = new Widget();
        widget.setBirthDate(DUMMY_BORN);

        String json = GSON.toJson(widget);
        JsonParser jsonParser = new JsonParser();
        JsonObject actual = (JsonObject) jsonParser.parse(json);

        JsonObject expected = new JsonObject();
        expected.addProperty("age", DEFAULT_AGE);
        expected.addProperty("points", DEFAULT_POINTS);
        expected.addProperty("birth_date", ActiveAndroidModelGson.dateFormat.format(DUMMY_BORN));
        expected.add("m_table_info", new JsonObject());

        assertEquals(expected, actual);
    }

    @Test
    public void serializeWithPoints() {

        Widget widget = new Widget();
        widget.setPoints(DUMMY_POINTS);

        String json = GSON.toJson(widget);
        JsonParser jsonParser = new JsonParser();
        JsonObject actual = (JsonObject) jsonParser.parse(json);

        JsonObject expected = new JsonObject();
        expected.addProperty("age", DEFAULT_AGE);
        expected.addProperty("points", DUMMY_POINTS);
        expected.add("m_table_info", new JsonObject());

        assertEquals(expected, actual);
    }

    @Test
    public void serializeWithAge() {

        Widget widget = new Widget();
        widget.setAge(DUMMY_AGE);

        String json = GSON.toJson(widget);
        JsonParser jsonParser = new JsonParser();
        JsonObject actual = (JsonObject) jsonParser.parse(json);

        JsonObject expected = new JsonObject();
        expected.addProperty("age", DUMMY_AGE);
        expected.addProperty("points", DEFAULT_POINTS);
        expected.add("m_table_info", new JsonObject());

        assertEquals(expected, actual);
    }
}

package news.agoda.com.sample.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json Object serializer and deserializer utility. This class is using service layer
 */
public class GsonUtil {

    private static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.sssZ";

    private static Gson gson = null;

    /**
     * It's the main json object converter function, servicasdasde is using this converter
     *
     * @return
     */
    public static Gson getGson() {

        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat(dateFormat);
            builder.registerTypeAdapter(Date.class, new DateDeserializer());

            gson = builder.create();
        }

        return gson;
    }

    public static String toJson(Object data) {
        Gson gson = getGson();
        return gson.toJson(data);
        //return " ";
    }

    public static Object fromJson(String data, Object d) {
        Gson gson = getGson();
        return gson.fromJson(data, d.getClass());
    }

    public static <T> T fromJson(String data, Class<T> d) {
        Gson gson = getGson();
        return gson.fromJson(data, d);
    }

    public static Object getJsonValue(String data, String key) {
        Object value = null;
        Log.i("DEBUG", "Data String : " + data);
        try {
            JSONObject object = new JSONObject(data);

            if (object != null) {
                Log.i("DEBUG", "Object : " + object.toString());
                if (object.has(key))
                    value = object.get(key);
            }
        } catch (Exception ex) {
            Log.e("GsonUtil 1", ex.getMessage());
        }

        value = value == null ? "" : value;

        return value;
    }

    public static Object getJsonValue(JSONObject object, String key) {
        Object value = null;
        Log.d("DEBUG", "Data Object : " + object);
        try {

            if (object != null) {
                Log.i("DEBUG", "Object : " + object.toString());
                if (object.has(key))
                    value = object.get(key);
            }
        } catch (Exception ex) {
            Log.e("GsonUtil 2", ex.getMessage());
        }

        value = value == null ? "" : value;

        return value;
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {

            boolean parsed = false;
            Date date = null;

            // Sample date format: 2016-03-27T19:34:29.029+0600 (string), 1453108603000 (long)
            try {
                // String formatted date parsing
                if (json.getAsString().matches("^-?\\d+$")) {
                    long timeinMilliSeccond = Long.parseLong(json.getAsString());
                    date = new Date(timeinMilliSeccond);
                } else {
                    date = new SimpleDateFormat(dateFormat).parse(json.getAsString());
                }
                parsed = true;
            } catch (ParseException e) {
                Log.e("GsonUtil 3", e.getMessage());
            }

            if (parsed == false) {
                // Long formatted date parsing
                date = new Date(json.getAsJsonPrimitive().getAsLong());
            }

            return date;
        }
    }
}

package code.serie2.ex7.utils;

import com.google.gson.*;
import code.serie2.ex7.entities.GoogleTaskEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 24/11/2015.
 */
public class JsonGeneration {

    /**
     * Creates a JsonObject based on the data of the GoogleCalendarEvent
     * @param event GoogleCalendarEvent to convert
     * @return JsonObject of the GoogleCalenderEvent
     */
    public static JsonElement GoogleTaskEventToJsonElement(GoogleTaskEvent event) {
        JsonObject taskObj = new JsonObject();
        taskObj.add("kind", new JsonPrimitive(GoogleTaskEvent.kind));
        taskObj.addProperty("summary", event.summary);
        taskObj.addProperty("description", event.Description);
        taskObj.add("end", event.end);
        taskObj.add("end", event.end);
        taskObj.add("start", event.start);
        return taskObj;
    }

    /**
     * Converts String to JsonObject
     * @param string string to convert
     * @return JsonObject of the string
     */
    public static JsonObject StringToJsonObject(String string) {
        return new JsonParser().parse(string).getAsJsonObject();
    }

    /**
     * Converts String to JsonArray
     * @param string string to convert
     * @return JsonArray of the string
     */
    public static JsonArray StringToJsonArray(String string) {
        return new JsonParser().parse(string).getAsJsonArray();
    }

    /**
     * Converts the List of GoogleCalendarEvents to a List of JsonElements
     * @param events List of GoogleCalendarEvent
     * @return List of JsonElements
     */
    public static List<JsonElement> GoogleTaskEventsToJson(List<GoogleTaskEvent> events) {
        List<JsonElement> elements = new ArrayList<>();
        for (GoogleTaskEvent event : events)
            elements.add(GoogleTaskEventToJsonElement(event));
        return elements;
    }

}

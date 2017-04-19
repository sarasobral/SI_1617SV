package exercicio7.utils;

import com.google.gson.*;
import exercicio7.entities.GoogleCalendarEvent;

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
    public static JsonElement GoogleCalendarEventToJsonElement(GoogleCalendarEvent event)
    {
        JsonObject taskObj = new JsonObject();
        taskObj.add("kind", new JsonPrimitive(GoogleCalendarEvent.kind));
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
    public static List<JsonElement> GoogleCalendarEventsToJson(List<GoogleCalendarEvent> events)
    {
        List<JsonElement> elements = new ArrayList<>();
        for (GoogleCalendarEvent event : events)
            elements.add(GoogleCalendarEventToJsonElement(event));
        return elements;
    }

}

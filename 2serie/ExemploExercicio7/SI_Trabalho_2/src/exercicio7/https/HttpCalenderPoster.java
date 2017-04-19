package exercicio7.https;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exercicio7.app.WebApp;
import exercicio7.entities.GoogleCalendarEvent;
import exercicio7.utils.JsonGeneration;

public class HttpCalenderPoster {
    /**
     * Posts the GoogleCalendarEvents in the users GoogleCalendar
     * @param events GoogleCalendarEvents to post
     * @param id the users GoogleCalendar id
     * @throws IOException caused by failed Get and Post requests
     */
    public static void post(List<GoogleCalendarEvent> events,String id) throws IOException {

        final Map<String, String> urlProperties = new HashMap<>();
        urlProperties.put("Accept-Language", "en-US,en;q=0.5");
        urlProperties.put("Accept", "application/json");
        urlProperties.put("Content-Type", "application/json");
        urlProperties.put("Authorization", "Bearer " + WebApp.googleToken.getToken());

        String urlName = "https://www.googleapis.com/calendar/v3/calendars/"+id+"/events?access_token="+WebApp.googleToken.getToken();
        String response = HttpRequests.sendGet(urlName, null, null);
        JsonObject curEvents = JsonGeneration.StringToJsonObject(response);
        for ( JsonElement elem : JsonGeneration.GoogleCalendarEventsToJson(events)) {
            if(!validateEvent(elem,curEvents))continue;
            HttpRequests.sendPost(urlName, urlProperties, elem);
        }
    }

    /**
     * Checks if the current event was already added to the Calendar
     * @param event Event to check
     * @param curEvents Events in Calendar
     * @return true = Valid, false = Invalid
     */
    private static boolean validateEvent(JsonElement event,JsonObject curEvents)
    {
        for (JsonElement JObj: curEvents.get("items").getAsJsonArray())
        {
            if(((JsonObject)event).get("summary").getAsString().equals(((JsonObject)JObj).get("summary").getAsString()))return false;
        }
        return true;
    }
}

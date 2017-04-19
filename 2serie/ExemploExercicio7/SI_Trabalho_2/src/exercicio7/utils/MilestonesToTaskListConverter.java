package exercicio7.utils;

import exercicio7.entities.GitHubMilestones;
import exercicio7.entities.GoogleCalendarEvent;
import java.util.ArrayList;
import java.util.List;

public class MilestonesToTaskListConverter {
    /**
     * Converts the Array of GitHubMilestones to a List of GoogleCalendarEvents
     * @param milestones Array of Milestones
     * @return
     */
    public static List<GoogleCalendarEvent> convertList(GitHubMilestones[] milestones){

        List<GoogleCalendarEvent> eventList = new ArrayList<>();
        for (GitHubMilestones issue : milestones)
            eventList.add(new GoogleCalendarEvent(issue.title+" id: "+issue.id,DateTimeUtils.getDateTimeFromString(issue.due_on.getAsString())));
        return eventList;
    }
}

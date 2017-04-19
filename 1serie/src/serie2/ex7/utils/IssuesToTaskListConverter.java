package code.serie2.ex7.utils;

import code.serie2.ex7.entities.GitHubIssues;
import code.serie2.ex7.entities.GoogleTaskEvent;
import java.util.ArrayList;
import java.util.List;

public class IssuesToTaskListConverter {
    /**
     * Converts the Array of GitHubIssues to a List of GoogleCalendarEvents
     * @param issues Array of Issues
     * @return
     */
    public static List<GoogleTaskEvent> convertList(GitHubIssues[] issues){
        List<GoogleTaskEvent> eventList = new ArrayList<>();
        for (GitHubIssues issue : issues)
            eventList.add(new GoogleTaskEvent(issue.title+" id: "+issue.id,DateTimeUtils.getDateTimeFromString(issue.due_on.getAsString())));
        return eventList;
    }
}

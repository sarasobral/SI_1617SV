package code.serie2.ex7.entities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GitHubIssues {
    public final JsonElement url;
    public final JsonElement html_url;
    public final JsonElement labels_url;
    public final JsonElement id;
    public final JsonElement number;
    public final JsonElement title;
    public final JsonElement description;
    public final JsonElement creator;
    public final JsonElement open_issues;
    public final JsonElement closed_issues;
    public final JsonElement state;
    public final JsonElement created_at;
    public final JsonElement updated_at;
    public final JsonElement due_on;
    public final JsonElement closed_at;

    public GitHubIssues(JsonObject element) {
        this.url = element.get("url");
        this.id = element.get("id");
        this.html_url = element.get("html_url");
        this.labels_url = element.get("labels_url");
        this.number = element.get("number");
        this.title = element.get("title");
        this.description = element.get("description");
        this.creator = element.get("creator");
        this.open_issues = element.get("open_issues");
        this.closed_issues = element.get("closed_issues");
        this.state = element.get("state");
        this.created_at = element.get("created_at");
        this.updated_at = element.get("updated_at");
        this.due_on = element.get("due_on");
        this.closed_at = element.get("closed_at");
    }

    public String toString(){
        return "{ url = " + url
                + " html_url = " + html_url +",\n"
                + " labels_url = " + labels_url +",\n"
                + " id = " + id +",\n"
                + " number = " + number +",\n"
                + " title = " + title +",\n"
                + " description = " + description +",\n"
                + " creator = " + creator +",\n"
                + " open_issues = " + open_issues +",\n"
                + " closed_issues = " + closed_issues +",\n"
                + " state = " + state +",\n"
                + " created_at = " + created_at +",\n"
                + " updated_at = " + updated_at +",\n"
                + " due_on = " + due_on +",\n"
                + " closed_at = " + closed_at +",\n"
                + " }";
    }
}

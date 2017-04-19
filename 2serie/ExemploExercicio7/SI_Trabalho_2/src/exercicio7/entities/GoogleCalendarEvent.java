package exercicio7.entities;


import com.google.gson.JsonObject;
import org.joda.time.DateTime;

public class GoogleCalendarEvent {

	public static final String kind = "calendar#event";
	public final JsonObject end;
	public final JsonObject start;
	public final String summary;
	public final String Description = "Trabalho de SI";
	
	public GoogleCalendarEvent(String summary,DateTime date){
		this.summary = summary;
		this.end = new JsonObject();
		this.start = new JsonObject();
		end.addProperty("date",date.getYear()+"-"+date.getMonthOfYear()+"-"+(date.getDayOfMonth()));
		start.addProperty("date",date.getYear()+"-"+date.getMonthOfYear()+"-"+date.getDayOfMonth());
	}

	public String toString(){
		return "{ kind = " + kind +
				" , start = " + start +
				" , end = " + end +
				" }"; 
	}
}

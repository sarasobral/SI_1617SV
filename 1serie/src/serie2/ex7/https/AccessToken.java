package code.serie2.ex7.https;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import code.serie2.ex7.servlets.github.GithubData;
import code.serie2.ex7.servlets.google.GoogleData;
import code.serie2.ex7.utils.JsonGeneration;
import code.serie2.ex7.utils.HttpRequests;

/**
 * Created by Utilizador on 05/12/2016.
 */
public class AccessToken {
    private String token , site;

    public AccessToken(String value,String site){
        split(value);
        this.site=site;
        System.out.println(this.toString());
    }

    private void split(String value) {
        JsonParser parser = new JsonParser();
        JsonObject o = (JsonObject)parser.parse(value);
        token = o.get("access_token").getAsString();
    }

    /**
     * Obtains the google AccessToken
     * @param code cone used to get the accessToken
     * @return AccessToken
     * @throws IOException
     */
    public static AccessToken getGoogleTasksAccessToken(String code) throws IOException{

        String getConfig = HttpRequests.sendGet("https://accounts.google.com/.well-known/openid-configuration",null,null);
        String url = JsonGeneration.StringToJsonObject(getConfig).get("token_endpoint").getAsString().replace("\"","");
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("code", code);
        urlParameters.put("client_id", GoogleData.CLIENT_ID);
        urlParameters.put("client_secret",GoogleData.CLIENT_SECRET);
        urlParameters.put("redirect_uri", "http://localhost:8080/gtaskscallback");
        urlParameters.put("grant_type", "authorization_code");
        String getResponse = HttpRequests.sendPost(url,urlParameters);
        return new AccessToken(getResponse,"Google");
    }

    /**
     * Obtains the gitHub AccessToken
     * @param code cone used to get the accessToken
     * @return AccessToken
     * @throws IOException
     */
    public static AccessToken getGitHubAccessToken(String code) throws IOException{
        String url = "https://github.com/login/oauth/access_token";
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("client_id", GithubData.CLIENT_ID);
        urlParameters.put("client_secret", GithubData.CLIENT_SECRET);
        urlParameters.put("redirect_uri", "http://localhost:8080/githubcallback");
        urlParameters.put("code", code);
        String getResponse = HttpRequests.sendGet(url, null, urlParameters);
        return new AccessToken(getResponse , "Github");
    }

    public String getToken(){
        return token;
    }

    public String getSite(){
        return site;
    }

    public String toString(){
        return "Token's website : " + site + " => access_token = " + token;
    }
}


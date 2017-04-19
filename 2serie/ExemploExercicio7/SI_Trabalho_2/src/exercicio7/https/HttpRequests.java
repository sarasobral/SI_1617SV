package exercicio7.https;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonElement;

public class HttpRequests {

    private static Map<String, String> DEFAULT_GET_CONNECTION_PROPERTIES;
    private static Map<String, String> DEFAULT_POST_CONNECTION_PROPERTIES;

    static {
        DEFAULT_GET_CONNECTION_PROPERTIES = new HashMap<>();
        DEFAULT_GET_CONNECTION_PROPERTIES.put("Accept-Language", "en-US,en;q=0.5");
        DEFAULT_GET_CONNECTION_PROPERTIES.put("Accept", "application/json");

        DEFAULT_POST_CONNECTION_PROPERTIES = new HashMap<>();
        DEFAULT_POST_CONNECTION_PROPERTIES.putAll(DEFAULT_GET_CONNECTION_PROPERTIES);
        DEFAULT_POST_CONNECTION_PROPERTIES.put("Content-Type", "application/json");
    }

    /**
     * Sends a post request based on the specified url Properties and element
     * @param urlString Url to use in request
     * @param urlProperties Properties of the request
     * @param elem data to write
     * @throws IOException Bad Request
     */
    public static void sendPost(String urlString, Map<String,String> urlProperties, JsonElement elem) throws IOException{
        if (elem.isJsonNull())
            return;

        String content = elem.toString();
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        if (urlProperties != null)
            urlProperties.put("Content-Length", "" + content.length());
        addPostRequestProperties(connection, urlProperties);
        writeBodyContent(connection, content);
        printDebugMessages(connection);
    }

    /**
     * Sends a post request based on the specified url and parameters
     * @param urlString Url to use in request
     * @param urlParameters Parameters of the request
     * @return the response of the server to the post
     * @throws IOException Bad Request
     */
    public static String sendPost(String urlString, Map<String, String> urlParameters) throws IOException {
        String content = getParametersFromMap(urlParameters);
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        writeBodyContent(connection, content);
        printDebugMessages(connection);
        return retrieveGetResponse(connection);
    }

    /**
     * Sends a get request based o the specified url, properties and parameters
     * @param urlString Url to use in request
     * @param urlProperties Properties of the request
     * @param urlParameters Parameters of the request
     * @return the response of the server to the post
     * @throws IOException Bad Request
     */
    public static String sendGet(String urlString, Map<String, String> urlProperties, Map<String, String> urlParameters) throws IOException{
        URL obj = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

        addGetRequestProperties(connection, urlProperties);
        writeBodyContent(connection, getParametersFromMap(urlParameters));
        printDebugMessages(connection);

        return retrieveGetResponse(connection);
    }

    /**
     * Adds properties to the post request
     * @param connection connection to add the properties to
     * @param urlProperties properties to add
     */
    private static void addPostRequestProperties(HttpsURLConnection connection,	Map<String, String> urlProperties) {
        if (urlProperties == null)
            urlProperties = DEFAULT_POST_CONNECTION_PROPERTIES;

        addRequestProperties(connection, urlProperties);
    }

    /**
     * Adds properties to the get request
     * @param connection connection to add the properties to
     * @param urlProperties properties to add
     */
    private static void addGetRequestProperties(HttpsURLConnection connection,	Map<String, String> urlProperties) {
        if (urlProperties == null)
            urlProperties = DEFAULT_GET_CONNECTION_PROPERTIES;

        addRequestProperties(connection, urlProperties);
    }

    /**
     * Used with addGetRequestProperties and addPostRequestProperties
     * Adds the properties to the connection
     * @param connection connection to add the properties to
     * @param urlProperties properties to add
     */
    private static void addRequestProperties(HttpsURLConnection connection, Map<String, String> urlProperties) {
        for (String key : urlProperties.keySet())
            connection.setRequestProperty(key, urlProperties.get(key));
    }

    /**
     * Retrieves the response of the connection
     * @param connection connection used
     * @return String containing the response of the server
     * @throws IOException
     */
    private static String retrieveGetResponse(HttpsURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader ( connection.getInputStream() ) );
        String inputLine = in.readLine();
        StringBuffer response = new StringBuffer();

        while (inputLine != null) {
            response.append(inputLine);
            inputLine = in.readLine();
        }
        in.close();
        return response.toString();
    }

    /**
     * Writes the bodyContent to the connection
     * @param con connection to write the properties to
     * @param parametersString bodyContent
     * @throws IOException
     */
    private static void writeBodyContent(HttpsURLConnection con, String parametersString) throws IOException {
        con.setDoOutput(true);
        if (parametersString != null){
            OutputStream wr = con.getOutputStream();
            wr.write(parametersString.getBytes("UTF-8"));
            wr.flush();
            wr.close();
        }
    }

    /**
     * Converts a map with the Parameters into a String
     * @param urlParameters Parameters to convert
     * @return String containing the properties
     */
    private static String getParametersFromMap(Map<String, String> urlParameters) {
        if (urlParameters == null || urlParameters.size() == 0)
            return null;

        StringBuilder mapParameters = new StringBuilder();
        Iterator<String> parametersIterator = urlParameters.keySet().iterator();

        for(int i = 0 ; i < urlParameters.keySet().size() && parametersIterator.hasNext(); ++i){
            String key = parametersIterator.next();
            mapParameters.append(((i == 0)?"": "&")+key+"="+urlParameters.get(key));
        }

        return mapParameters.toString();
    }

    /**
     * Prints a Message indicating the url of the request and the request response code
     * @param connection connection used
     * @throws IOException
     */
    private static void printDebugMessages(HttpsURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        System.out.println("Sending " + connection.getRequestMethod() + " request to URL : " + connection.getURL().getHost()+connection.getURL().getFile());
        System.out.println("Response Code : " + responseCode);
    }
}

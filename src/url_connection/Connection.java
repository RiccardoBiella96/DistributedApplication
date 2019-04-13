package url_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Connection {
    private URL url;
    private HttpURLConnection connection;
    public Connection(String u)  {
        try {
            url=new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public void showContentPage() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        while (true) { String s = in.readLine();
            if (s == null) break; else
            System.out.println(s);
        }
        in.close();
    }

    public void showHeaderRequest() throws IOException {
        connection=(HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setReadTimeout(10000);

        connection.setRequestProperty( "User-agent", "Mozilla/5.0(iPad; U; CPU iPhone OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B314 Safari/531.21.10");

        connection.setRequestMethod("POST");

        System.out.println("---begin REQUEST properties ----");
        //get all headers
        Map<String, List<String>> map = connection.getRequestProperties();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
        }
        System.out.println("---end REQUEST properties ----");
        OutputStreamWriter wr = new OutputStreamWriter( connection.getOutputStream());
        wr.flush();
    }
    public void showHeaderResponse(){
        System.out.println("---begin header fields ----");
        //get all headers
        Map<String, List<String>> map2 = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map2.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
    }
    System.out.println("---end header fields ----");
    }
    public int countInternalResource() throws IOException {
        int count=0;
        int HttpResult = connection.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("in :"+ line);
                count+=line.split("src").length-1;
            }
            br.close();
        }
        return count;
    }
}


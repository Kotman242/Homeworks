import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=eOgFgm2Mx7ordc6663QAg6aFweypjdVLcLcbVthz");
            httpGet.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            Nasa nasa = mapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Nasa>() {
            });

            String[] s = nasa.getHdurl().split("/");
            String name = s[s.length - 1];

            try (BufferedInputStream in = new BufferedInputStream(
                    new URL(nasa.getUrl()).openStream());
                 BufferedOutputStream out = new BufferedOutputStream(
                         new FileOutputStream(name))) {
                out.write(in.readAllBytes());
            }
//            try(ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(nasa.getHdurl()).openStream());
//            FileOutputStream fileOutputStream = new FileOutputStream(name)){
//                fileOutputStream.getChannel().transferFrom(readableByteChannel,0,Long.MAX_VALUE);
//            }
        }
    }
}

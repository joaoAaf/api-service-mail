package estudo.servicemail.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class EmailService {

    public Response sendEmail(String from, String to, String subject, String text)
            throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"from\":{\"email\":\"" + from
                + "\",\"name\":\"Mailtrap Test\"},\"to\":[{\"email\":\"" + to + "\"}],\"subject\":\"" + subject
                + "\",\"text\":\"" + text + "\",\"category\":\"Integration Test\"}", mediaType);
        Request request = new Request.Builder()
                .url("https://send.api.mailtrap.io/api/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 150ee8920d34bf60b667a379d264854b")
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

}

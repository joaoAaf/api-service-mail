package estudo.servicemail.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import estudo.servicemail.dto.DataEmail;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
public class EmailService {

        @Value("${MAIL_TOKEN}")
        private String mailToken;

        public void sendEmail(String from, DataEmail email)
                        throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create("{\"from\":{\"email\":\"" + from
                                + "\",\"name\":\"" + email.name() + "\"},\"to\":[{\"email\":\"" + email.to()
                                + "\"}],\"subject\":\"" + email.subject() + "\",\"text\":\"" + email.text()
                                + "\",\"category\":\"" + email.category() + "\"}",
                                mediaType);
                Request request = new Request.Builder()
                                .url("https://send.api.mailtrap.io/api/send")
                                .method("POST", body)
                                .addHeader("Authorization", "Bearer " + mailToken)
                                .addHeader("Content-Type", "application/json")
                                .build();
                client.newCall(request).execute().close();
        }

}

package com.server.restservice.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() throws IOException {

        InputStream serviceAccount =
                //new FileInputStream("./serviceAccountKey.json");
                //new FileInputStream(new ClassPathResource("serviceAccountKey.json").getFile());
                getClass().getResourceAsStream("/serviceAccountKey.json");

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(options);

    }
}

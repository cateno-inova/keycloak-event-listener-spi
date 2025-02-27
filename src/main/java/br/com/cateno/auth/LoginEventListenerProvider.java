package br.com.cateno.auth;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class LoginEventListenerProvider implements EventListenerProvider {
    private static final String API_URL = System.getenv("INTRANET_API_URL") + "/keycloak/events";

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.LOGIN) {
            sendLoginEventToApi(event.getUserId());
        }
    }

    private void sendLoginEventToApi(String userId) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = "{\"event\": \"LOGIN\", \"userId\": \"" + userId + "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {}

    @Override
    public void onEvent(AdminEvent arg0, boolean arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEvent'");
    }
}

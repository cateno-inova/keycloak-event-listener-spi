package br.com.cateno.auth;


import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class LoginEventListenerProviderFactory implements EventListenerProviderFactory {
    public static final String PROVIDER_ID = "login-event-listener";

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new LoginEventListenerProvider();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory sessionFactory) {
    }

    @Override
    public void close() {
    }
}

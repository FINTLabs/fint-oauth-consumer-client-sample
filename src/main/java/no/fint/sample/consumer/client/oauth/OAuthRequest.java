package no.fint.sample.consumer.client.oauth;

import lombok.extern.slf4j.Slf4j;
import no.fint.kodeverk.Begrepsverdi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class OAuthRequest {
    @Value("${fint.sample.oauth.username}")
    private String username;

    @Value("${fint.sample.oauth.password}")
    private String password;

    @Value("${fint.sample.oauth.accessTokenUri}")
    private String accessTokenUri;

    @Value("${fint.sample.oauth.clientId}")
    private String clientId;

    @Value("${fint.sample.oauth.clientSecret}")
    private String clientSecret;

    @Value("${fint.sample.oauth.requestUrl}")
    private String requestUrl;

    @Value("${fint.sample.oauth.scope}")
    private String scope;

    private
    OAuth2RestTemplate restTemplate;

    @PostConstruct
    private void init() {

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList(new String(scope)));

        restTemplate = new OAuth2RestTemplate(resourceDetails);
    }

    public List<Begrepsverdi> request() {

        try {
            Begrepsverdi[] begrepsverdier = restTemplate.getForObject(requestUrl, Begrepsverdi[].class);
            return Arrays.asList(begrepsverdier);
        } catch (HttpClientErrorException e) {
            log.info("Message: {}", e.getMessage());
            log.info("Please try again ;)");
        }

        return Arrays.asList();
    }

}

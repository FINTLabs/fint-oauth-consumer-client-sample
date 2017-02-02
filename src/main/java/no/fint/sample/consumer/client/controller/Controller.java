package no.fint.sample.consumer.client.controller;

import no.fint.kodeverk.Begrepsverdi;
import no.fint.sample.consumer.client.oauth.OAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(value = "/test")
public class Controller {

    @Autowired
    OAuthRequest oAuthRequest;

    @RequestMapping(method = RequestMethod.GET)
    public List<Begrepsverdi> getCodelist() {
        return oAuthRequest.request();
    }
}

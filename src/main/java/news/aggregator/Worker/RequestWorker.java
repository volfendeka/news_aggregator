package news.aggregator.Worker;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RequestWorker implements Runnable{

    private final String uri;

    public RequestWorker(String uri){
        this.uri = uri;
    }

    public void run(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_ATOM_XML));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println(uri + ": " + result.getStatusCode());
    }

}

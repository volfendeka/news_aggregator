package news.aggregator.Worker;

import news.aggregator.Entity.Source;
import news.aggregator.Service.FeedParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


public class RequestWorker extends Worker implements Runnable{

    private String uri;
    private Source source;
    private FeedParser feedParser;


    public RequestWorker(Source source, FeedParser feedParser)
    {
        this.uri = source.getRssUri();
        this.source = source;
        this.feedParser = feedParser;
    }

    public void run(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_ATOM_XML));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(this.uri, HttpMethod.GET, entity, String.class);

        feedParser.parse(this.source, responseEntity);

        System.out.println(this.uri + ": " + responseEntity.getStatusCode());

    }

}

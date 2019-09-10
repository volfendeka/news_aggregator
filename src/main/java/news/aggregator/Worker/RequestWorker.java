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


    /**
     * @param source
     * @param feedParser
     */
    public RequestWorker(Source source, FeedParser feedParser)
    {
        this.uri = source.getRssUri();
        this.source = source;
        this.feedParser = feedParser;
    }

    /**
     * Runnable implementation. Ping active sources for available feeds
     */
    public void run(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_ATOM_XML));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(this.uri, HttpMethod.GET, entity, String.class);

        try {
            HttpStatus statusCode = responseEntity.getStatusCode();
            if(statusCode.is2xxSuccessful()){
                feedParser.parse(this.source, responseEntity);
            }else {
                throw new Exception("To many requests exception");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        System.out.println(this.uri + ": " + responseEntity.getStatusCode());
    }

}

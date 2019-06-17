package news.aggregator.Service;

import news.aggregator.Entity.Source;
import news.aggregator.Repository.SourceRepository;
import news.aggregator.Worker.RequestWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FeedRunner {

    @Autowired
    private SourceRepository sourceRepository;

    private static  final int THREADS = 10;

    public String init() {
        Iterable<Source> sources = sourceRepository.findAll();
        String rssUri = "http://feeds.bbci.co.uk/news/rss.xml";

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        System.out.println(sources.toString());
        for(Source source: sources){
            rssUri = source.getRssUri();
            System.out.println(rssUri);
            Runnable requestWorker = new RequestWorker(rssUri);

             executor.execute(requestWorker);
        }

        executor.shutdown();

        // Wait until all threads are finish
        while (!executor.isTerminated()) {
            System.out.println("working");
        }

        System.out.println("Executor finshed");

    return "exit runner";
    }
}

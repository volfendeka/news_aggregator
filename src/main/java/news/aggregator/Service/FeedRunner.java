package news.aggregator.Service;

import news.aggregator.Entity.Source;
import news.aggregator.Repository.SourceRepository;
import news.aggregator.Worker.RequestWorker;
import news.aggregator.Worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FeedRunner {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private Worker worker;

    private static  final int THREADS = 10;

    public String init() {
        Iterable<Source> sources = sourceRepository.findAll();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        System.out.println(sources.toString());
        String rssUri;
        for(Source source: sources){
            rssUri = source.getRssUri();
            System.out.println(rssUri);
            //RequestWorker worker = requestWorker.make(source);

            RequestWorker requestWorker = worker.make(source);
            executor.execute(requestWorker);
        }

        executor.shutdown();

        // Wait until all threads are finish
        while (!executor.isTerminated()) {
            //System.out.println("working");
        }

        System.out.println("Executor finshed");

    return "exit runner";
    }
}

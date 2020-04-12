package news.aggregator.Service;

import news.aggregator.Entity.Source;
import news.aggregator.Repository.SourceRepositoryCustom;
import news.aggregator.Worker.RequestWorker;
import news.aggregator.Worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Collections.EMPTY_LIST;

@Service
public class FeedRunner extends TimerTask {

    @Autowired
    private SourceRepositoryCustom sourceRepositoryCustom;
    @Autowired
    private Worker worker;

    /**
     * Service to run requests
     */
    private ExecutorService executorService;

    Logger logger = LoggerFactory.getLogger(FeedRunner.class);

    private static  final int THREADS = 5;

    /**
     * Run threads with source batches
     */
    private void processBatch (List<Source> sources, int counter) {
	    Collection batch = new ArrayList<>();
    	    for (Source source : sources) {
                System.out.println(source.getName());
                RequestWorker requestWorker = worker.make(source);
		batch.add(requestWorker);
                logger.debug("Make workers");
            }
	    List<Future> futures = this.executorService.invokeAll(batch);
	    System.out.println("Batch from " + counter + " executed!");
	    
	    return;
    }

    public List<Source> init() {
        try {
            List<Source> sources = sourceRepositoryCustom.getActiveSources();

            this.executorService = Executors.newFixedThreadPool(THREADS);

	    int counter = 0;
	    while(counter < sources.size()){
		    int nextInc = Math.min(sources.size() - counter, THREADS);                   
		    this.processBatch(sources.subList(counter, counter + nextInc), counter);
		    counter = counter + nextInc;
	    }            

            this.executorService.shutdown();

            System.out.println("Executor finished");

            return sources;

        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return (List<Source>) EMPTY_LIST;
        }
    }

    public List<Source> destroy() {
        try {
            List<Source> sources = sourceRepositoryCustom.getActiveSources();

            //todo stop parser workers;

            System.out.println("Workers destroyed");

            return sources;

        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return (List<Source>) EMPTY_LIST;
        }
    }

    public void run() {
        Date statDate = new Date();
        System.out.println("start time:" + statDate.toString());
        this.init();
        Date endDate = new Date();
        System.out.println("end time:" + endDate.toString());

    }
}

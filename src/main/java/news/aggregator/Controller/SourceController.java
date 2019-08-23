package news.aggregator.Controller;

import news.aggregator.Entity.Source;
import news.aggregator.Entity.SourceStatus;
import news.aggregator.Entity.SourceType;
import news.aggregator.Repository.SourceRepository;
import news.aggregator.Repository.SourceStatusRepository;
import news.aggregator.Repository.SourceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SourceController {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private SourceTypeRepository sourceTypeRepository;
    @Autowired
    private SourceStatusRepository sourceStatusRepository;

    @GetMapping("/source")
    public @ResponseBody Iterable<Source> getAllSources() {
        // This returns a JSON or XML with the users
        return sourceRepository.findAll();
    }

    @PostMapping("/source")
    public ResponseEntity<Object> createSource(@RequestBody Source source) {
        Source savedSource = sourceRepository.save(source);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSource.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/source/{id}")
    public ResponseEntity<Object> updateSource(@RequestBody Source source, @PathVariable int id) {

        Optional<Source> sourceOptional = sourceRepository.findById(id);

        if (!sourceOptional.isPresent())
            return ResponseEntity.notFound().build();

        source.setId(id);

        sourceRepository.save(source);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/source/type")
    public @ResponseBody Iterable<SourceType> getAllSourceTypes() {
        // This returns a JSON or XML with the users
        return sourceTypeRepository.findAll();
    }

    @GetMapping("/source/status")
    public @ResponseBody Iterable<SourceStatus> getAllSourceStatuses() {
        // This returns a JSON or XML with the users
        return sourceStatusRepository.findAll();
    }
}
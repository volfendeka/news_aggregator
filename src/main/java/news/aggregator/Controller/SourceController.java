package news.aggregator.Controller;

import news.aggregator.Entity.Country;
import news.aggregator.Entity.Source;
import news.aggregator.Entity.SourceStatus;
import news.aggregator.Entity.SourceType;
import news.aggregator.Repository.*;
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
    private SourceRepositoryCustom sourceRepositoryCustom;
    @Autowired
    private SourceTypeRepository sourceTypeRepository;
    @Autowired
    private SourceStatusRepository sourceStatusRepository;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/source")
    public @ResponseBody Iterable<Source> getAllSources() {
        // This returns a JSON or XML with the users
        return sourceRepository.findAll();
    }

    @PostMapping("/source")
    public ResponseEntity<Object> createSource(@RequestBody Source source) {

        this.prepareSource(source);

        Source savedSource = sourceRepository.save(source);

        return ResponseEntity.ok().body(savedSource);

    }

    @PutMapping("/source/{id}")
    public ResponseEntity<Object> updateSource(@RequestBody Source source, @PathVariable int id) {

        Optional<Source> sourceOptional = sourceRepository.findById(id);

        if (!sourceOptional.isPresent())
            return ResponseEntity.notFound().build();

        source.setId(id);

        this.prepareSource(source);

        Source updatedSource = sourceRepository.save(source);

        return ResponseEntity.ok().body(updatedSource);
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

    /**
     * Find relations and set as entities for Source
     * @param source
     */
    private void prepareSource(Source source) {
        Optional<Country> countryOptional = countryRepository.findById(source.getCountry().getId());
        Optional<SourceType> sourceTypeOptional = sourceTypeRepository.findById(source.getSourceType().getId());
        Optional<SourceStatus> sourceStatusOptional = sourceStatusRepository.findById(source.getSourceStatus().getId());

        source.setCountry(countryOptional.orElse(source.getCountry()));
        source.setSourceType(sourceTypeOptional.orElse(source.getSourceType()));
        source.setSourceStatus(sourceStatusOptional.orElse(source.getSourceStatus()));
    }
}
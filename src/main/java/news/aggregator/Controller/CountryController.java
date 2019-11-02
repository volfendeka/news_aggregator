package news.aggregator.Controller;

import news.aggregator.Entity.Country;
import news.aggregator.Entity.User;
import news.aggregator.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/country")
    public @ResponseBody
    Iterable<Country> getAllCountries() {
        // This returns a JSON or XML with the countries
        return countryRepository.findAll();
    }

    @GetMapping("/countries_with_sources")
    public @ResponseBody
    Iterable<Country> getCountriesWithSources() {
        // This returns a JSON or XML with the countries
        return countryRepository.findAll();
    }

}
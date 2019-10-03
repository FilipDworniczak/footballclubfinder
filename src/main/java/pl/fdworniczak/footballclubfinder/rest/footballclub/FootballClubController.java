package pl.fdworniczak.footballclubfinder.rest.footballclub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fdworniczak.footballclubfinder.footballclub.FootballClubService;

/**
 * Created by filip on 03.10.19
 */

@RestController()
@RequestMapping("/api/football-club")
public class FootballClubController {

    @Autowired
    private FootballClubService footballClubService;

    @GetMapping("/{name}")
    public ResponseEntity<String> getFootballClubWikiUrl(@PathVariable final String name) {
        return new ResponseEntity<>(footballClubService.getFootballClubWikiUrl(name), HttpStatus.OK);
    }
}

package pl.fdworniczak.footballclubfinder.footballclub;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fdworniczak.footballclubfinder.exception.FootballClubFinderException;

import java.util.Optional;

/**
 * Created by filip on 03.10.19
 */
@Service
public class FootballClubService {

    private FootballClubExternalService footballClubExternalService;

    @Autowired
    public FootballClubService(final FootballClubExternalService footballClubExternalService) {
        this.footballClubExternalService = footballClubExternalService;
    }

    public String getFootballClubWikiUrl(final String name) {
        JsonNode article = footballClubExternalService.getWikiFootballArticle(name).orElseThrow(FootballClubFinderException::new);
        String articleTitle = Optional.ofNullable(article.path("title").asText()).orElseThrow(FootballClubFinderException::new);
        return "http://en.wikipedia.org/wiki/" + articleTitle.replace(" ", "_");
    }
}

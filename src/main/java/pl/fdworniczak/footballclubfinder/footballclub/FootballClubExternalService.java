package pl.fdworniczak.footballclubfinder.footballclub;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.fdworniczak.footballclubfinder.exception.FootballClubFinderException;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by filip on 03.10.19
 */
@Service
public class FootballClubExternalService {
    private RestTemplate restTemplate = new RestTemplate();

    public Optional<JsonNode> getWikiFootballArticle(final String name) {
        ResponseEntity<JsonNode> response = restTemplate.getForEntity("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + name + "&format=json&srprop=sectiontitle&srlimit=10", JsonNode.class);
        JsonNode responseBody = Optional.ofNullable(response.getBody()).orElseThrow(FootballClubFinderException::new);
        JsonNode articles = responseBody.path("query").path("search");
        if (articles.isMissingNode() || !articles.isArray()) {
            throw new FootballClubFinderException();
        }
        return StreamSupport.stream(articles.spliterator(), false).filter(this::belongsToCategoryContaining).findFirst();
    }

    private boolean belongsToCategoryContaining(JsonNode article) {
        ResponseEntity<JsonNode> response = restTemplate.getForEntity("https://en.wikipedia.org/w/api.php?action=query&prop=categories&titles=" + article.get("title").asText().replace(" ", "_") + "&format=json&cllimit=20", JsonNode.class);
        JsonNode responseBody = Optional.ofNullable(response.getBody()).orElseThrow(FootballClubFinderException::new);
        JsonNode categories = responseBody.path("query").path("pages").path(article.get("pageid").asText()).path("categories");
        if (categories.isMissingNode() || !categories.isArray()) {
            throw new FootballClubFinderException();
        }
        return StreamSupport.stream(categories.spliterator(), false).anyMatch(category -> category.get("title").asText().toLowerCase().contains("football"));
    }
}

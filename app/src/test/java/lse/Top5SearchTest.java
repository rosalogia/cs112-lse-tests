package lse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Top5SearchTest {
    private static class SearchTerms {
        public String term1;
        public String term2;
        public ArrayList<Integer> results;

        public SearchTerms(String s1, String s2, Integer[] results) {
            this.term1 = s1;
            this.term2 = s2;
            this.results = new ArrayList<>(Arrays.asList(results));
        }
    }

    private static class SearchTermsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext ctx) {
            return Stream.of(
                    Arguments.of(new SearchTerms("strange", "case",
                            new Integer[] {}
                    )),
                    Arguments.of(new SearchTerms("color", "strange",
                            new Integer[] {5, 3, 7, 2, 4}
                    )),
                    Arguments.of(new SearchTerms("orange", "weird",
                            new Integer[] {5, 6, 2, 8, 7}
                    )),
                    Arguments.of(new SearchTerms("red", "orange",
                            new Integer[] {5, 3, 2, 4, 7}
                    )),
                    Arguments.of(new SearchTerms("red", "car",
                            new Integer[] {1, 3, 2, 7, 4}
                    ))
            );
        }
    }
    @ParameterizedTest
    @ArgumentsSource(SearchTermsProvider.class)
    void searchTest(SearchTerms terms) throws FileNotFoundException {
        var testingLSE = new LittleSearchEngine();
        var path = "./src/test/resources/top5search/";
        testingLSE.makeIndex(path + "docs.txt", "./src/test/resources/noisewords.txt");
        var results = testingLSE.top5search(terms.term1, terms.term2);
        var ans = terms.results.stream().map(i -> path + "doc" + i + ".txt").collect(Collectors.toList());

        Assertions.assertThat(results).isEqualTo(ans);
    }
}

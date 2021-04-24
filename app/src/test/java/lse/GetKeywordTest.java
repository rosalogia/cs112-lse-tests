package lse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class GetKeywordTest {
    @ParameterizedTest
    @ValueSource(strings = {"sWord", "paraphrase", "really?!?!", "Between,", "either:or"})
    void getKeyword(String word) throws FileNotFoundException {
        String ans = null;
        switch (word) {
            case "sWord":
                ans = "sword";
                break;
            case "paraphrase":
                ans = "paraphrase";
                break;
            case "really?!?!":
                ans = "really";
                break;
            default:
                break;
        }

        var testingLSE = new LittleSearchEngine();
        Scanner sc = new Scanner(new File("./src/test/resources/noisewords.txt"));
        while (sc.hasNext()) {
            String w = sc.next();
            testingLSE.noiseWords.add(w);
        }

        Assertions.assertThat(testingLSE.getKeyword(word)).isEqualTo(ans);
    }
}

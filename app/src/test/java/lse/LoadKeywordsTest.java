package lse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadKeywordsTest {
    @ParameterizedTest
    @ValueSource(strings = {"pohlx", "Tyger", "jude"})
    void getKeyword(String filename) throws FileNotFoundException {
        var testingLSE = new LittleSearchEngine();
        var path = "./src/test/resources/loadKeywords/";
        var kwhm = testingLSE.loadKeywordsFromDocument( path + filename + ".txt");

        Scanner sc = new Scanner(new File(path + filename + ".out.txt"));
        while (sc.hasNextLine()) {
            var l = sc.nextLine().split(" ");
            var f = Integer.parseInt(l[1]);
            Assertions.assertThat(kwhm.containsKey(l[0]) && kwhm.get(l[0]).frequency == f).isTrue();
        }
    }
}

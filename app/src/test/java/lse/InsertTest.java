package lse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertTest {
    private static class FrequencyList {
        public Integer[] frequencies;
        public ArrayList<Integer> ans;

        public FrequencyList(Integer[] f, Integer[] a) {
            this.frequencies = f;
            this.ans = new ArrayList<>(Arrays.asList(a));
        }
    }
    private static class IntArrayArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext ctx) {
            return Stream.of(
                    Arguments.of(new FrequencyList(
                            new Integer[] {82, 76, 71, 71, 70, 65, 61, 56, 54, 51, 48, 45, 41, 36, 34, 30, 25, 20, 20, 18, 17, 17, 14, 12, 85},
                            new Integer[] {11, 5, 2, 0}
                    )),
                    Arguments.of(new FrequencyList(
                            new Integer[] {82,76,71,71,70,65,61,56,54,51,48,45,41,36,34,30,25,20,20,18,17,17,14,12,4},
                            new Integer[] {11, 17, 20, 22, 23}
                    )),
                    Arguments.of(new FrequencyList(
                            new Integer[] {82,76,71,71,70,65,61,56,54,51,48,45,41,36,34,30,25,20,20,18,17,17,14,12,50},
                            new Integer[] {11,5,8,9,10}
                    )),
                    Arguments.of(new FrequencyList(
                            new Integer[] {82,76,71,71,70,65,61,56,54,51,48,45,41,36,34,30,25,20,20,18,17,17,14,12,26},
                            new Integer[] {11,17,14,15,16}
                    )),
                    Arguments.of(new FrequencyList(
                            new Integer[] {82,76,71,71,70,65,61,56,54,51,48,45,41,36,34,30,25,20,20,18,17,17,14,12,17},
                            new Integer[] {11,17,20}
                    ))
            );
        }
    }
    @ParameterizedTest
    @ArgumentsSource(IntArrayArgsProvider.class)
    void insertionTest(FrequencyList fl) {
        ArrayList<Occurrence> occurrences =
                (ArrayList<Occurrence>) Arrays
                        .stream(fl.frequencies)
                        .map(freq -> new Occurrence(null, freq))
                        .collect(Collectors.toList());

        var testingLSE = new LittleSearchEngine();
        var results = testingLSE.insertLastOccurrence(occurrences);
        Assertions.assertThat(results).isEqualTo(fl.ans);
    }
}

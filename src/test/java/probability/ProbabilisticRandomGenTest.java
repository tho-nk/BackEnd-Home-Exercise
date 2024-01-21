package probability;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;


class ProbabilisticRandomGenTest {

	@Test
	void testNextFromSample() {
		List<ProbabilisticRandomGen.NumAndProbability> numAndProbabilityList = Arrays.asList(
                new ProbabilisticRandomGen.NumAndProbability(1, 0.2f),
                new ProbabilisticRandomGen.NumAndProbability(2, 0.5f),
                new ProbabilisticRandomGen.NumAndProbability(3, 0.3f)
        );
		
		ProbabilisticRandomGen sut = new ProbabilisticRandomGenImpl(numAndProbabilityList);
		
        for (int i = 0; i < 1000; i++) {
            int result = sut.nextFromSample();
            assertTrue(result >= 1 && result <= 3, "Result should be within the valid range");
        }
	}
	
    @Test
    void testErroProbabiltyData() {
        // Create a scenario with probabilities that do not sum exactly to 1.0
        List<ProbabilisticRandomGen.NumAndProbability> numAndProbabilityList = Arrays.asList(
                new ProbabilisticRandomGen.NumAndProbability(1, 0.2f),
                new ProbabilisticRandomGen.NumAndProbability(2, 0.5f),
                new ProbabilisticRandomGen.NumAndProbability(3, 0.25f) // Incorrect probability
        );

        ProbabilisticRandomGen sut = new ProbabilisticRandomGenImpl(numAndProbabilityList);

        for (int i = 0; i < 1000; i++) {
            int result = sut.nextFromSample();
            assertTrue(result >= 1 && result <= 3, "Result should be within the valid range");
        }
    }

}
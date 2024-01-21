/**
 * 
 */
package probability;

import java.util.List;
import java.util.Random;

/**
 * 
 */
public class ProbabilisticRandomGenImpl implements ProbabilisticRandomGen {

	private List<NumAndProbability> numAndProbabilityList_ = null;

	private Random random_;

	public ProbabilisticRandomGenImpl(List<NumAndProbability> numAndProbabilityList) {
		this.numAndProbabilityList_ = numAndProbabilityList;
		this.random_= new Random();
	}
	
	@Override
	public int nextFromSample() {
		float randomNumber = random_.nextFloat();
		return computeSample(randomNumber);

	}
	
	private int computeSample(float probability) {
		float cumulProba = 0.f;
		
		for (NumAndProbability numberAndProba : numAndProbabilityList_) {
			cumulProba += numberAndProba.getProbabilityOfSample();
			if(probability <= cumulProba) {
				return numberAndProba.getNumber();
			}
		}
		// in case of broken probability data
		return numAndProbabilityList_.get(numAndProbabilityList_.size() - 1).getNumber();
	}

}

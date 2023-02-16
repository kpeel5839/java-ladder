package domain.ladder.strategy;

import util.RandomNumberGenerator;

public class RandomGenerateBridgeStrategy implements GenerateBridgeStrategy {

    @Override
    public boolean isGeneratedBridge() {
        return 8 <= RandomNumberGenerator.pickRandomNumberInRange(1, 10);
    }
}
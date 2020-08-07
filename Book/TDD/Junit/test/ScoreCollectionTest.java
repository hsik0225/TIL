import com.hyunsiks.ScoreCollection;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCollectionTest {

    @Test
    public void answerArithmeticMeanOfTwoNumbers() {

        // 준비
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // 실행
        int actualResult = collection.arithmeticMean();

        // 단언(assert)
        assertThat(actualResult).isEqualTo(6);
    }
}

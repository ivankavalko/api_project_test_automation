import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class MyCalculatorTest {

    @DataProvider (name  = "paramTest1")
    public Object [][] calcAddTest(){
        return new Object[][] {
                {15, 20, 35},
                {1, 11, 12},
                {0, -4, -4}
        };

    }

    @Test(dataProvider = "paramTest1")
    public void calcAddTest (int firstValue, int secondValue, int expectedResult) {

        //Act
        int actualResult = MyCalculator.add(firstValue, secondValue);

        //Assert
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider (name = "paramTest2")
    public Object [][] calcMyltiplyTest () {
        return new Object[][] {
                {14, 2, 7},
                {110, 10, 11},
                {-50, 5, -10}
        };
    }

    @Test(dataProvider = "paramTest2")
    public void calcMyltiplyTest (int firstOperand, int secondOperand, int expectedResult) {

        //Act
        int actualResult = MyCalculator.multiply(firstOperand, secondOperand);

        //Assert
        Assert.assertEquals(actualResult, expectedResult);
    }
}

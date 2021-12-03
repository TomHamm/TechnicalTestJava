import demo.CustomNumberEntity;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class NumberFinderImplTest {

    @Test
    public void whenReadFromFileMethodIsCalled_usingExampleFile_expectedValuesAreStoredInCustomNumberEntityList() {
        NumberFinderImpl numberFinderImp = new NumberFinderImpl();
        List<CustomNumberEntity> customNumberEntityList = numberFinderImp.readFromFile("Example.json");
        Assert.assertEquals("67", customNumberEntityList.get(0).getNumber());
        Assert.assertEquals("45", customNumberEntityList.get(1).getNumber());
        Assert.assertEquals("null", customNumberEntityList.get(2).getNumber());
        Assert.assertEquals("45", customNumberEntityList.get(3).getNumber());
        Assert.assertEquals("s", customNumberEntityList.get(4).getNumber());
        Assert.assertEquals("-3", customNumberEntityList.get(5).getNumber());
        Assert.assertEquals("12", customNumberEntityList.get(6).getNumber());
        Assert.assertEquals("100", customNumberEntityList.get(7).getNumber());
        Assert.assertEquals("3", customNumberEntityList.get(8).getNumber());
    }

    @Test
    public void whenContainsMethodIsCalled_andNumberIsInTheList_trueIsReturned() throws InterruptedException {
        NumberFinderImpl numberFinderImp = new NumberFinderImpl();
        List<CustomNumberEntity> customNumberEntityList = numberFinderImp.readFromFile("Example.json");
        boolean isNumberInList = numberFinderImp.contains(100, customNumberEntityList);
        Assert.assertEquals(true, isNumberInList);
    }

    @Test
    public void whenContainsMethodIsCalled_andNumberIsNotInTheList_falseIsReturned() throws InterruptedException {
        NumberFinderImpl numberFinderImp = new NumberFinderImpl();
        List<CustomNumberEntity> customNumberEntityList = numberFinderImp.readFromFile("Example.json");
        boolean isNumberInList = numberFinderImp.contains(88, customNumberEntityList);
        Assert.assertEquals(false, isNumberInList);
    }

}
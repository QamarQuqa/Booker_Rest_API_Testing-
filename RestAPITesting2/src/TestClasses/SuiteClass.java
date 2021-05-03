package TestClasses;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;




@RunWith(Suite.class)
@Suite.SuiteClasses({

    TestGetRestAPI.class,
    TestPostRestAPI.class,
    TestUpdateRestAPI.class,

})

public class SuiteClass {}
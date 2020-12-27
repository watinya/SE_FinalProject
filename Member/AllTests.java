package Member;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdministratorTest.class, MemberTest.class, StudentTest.class, TeacherTest.class })
public class AllTests {

}

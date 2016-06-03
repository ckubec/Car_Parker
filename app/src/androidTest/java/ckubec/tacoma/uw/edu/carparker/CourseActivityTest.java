package ckubec.tacoma.uw.edu.carparker;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;

import authenticate.LoginActivity;

/**
 * Created by philm on 6/3/2016.
 */
public class CourseActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public CourseActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();

    }

    public void testMainActivity() {
        boolean fragmentLoaded = solo.searchText("Main Activity");
        assertTrue("Main Activity fragment loaded", fragmentLoaded);
    }

    public void testAbout() {
        solo.clickOnButton(4);
        boolean foundAbout = solo.searchText("About Car Parker");
        assertTrue("About activity loaded", foundAbout);
        solo.goBack();
    }

    public void testSettings() {
        solo.clickOnButton(3);
        boolean foundSettings = solo.searchText("FreeActivity");
        solo.clickOnButton("LOGOUT");
        boolean worked = solo.searchText("Settings");
        assertTrue("Settings Worked",worked);
    }
}

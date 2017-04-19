package com.sci.testamigo.usermanagement.testsuites;

import static com.sci.testamigo.usermanagement.common.Constants.FEATUREDETAILS;

import org.junit.Test;

import com.sci.testamigo.usermanagement.pageobjects.EditProfile;
import com.sci.testamigo.usermanagement.pageobjects.Secondaryauthorizationdeatils;
import com.sci.testamigo.usermanagement.pageobjects.Signin;
import com.sci.testamigo.usermanagement.pageobjects.Signout;
import com.sci.testamigo.usermanagement.pageobjects.Users;
import com.sci.testamigo.usermanagement.report.ReportManager;
import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.HeaderFile;
import com.sci.testamigo.usermanagement.util.RunnerSetup;

public class UserManagementSuiteTest {
  AppSetup appsetup = new AppSetup();
  ReportManager reportManager = new ReportManager();
  HeaderFile headerFile = new HeaderFile();
  RunnerSetup runnersetup = new RunnerSetup();

  @Test
  /* gets corresponding feature files */
  public void Runner() throws Throwable {
    try {
      FEATUREDETAILS = reportManager.populateInitialData(Signin.class.getSimpleName());
      headerFile.preRequisites();
      
      FEATUREDETAILS = reportManager.populateInitialData(Users.class.getSimpleName());
      runnersetup.UMrunnerOptions(appsetup.getFeatureName(Users.class.getSimpleName()));
      
      FEATUREDETAILS = reportManager.populateInitialData(Secondaryauthorizationdeatils.class.getSimpleName());
      runnersetup.UMrunnerOptions(appsetup.getFeatureName(Secondaryauthorizationdeatils.class.getSimpleName()));
      
      FEATUREDETAILS = reportManager.populateInitialData(EditProfile.class.getSimpleName());
      runnersetup.UMrunnerOptions(appsetup.getFeatureName(EditProfile.class.getSimpleName()));
      
      FEATUREDETAILS = reportManager.populateInitialData(Signout.class.getSimpleName());    
      headerFile.postRequisites();

    } catch (Exception e) {
    	System.out.println(e);
      e.printStackTrace();
    }
  }
}

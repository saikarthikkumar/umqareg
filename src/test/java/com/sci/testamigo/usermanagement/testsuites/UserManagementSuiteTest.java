package com.sci.testamigo.usermanagement.testsuites;

import static com.sci.testamigo.usermanagement.common.Constants.FEATUREDETAILS;

import org.junit.Test;

import com.sci.testamigo.usermanagement.pageobjects.Signin;
import com.sci.testamigo.usermanagement.pageobjects.Signout;
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
  public void Runner() throws Throwable {
    try {
      FEATUREDETAILS = reportManager.populateInitialData(Signin.class.getSimpleName());
      headerFile.preRequisites();
      
      FEATUREDETAILS = reportManager.populateInitialData(appsetup.getFeatureName("users"));
      runnersetup.UMrunnerOptions(appsetup.getFeatureName("users"));
      
      
      FEATUREDETAILS = reportManager.populateInitialData(appsetup.getFeatureName("secondaryauthorizationdeatils"));
      runnersetup.UMrunnerOptions(appsetup.getFeatureName("secondaryauthorizationdeatils"));
      
      FEATUREDETAILS = reportManager.populateInitialData(Signout.class.getSimpleName());    
      headerFile.postRequisites();

    } catch (Exception e) {
    	System.out.println(e);
      e.printStackTrace();
    }
  }
}
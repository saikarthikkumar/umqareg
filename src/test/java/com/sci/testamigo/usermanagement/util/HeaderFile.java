package com.sci.testamigo.usermanagement.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeaderFile {
  RunnerSetup birunnersetup = new RunnerSetup();
  AppSetup appsetup = new AppSetup();
  protected transient final Log log = LogFactory.getLog(getClass());

  // ====================================================
  
  public void preRequisites() throws Throwable {
    birunnersetup.UMrunnerOptions(appsetup.getFeatureName("signin"));
  }

  // ====================================================
  
  public void postRequisites() throws Throwable {
    log.info("Start of the method Signing-Out");
    birunnersetup.UMrunnerOptions(appsetup.getFeatureName("signout"));
    log.info("End of the method Signing-Out");
  }
  
  // ====================================================
}
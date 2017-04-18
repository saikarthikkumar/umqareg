/**
 * @author Karthik
 *
 */
package com.sci.testamigo.usermanagement.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sci.testamigo.usermanagement.report.FeatureDetails;

public class Constants {

  public static String BROWSER_NAME = "browser.name";
  public static final String XML_REPOSITORY = "/xml_repository/";
  public static final String XML = ".xml";

  public static final String PAGE_ELEMENT = "page-element";
  public static final String SUB_ELEMENT = "sub-element";
  public static final String CATEGORY = "category";
  public static final String CATEGORY_NORMAL = "normal";
  public static final String CATEGORY_PARALLEL = "parallel";
  public static final String CATEGORY_INNER = "inner";

  public static final String NO = "no";
  public static final String TYPE = "type";
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String CLASS_NAME = "className";
  public static final String CSS_SELECTOR = "cssSelector";
  public static final String XPATH = "xPath";
  public static final String LINK_TEXT = "linkText";
  public static final String DATA = "data";

  public static String UNDER_SCORE = "_";
  public static final String PNG = ".png";

  public static final String APPLICATION_URL = "application.url";
  public static final String STEPDEF_PATH = "step.definition.path";
  public static final String FEATURE_PATH = "feature.path";
  public static final String BI_USED_TAG = "BI.used.tag";

  public static final String SCREEN_SHOTS_REPOSITORY = "//screen_shots_repository//";

  public static final boolean runnerflag = true;
  public static final boolean suiteflag = false;

  public static final String FEATUREFILE_PATH = "src//generated-test-sources//cucumber";

  static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
  static Date date = new Date();
  public static final String time = dateFormat.format(date);

  public static FeatureDetails FEATUREDETAILS;
  public static String SCEANRIO_NAME;

  public static char TRUE = '1';
  public static char FALSE = '0';
}
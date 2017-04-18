/**
 * @author Jp
 *
 */
package com.sci.testamigo.usermanagement.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.report.FeatureDetails;
import com.sci.testamigo.usermanagement.report.ScenarioDetails;
import com.sci.testamigo.usermanagement.report.StepDetails;

public class TestAmigoDBManagerHelper {
  protected transient final Log log = LogFactory.getLog(getClass());
  private static TestAmigoDBManagerHelper testmateDBManagerHelper;

  // ========================================================================

  // Making this class as a single-ton class.
  private TestAmigoDBManagerHelper() {
  }

  // ========================================================================

  public static TestAmigoDBManagerHelper getInstance() {
    if (testmateDBManagerHelper == null) {
      return new TestAmigoDBManagerHelper();
    } else {
      return testmateDBManagerHelper;
    }
  }

  // ========================================================================
  public FeatureDetails saveFeatureDetails(FeatureDetails featureDetails) {
    log.info("START of the method saveFeatureDetails");
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String insertQuery = null;
    TestAmigoDBConnectionManager dbConnectionManager = null;
    long timeStamp = 0;
    String featureDetailsUnqID = null;

    try {
      if (true) {
        dbConnectionManager = TestAmigoDBConnectionManager.getInstance();
        if (dbConnectionManager != null) {
          connection = dbConnectionManager.getPooledConnection();
          if (connection != null) {
            insertQuery = "insert into feature_details(feature_details_unique_id,feature_name, feature_definition, is_execute, status, start_time, end_time)";
            insertQuery = insertQuery.concat(" values(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement = connection.prepareStatement(insertQuery);
            if (preparedStatement != null) {
              timeStamp = System.currentTimeMillis();
              featureDetailsUnqID = "FD_" + timeStamp;
              preparedStatement.setString(1, featureDetailsUnqID);
              preparedStatement.setString(2, featureDetails.getFeatureName());
              preparedStatement.setString(3, featureDetails.getFeatureDefinition());
              preparedStatement.setString(4, String.valueOf(featureDetails.isExecute()));
              preparedStatement.setString(5, featureDetails.getStatus());
              if (featureDetails.isExecute() == '1') {
                preparedStatement.setTimestamp(6, new Timestamp(featureDetails.getStartTimeStamp()));
                preparedStatement.setTimestamp(7, new Timestamp(featureDetails.getEndTimeStamp()));
              } else {
                preparedStatement.setTimestamp(6, new Timestamp(timeStamp));
                preparedStatement.setTimestamp(7, new Timestamp(timeStamp));
              }
              int noOfRecordsEffected = preparedStatement.executeUpdate();
              if (noOfRecordsEffected > 0) {
                log.info("saveFeatureDetails saved successfully");
                featureDetails.setFeatureDetailsId(getFeatureDetails(0, featureDetailsUnqID));
              }
            } else {
              log.info("preparedStatement object is null");
            }
          } else {
            log.info("connection object is null");
          }
        } else {
          log.info("dbConnectionManager object is null");
        }
      }
    } catch (Exception e) {
      log.error("PROBLEM in the method:" + featureDetails.getFeatureName()
          + " saveFeatureDetails in the TestMateDBConnectionManager Class");
      e.printStackTrace();
      log.error(e);
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        log.error(e);
      }
    }
    log.info("END of the method" + featureDetails.getFeatureName() + " saveFeatureDetails");
    return featureDetails;
  }

  // ========================================================================

  public long getFeatureDetails(long featureDetailsId, String featureDetailsUnqID) {
    log.info("START of the method getFeatureDetails");
    FeatureDetails featureDetails = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String selectQuery = null;
    TestAmigoDBConnectionManager dbConnectionManager = null;

    try {
      dbConnectionManager = TestAmigoDBConnectionManager.getInstance();
      if (dbConnectionManager != null) {
        connection = dbConnectionManager.getPooledConnection();
        if (connection != null) {
          statement = connection.createStatement();
          if (statement != null) {
            selectQuery = "SELECT * FROM feature_details as tscd WHERE ";
            if (!featureDetailsUnqID.equals("null")) {
              selectQuery = selectQuery
                  .concat(" tscd.feature_details_unique_id='" + featureDetailsUnqID + "'");
            } else {
              selectQuery = selectQuery.concat(" tscd.feature_id=" + featureDetailsId);
            }
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet != null) {
              while (resultSet.next()) {
                featureDetails = new FeatureDetails();
                featureDetails.setFeatureDetailsId(resultSet.getLong("feature_id"));
				featureDetails.setFeatureName(resultSet.getString("feature_name"));
              }
            } else {
              log.info("result set is null");
            }
          } else {
            log.info("Statement is null");
          }
        } else {
          log.info("Unable to get the pooled connection");
        }
      } else {
        log.info("Unable to get the dbConnectionManager object");
      }
    } catch (Exception e) {
      log.error("PROBLEM in the method: " + featureDetails.getFeatureName()
          + " getFeatureDetails in the TestMateDBConnectionManager Class");
      log.error(e);
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        if (statement != null) {
          statement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        log.error(e);
      }
    }
    log.info("END of the method " + featureDetails.getFeatureName() + " getFeatureDetails");
    return featureDetails.getFeatureDetailsId();
  }

  // ========================================================================

  public long saveScenarioDetails(ScenarioDetails scenarioDetails) {
    log.info("START of the method saveScenarioDetails");
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String insertQuery = null;
    TestAmigoDBConnectionManager dbConnectionManager = null;
    long timeStamp = 0;
    String scenarioUnqId = null;
    long scenarioId = 0;

    try {
      dbConnectionManager = TestAmigoDBConnectionManager.getInstance();
      if (dbConnectionManager != null) {
        connection = dbConnectionManager.getPooledConnection();
        if (connection != null) {
          insertQuery = "insert into scenario_details(scenario_unique_id,scenario_name, tags, is_execute, status, error_msg, screen_shot_location, start_time, end_time, feature_id)";
          insertQuery = insertQuery.concat(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
          preparedStatement = connection.prepareStatement(insertQuery);
          if (preparedStatement != null) {
            timeStamp = System.currentTimeMillis();
            scenarioUnqId = "TD_" + timeStamp;
            preparedStatement.setString(1, scenarioUnqId);
            preparedStatement.setString(2, scenarioDetails.getScenarioName());
            preparedStatement.setString(3, scenarioDetails.getTags());
            preparedStatement.setString(4, String.valueOf(scenarioDetails.isExecute()));
            preparedStatement.setString(5, scenarioDetails.getStatus());
            preparedStatement.setString(6, scenarioDetails.getErrorMsg());
            preparedStatement.setString(7, scenarioDetails.getScreenShotLocation());
            if (scenarioDetails.isExecute() == '1') {
              preparedStatement.setTimestamp(8, new Timestamp(scenarioDetails.getStartTimeStamp()));
              preparedStatement.setTimestamp(9, new Timestamp(scenarioDetails.getEndTimeStamp()));
            } else {
              preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
              preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            }
            preparedStatement.setLong(10, Constants.FEATUREDETAILS.getFeatureDetailsId());
            int noOfRecordsEffected = preparedStatement.executeUpdate();
            if (noOfRecordsEffected > 0) {
              log.info(
                  "saveScenarioDetails " + scenarioDetails.getScenarioName() + " saved successfully");
              scenarioId = getScenarioDetails(0, scenarioUnqId);
            }
          } else {
            log.info("preparedStatement object is null");
          }
        } else {
          log.info("connection object is null");
        }
      } else {
        log.info("dbConnectionManager object is null");
      }
    } catch (Exception e) {
      log.error("PROBLEM in the method: " + scenarioDetails.getScenarioName()
          + " saveScenarioDetails in the TestAmigoDBConnectionManager Class");
      log.error(e);
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        log.error(e);
      }
    }
    log.info("END of the method " + scenarioDetails.getScenarioName() + " saveScenarioDetails");
    return scenarioId;
  }

  // ========================================================================

  public long getScenarioDetails(int scenarioId, String scenarioUnqId) {
    log.info("START of the method getSceanrio");
    ScenarioDetails scenarioDetails = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String selectQuery = null;
    TestAmigoDBConnectionManager dbConnectionManager = null;

    try {
      dbConnectionManager = TestAmigoDBConnectionManager.getInstance();
      if (dbConnectionManager != null) {
        connection = dbConnectionManager.getPooledConnection();
        if (connection != null) {
          statement = connection.createStatement();
          if (statement != null) {
            selectQuery = "SELECT * FROM scenario_details as sd WHERE ";
            if (!scenarioUnqId.equals("null")) {
              selectQuery = selectQuery.concat(" sd.scenario_unique_id='" + scenarioUnqId + "'");
            } else {
              selectQuery = selectQuery.concat(" sd.scenario_id=" + scenarioId);
            }
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet != null) {
              while (resultSet.next()) {
                scenarioDetails = new ScenarioDetails();
                scenarioDetails.setScenarioDetailsId(resultSet.getLong("scenario_id"));
              }
            } else {
              log.info("result set is null");
            }
          } else {
            log.info("Statement is null");
          }
        } else {
          log.info("Unable to get the pooled connection");
        }
      } else {
        log.info("Unable to get the dbConnectionManager object");
      }
    } catch (Exception e) {
      log.error("PROBLEM in the method: " + scenarioDetails.getScenarioName() + " getScenarioDetails in the TestAmigoDBConnectionManager Class");
      log.error(e);
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        if (statement != null) {
          statement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        log.error(e);
      }
    }
    log.info("END of the method getSaveScenarioDetails");
    return scenarioDetails.getScenarioDetailsId();
  }

  // ========================================================================

  public void saveStepDetails(StepDetails stepDetails, long scenarioId) {
    log.info("START of the method saveStepDetails");
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String insertQuery = null;
    TestAmigoDBConnectionManager dbConnectionManager = null;

    try {
      dbConnectionManager = TestAmigoDBConnectionManager.getInstance();
      if (dbConnectionManager != null) {
        connection = dbConnectionManager.getPooledConnection();
        if (connection != null) {
          insertQuery = "insert into step_details(step_name, status, error_msg, screenshot_location, scenario_id, custom_message)";
          insertQuery = insertQuery.concat(" values(?, ?, ?, ?, ?, ?)");
          preparedStatement = connection.prepareStatement(insertQuery);
          if (preparedStatement != null) {
            preparedStatement.setString(1, stepDetails.getStep());
            preparedStatement.setString(2, stepDetails.getStatus());
            preparedStatement.setString(3, stepDetails.getErrorMessage());
            preparedStatement.setString(4, stepDetails.getScreenShotLocation());
            preparedStatement.setLong(5, scenarioId);
            preparedStatement.setString(6, stepDetails.getCustomMsg());
            int noOfRecordsEffected = preparedStatement.executeUpdate();
            if (noOfRecordsEffected > 0) {
              log.info("saveStepDetails saved successfully");
            }
          } else {
            log.info("preparedStatement object is null");
          }
        } else {
          log.info("connection object is null");
        }
      } else {
        log.info("dbConnectionManager object is null");
      }
    } catch (Exception e) {
      log.error("PROBLEM in the method: " + stepDetails.getStep()
          + "saveStepDetails in the TestAmigoDBConnectionManager Class");
      log.error(e);
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        log.error(e);
      }
    }
    log.info("END of the method " + stepDetails.getStep() + " saveStepDetails");
  }
}
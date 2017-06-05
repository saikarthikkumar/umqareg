/**
 * @author Jp
 *
 */
package com.sci.testamigo.usermanagement.db;

import static com.sci.testamigo.usermanagement.db.TestAmigoDBConstants.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import snaq.db.DBPoolDataSource;

public class TestAmigoDBConnectionManager {
  protected transient final Log log = LogFactory.getLog(getClass());
  private static TestAmigoDBConnectionManager testmaateDBConnectionManager = null;
  private DBPoolDataSource dbPoolDataSource = null;
  
  //===============================================================
  
  private TestAmigoDBConnectionManager() {
    init();
  }
  
  //===============================================================
  
  private void init() {
    ResourceBundle rb = null;
    String dbUrl = "jdbc:mysql://";
    
    try {
      rb = ResourceBundle.getBundle(TEST_AMIGO_DB_PROPS_FILE);
      dbPoolDataSource = new DBPoolDataSource();
      dbPoolDataSource.setName(rb.getString(POOL_NAME));
      dbPoolDataSource.setDescription(rb.getString(POOL_DESC));
      dbPoolDataSource.setDriverClassName(rb.getString(DRIVER_CLASS_NAME));
      dbUrl = dbUrl.concat(rb.getString(DATABASE_HOST)).concat(COLON).concat(rb.getString(DATABASE_PORT).concat(SLASH).concat(rb.getString(DATABASE_NAME)));
      dbPoolDataSource.setUrl(dbUrl);
      dbPoolDataSource.setUser(rb.getString(DATABASE_USER_NAME));
      dbPoolDataSource.setPassword(rb.getString(DATABASE_PASSWORD));
      dbPoolDataSource.setMinPool(Integer.parseInt(rb.getString(MIN_POOL_COUNT)));
      dbPoolDataSource.setMaxPool(Integer.parseInt(rb.getString(MAX_POOL_COUNT)));
      dbPoolDataSource.setMaxSize(Integer.parseInt(rb.getString(MAX_SIZE)));
      dbPoolDataSource.setIdleTimeout(Integer.parseInt(rb.getString(IDLE_TIMEOUT)));
    } catch (Exception e) {
      log.error(e);
    }
  }
  
  //===============================================================
  
  public static TestAmigoDBConnectionManager getInstance(){
    if (testmaateDBConnectionManager == null) {
      testmaateDBConnectionManager = new TestAmigoDBConnectionManager();
      return testmaateDBConnectionManager;
    } else {
      return testmaateDBConnectionManager;
    }
  }
  
  //===============================================================
  
  public Connection getPooledConnection() {
    Connection connection = null;
    
    try {
      if(dbPoolDataSource != null) {
        connection = dbPoolDataSource.getConnection();
        if(connection == null) {
          log.info("INFO: As unable to get the connection. Initializing the datasource again.");
          init();
          
          if(dbPoolDataSource != null) {
            log.info("INFO: And getting the connection again");
            connection = dbPoolDataSource.getConnection();
          }
        }
      }
    } catch (SQLException e) {
      log.error("SQLException occurred while getting the connection object.");
      log.error(e);
    } catch (Exception e) {
      log.error(e);
    }
    return connection;
  }
  
  //===============================================================
}
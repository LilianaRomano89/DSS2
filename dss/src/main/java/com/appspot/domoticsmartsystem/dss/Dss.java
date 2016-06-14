/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-05-27 16:00:31 UTC)
 * on 2016-06-13 at 18:46:12 UTC 
 * Modify at your own risk.
 */

package com.appspot.domoticsmartsystem.dss;

/**
 * Service definition for Dss (v1).
 *
 * <p>
 * Domotic Smart System Endpoints
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link DssRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Dss extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.22.0 of the dss library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://domoticsmartsystem.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "dss/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Dss(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Dss(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the NotificationApi collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Dss dss = new Dss(...);}
   *   {@code Dss.NotificationApi.List request = dss.notificationApi().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public NotificationApi notificationApi() {
    return new NotificationApi();
  }

  /**
   * The "notificationApi" collection of methods.
   */
  public class NotificationApi {

    /**
     * Create a request for the method "notificationApi.register".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link Register#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.NotificationMessageRegister}
     * @return the request
     */
    public Register register(com.appspot.domoticsmartsystem.dss.model.NotificationMessageRegister content) throws java.io.IOException {
      Register result = new Register(content);
      initialize(result);
      return result;
    }

    public class Register extends DssRequest<com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse> {

      private static final String REST_PATH = "notification/register";

      /**
       * Create a request for the method "notificationApi.register".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link Register#execute()} method to invoke the remote operation. <p>
       * {@link
       * Register#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.NotificationMessageRegister}
       * @since 1.13
       */
      protected Register(com.appspot.domoticsmartsystem.dss.model.NotificationMessageRegister content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse.class);
      }

      @Override
      public Register setAlt(java.lang.String alt) {
        return (Register) super.setAlt(alt);
      }

      @Override
      public Register setFields(java.lang.String fields) {
        return (Register) super.setFields(fields);
      }

      @Override
      public Register setKey(java.lang.String key) {
        return (Register) super.setKey(key);
      }

      @Override
      public Register setOauthToken(java.lang.String oauthToken) {
        return (Register) super.setOauthToken(oauthToken);
      }

      @Override
      public Register setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Register) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Register setQuotaUser(java.lang.String quotaUser) {
        return (Register) super.setQuotaUser(quotaUser);
      }

      @Override
      public Register setUserIp(java.lang.String userIp) {
        return (Register) super.setUserIp(userIp);
      }

      @Override
      public Register set(String parameterName, Object value) {
        return (Register) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "notificationApi.unregister".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link Unregister#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest}
     * @return the request
     */
    public Unregister unregister(com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest content) throws java.io.IOException {
      Unregister result = new Unregister(content);
      initialize(result);
      return result;
    }

    public class Unregister extends DssRequest<com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse> {

      private static final String REST_PATH = "notification/unregister";

      /**
       * Create a request for the method "notificationApi.unregister".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link Unregister#execute()} method to invoke the remote operation. <p>
       * {@link
       * Unregister#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest}
       * @since 1.13
       */
      protected Unregister(com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse.class);
      }

      @Override
      public Unregister setAlt(java.lang.String alt) {
        return (Unregister) super.setAlt(alt);
      }

      @Override
      public Unregister setFields(java.lang.String fields) {
        return (Unregister) super.setFields(fields);
      }

      @Override
      public Unregister setKey(java.lang.String key) {
        return (Unregister) super.setKey(key);
      }

      @Override
      public Unregister setOauthToken(java.lang.String oauthToken) {
        return (Unregister) super.setOauthToken(oauthToken);
      }

      @Override
      public Unregister setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Unregister) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Unregister setQuotaUser(java.lang.String quotaUser) {
        return (Unregister) super.setQuotaUser(quotaUser);
      }

      @Override
      public Unregister setUserIp(java.lang.String userIp) {
        return (Unregister) super.setUserIp(userIp);
      }

      @Override
      public Unregister set(String parameterName, Object value) {
        return (Unregister) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the SensorApi collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Dss dss = new Dss(...);}
   *   {@code Dss.SensorApi.List request = dss.sensorApi().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public SensorApi sensorApi() {
    return new SensorApi();
  }

  /**
   * The "sensorApi" collection of methods.
   */
  public class SensorApi {

    /**
     * Create a request for the method "sensorApi.alarm_action".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link AlarmAction#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.SensorMessageAlarmActionRequest}
     * @return the request
     */
    public AlarmAction alarmAction(com.appspot.domoticsmartsystem.dss.model.SensorMessageAlarmActionRequest content) throws java.io.IOException {
      AlarmAction result = new AlarmAction(content);
      initialize(result);
      return result;
    }

    public class AlarmAction extends DssRequest<com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse> {

      private static final String REST_PATH = "sensor/alarm_action";

      /**
       * Create a request for the method "sensorApi.alarm_action".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link AlarmAction#execute()} method to invoke the remote operation. <p>
       * {@link
       * AlarmAction#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.SensorMessageAlarmActionRequest}
       * @since 1.13
       */
      protected AlarmAction(com.appspot.domoticsmartsystem.dss.model.SensorMessageAlarmActionRequest content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse.class);
      }

      @Override
      public AlarmAction setAlt(java.lang.String alt) {
        return (AlarmAction) super.setAlt(alt);
      }

      @Override
      public AlarmAction setFields(java.lang.String fields) {
        return (AlarmAction) super.setFields(fields);
      }

      @Override
      public AlarmAction setKey(java.lang.String key) {
        return (AlarmAction) super.setKey(key);
      }

      @Override
      public AlarmAction setOauthToken(java.lang.String oauthToken) {
        return (AlarmAction) super.setOauthToken(oauthToken);
      }

      @Override
      public AlarmAction setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (AlarmAction) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public AlarmAction setQuotaUser(java.lang.String quotaUser) {
        return (AlarmAction) super.setQuotaUser(quotaUser);
      }

      @Override
      public AlarmAction setUserIp(java.lang.String userIp) {
        return (AlarmAction) super.setUserIp(userIp);
      }

      @Override
      public AlarmAction set(String parameterName, Object value) {
        return (AlarmAction) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "sensorApi.sensor_state".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link SensorState#execute()} method to invoke the remote operation.
     *
     * @param authToken
     * @return the request
     */
    public SensorState sensorState(java.lang.String authToken) throws java.io.IOException {
      SensorState result = new SensorState(authToken);
      initialize(result);
      return result;
    }

    public class SensorState extends DssRequest<com.appspot.domoticsmartsystem.dss.model.SensorMessageStateResponse> {

      private static final String REST_PATH = "sensor/sensor_state";

      /**
       * Create a request for the method "sensorApi.sensor_state".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link SensorState#execute()} method to invoke the remote operation. <p>
       * {@link
       * SensorState#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param authToken
       * @since 1.13
       */
      protected SensorState(java.lang.String authToken) {
        super(Dss.this, "GET", REST_PATH, null, com.appspot.domoticsmartsystem.dss.model.SensorMessageStateResponse.class);
        this.authToken = com.google.api.client.util.Preconditions.checkNotNull(authToken, "Required parameter authToken must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public SensorState setAlt(java.lang.String alt) {
        return (SensorState) super.setAlt(alt);
      }

      @Override
      public SensorState setFields(java.lang.String fields) {
        return (SensorState) super.setFields(fields);
      }

      @Override
      public SensorState setKey(java.lang.String key) {
        return (SensorState) super.setKey(key);
      }

      @Override
      public SensorState setOauthToken(java.lang.String oauthToken) {
        return (SensorState) super.setOauthToken(oauthToken);
      }

      @Override
      public SensorState setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (SensorState) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public SensorState setQuotaUser(java.lang.String quotaUser) {
        return (SensorState) super.setQuotaUser(quotaUser);
      }

      @Override
      public SensorState setUserIp(java.lang.String userIp) {
        return (SensorState) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key("auth.token")
      private java.lang.String authToken;

      /**

       */
      public java.lang.String getAuthToken() {
        return authToken;
      }

      public SensorState setAuthToken(java.lang.String authToken) {
        this.authToken = authToken;
        return this;
      }

      @com.google.api.client.util.Key("auth.email")
      private java.lang.String authEmail;

      /**

       */
      public java.lang.String getAuthEmail() {
        return authEmail;
      }

      public SensorState setAuthEmail(java.lang.String authEmail) {
        this.authEmail = authEmail;
        return this;
      }

      @com.google.api.client.util.Key("period.milliseconds")
      private java.lang.Long periodMilliseconds;

      /**

       */
      public java.lang.Long getPeriodMilliseconds() {
        return periodMilliseconds;
      }

      public SensorState setPeriodMilliseconds(java.lang.Long periodMilliseconds) {
        this.periodMilliseconds = periodMilliseconds;
        return this;
      }

      @com.google.api.client.util.Key("period.time_zone_offset")
      private java.lang.Long periodTimeZoneOffset;

      /**

       */
      public java.lang.Long getPeriodTimeZoneOffset() {
        return periodTimeZoneOffset;
      }

      public SensorState setPeriodTimeZoneOffset(java.lang.Long periodTimeZoneOffset) {
        this.periodTimeZoneOffset = periodTimeZoneOffset;
        return this;
      }

      @Override
      public SensorState set(String parameterName, Object value) {
        return (SensorState) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the UserApi collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Dss dss = new Dss(...);}
   *   {@code Dss.UserApi.List request = dss.userApi().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public UserApi userApi() {
    return new UserApi();
  }

  /**
   * The "userApi" collection of methods.
   */
  public class UserApi {

    /**
     * Create a request for the method "userApi.login".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link Login#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageLoginRequest}
     * @return the request
     */
    public Login login(com.appspot.domoticsmartsystem.dss.model.UserMessageLoginRequest content) throws java.io.IOException {
      Login result = new Login(content);
      initialize(result);
      return result;
    }

    public class Login extends DssRequest<com.appspot.domoticsmartsystem.dss.model.UserMessageLoginResponse> {

      private static final String REST_PATH = "user/login";

      /**
       * Create a request for the method "userApi.login".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link Login#execute()} method to invoke the remote operation. <p> {@link
       * Login#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageLoginRequest}
       * @since 1.13
       */
      protected Login(com.appspot.domoticsmartsystem.dss.model.UserMessageLoginRequest content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.UserMessageLoginResponse.class);
      }

      @Override
      public Login setAlt(java.lang.String alt) {
        return (Login) super.setAlt(alt);
      }

      @Override
      public Login setFields(java.lang.String fields) {
        return (Login) super.setFields(fields);
      }

      @Override
      public Login setKey(java.lang.String key) {
        return (Login) super.setKey(key);
      }

      @Override
      public Login setOauthToken(java.lang.String oauthToken) {
        return (Login) super.setOauthToken(oauthToken);
      }

      @Override
      public Login setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Login) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Login setQuotaUser(java.lang.String quotaUser) {
        return (Login) super.setQuotaUser(quotaUser);
      }

      @Override
      public Login setUserIp(java.lang.String userIp) {
        return (Login) super.setUserIp(userIp);
      }

      @Override
      public Login set(String parameterName, Object value) {
        return (Login) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "userApi.register".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link Register#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageRegistrationRequest}
     * @return the request
     */
    public Register register(com.appspot.domoticsmartsystem.dss.model.UserMessageRegistrationRequest content) throws java.io.IOException {
      Register result = new Register(content);
      initialize(result);
      return result;
    }

    public class Register extends DssRequest<com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse> {

      private static final String REST_PATH = "user/register";

      /**
       * Create a request for the method "userApi.register".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link Register#execute()} method to invoke the remote operation. <p>
       * {@link
       * Register#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageRegistrationRequest}
       * @since 1.13
       */
      protected Register(com.appspot.domoticsmartsystem.dss.model.UserMessageRegistrationRequest content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse.class);
      }

      @Override
      public Register setAlt(java.lang.String alt) {
        return (Register) super.setAlt(alt);
      }

      @Override
      public Register setFields(java.lang.String fields) {
        return (Register) super.setFields(fields);
      }

      @Override
      public Register setKey(java.lang.String key) {
        return (Register) super.setKey(key);
      }

      @Override
      public Register setOauthToken(java.lang.String oauthToken) {
        return (Register) super.setOauthToken(oauthToken);
      }

      @Override
      public Register setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Register) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Register setQuotaUser(java.lang.String quotaUser) {
        return (Register) super.setQuotaUser(quotaUser);
      }

      @Override
      public Register setUserIp(java.lang.String userIp) {
        return (Register) super.setUserIp(userIp);
      }

      @Override
      public Register set(String parameterName, Object value) {
        return (Register) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "userApi.validateUser".
     *
     * This request holds the parameters needed by the dss server.  After setting any optional
     * parameters, call the {@link ValidateUser#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest}
     * @return the request
     */
    public ValidateUser validateUser(com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest content) throws java.io.IOException {
      ValidateUser result = new ValidateUser(content);
      initialize(result);
      return result;
    }

    public class ValidateUser extends DssRequest<com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse> {

      private static final String REST_PATH = "user/validateUser";

      /**
       * Create a request for the method "userApi.validateUser".
       *
       * This request holds the parameters needed by the the dss server.  After setting any optional
       * parameters, call the {@link ValidateUser#execute()} method to invoke the remote operation. <p>
       * {@link
       * ValidateUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest}
       * @since 1.13
       */
      protected ValidateUser(com.appspot.domoticsmartsystem.dss.model.UserMessageAuthRequest content) {
        super(Dss.this, "POST", REST_PATH, content, com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse.class);
      }

      @Override
      public ValidateUser setAlt(java.lang.String alt) {
        return (ValidateUser) super.setAlt(alt);
      }

      @Override
      public ValidateUser setFields(java.lang.String fields) {
        return (ValidateUser) super.setFields(fields);
      }

      @Override
      public ValidateUser setKey(java.lang.String key) {
        return (ValidateUser) super.setKey(key);
      }

      @Override
      public ValidateUser setOauthToken(java.lang.String oauthToken) {
        return (ValidateUser) super.setOauthToken(oauthToken);
      }

      @Override
      public ValidateUser setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (ValidateUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public ValidateUser setQuotaUser(java.lang.String quotaUser) {
        return (ValidateUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public ValidateUser setUserIp(java.lang.String userIp) {
        return (ValidateUser) super.setUserIp(userIp);
      }

      @Override
      public ValidateUser set(String parameterName, Object value) {
        return (ValidateUser) super.set(parameterName, value);
      }
    }

  }

  /**
   * Builder for {@link Dss}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Dss}. */
    @Override
    public Dss build() {
      return new Dss(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link DssRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setDssRequestInitializer(
        DssRequestInitializer dssRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(dssRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}

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

package com.appspot.domoticsmartsystem.dss.model;

/**
 * Model definition for UserMessageRegistrationRequest.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the dss. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UserMessageRegistrationRequest extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String email;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String firstname;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String host;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lastname;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String password;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmail() {
    return email;
  }

  /**
   * @param email email or {@code null} for none
   */
  public UserMessageRegistrationRequest setEmail(java.lang.String email) {
    this.email = email;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname firstname or {@code null} for none
   */
  public UserMessageRegistrationRequest setFirstname(java.lang.String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getHost() {
    return host;
  }

  /**
   * @param host host or {@code null} for none
   */
  public UserMessageRegistrationRequest setHost(java.lang.String host) {
    this.host = host;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLastname() {
    return lastname;
  }

  /**
   * @param lastname lastname or {@code null} for none
   */
  public UserMessageRegistrationRequest setLastname(java.lang.String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPassword() {
    return password;
  }

  /**
   * @param password password or {@code null} for none
   */
  public UserMessageRegistrationRequest setPassword(java.lang.String password) {
    this.password = password;
    return this;
  }

  @Override
  public UserMessageRegistrationRequest set(String fieldName, Object value) {
    return (UserMessageRegistrationRequest) super.set(fieldName, value);
  }

  @Override
  public UserMessageRegistrationRequest clone() {
    return (UserMessageRegistrationRequest) super.clone();
  }

}

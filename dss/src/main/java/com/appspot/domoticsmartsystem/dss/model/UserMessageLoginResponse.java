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
 * on 2016-06-06 at 10:03:53 UTC 
 * Modify at your own risk.
 */

package com.appspot.domoticsmartsystem.dss.model;

/**
 * Model definition for UserMessageLoginResponse.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the dss. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UserMessageLoginResponse extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String host;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GenericMessagesResponse response;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String token;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getHost() {
    return host;
  }

  /**
   * @param host host or {@code null} for none
   */
  public UserMessageLoginResponse setHost(java.lang.String host) {
    this.host = host;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public GenericMessagesResponse getResponse() {
    return response;
  }

  /**
   * @param response response or {@code null} for none
   */
  public UserMessageLoginResponse setResponse(GenericMessagesResponse response) {
    this.response = response;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getToken() {
    return token;
  }

  /**
   * @param token token or {@code null} for none
   */
  public UserMessageLoginResponse setToken(java.lang.String token) {
    this.token = token;
    return this;
  }

  @Override
  public UserMessageLoginResponse set(String fieldName, Object value) {
    return (UserMessageLoginResponse) super.set(fieldName, value);
  }

  @Override
  public UserMessageLoginResponse clone() {
    return (UserMessageLoginResponse) super.clone();
  }

}
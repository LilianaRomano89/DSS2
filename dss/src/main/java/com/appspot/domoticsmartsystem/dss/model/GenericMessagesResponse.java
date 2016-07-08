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
 * on 2016-06-29 at 20:13:09 UTC 
 * Modify at your own risk.
 */

package com.appspot.domoticsmartsystem.dss.model;

/**
 * Model definition for GenericMessagesResponse.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the dss. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class GenericMessagesResponse extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("status_code") @com.google.api.client.json.JsonString
  private java.lang.Long statusCode;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStatus() {
    return status;
  }

  /**
   * @param status status or {@code null} for none
   */
  public GenericMessagesResponse setStatus(java.lang.String status) {
    this.status = status;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getStatusCode() {
    return statusCode;
  }

  /**
   * @param statusCode statusCode or {@code null} for none
   */
  public GenericMessagesResponse setStatusCode(java.lang.Long statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  @Override
  public GenericMessagesResponse set(String fieldName, Object value) {
    return (GenericMessagesResponse) super.set(fieldName, value);
  }

  @Override
  public GenericMessagesResponse clone() {
    return (GenericMessagesResponse) super.clone();
  }

}

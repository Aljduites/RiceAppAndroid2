/**
 * Remoteme rest for advanced integration
 * If You want i can hide it but there is nothing danger
 *
 * OpenAPI spec version: 1.0
 * Contact: contact@remoteme.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.remoteme.client.api;

import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.invoker.ApiInvoker;
import org.remoteme.client.invoker.Pair;

import org.remoteme.client.model.*;

import java.util.*;

import org.remoteme.client.model.HelloDto;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class HellorestApi {
  String basePath = "https://app.remoteme.org";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
   * return some object
   * 
   * @param aRLiteToken generated token
   * @return HelloDto
   */
  public HelloDto  getHelloDtoUsingGET (String aRLiteToken) throws ApiException {
    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/arLite/rest/v1/NO_AUTH/getHelloDto/".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    // form params
    Map<String, String> localVarFormParams = new HashMap<String, String>();


    localVarHeaderParams.put("ARLiteToken", ApiInvoker.parameterToString(aRLiteToken));

    String[] localVarContentTypes = {
      
    };
    String localVarContentType = localVarContentTypes.length > 0 ? localVarContentTypes[0] : "application/json";

    if (localVarContentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      localVarPostBody = localVarBuilder.build();
    } else {
      // normal form params
          }

    try {
      String localVarResponse = apiInvoker.invokeAPI(basePath, localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarContentType);
      if(localVarResponse != null){
        return (HelloDto) ApiInvoker.deserialize(localVarResponse, "", HelloDto.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  /**
   * say hello to You
   * 
   * @param name name
   * @param aRLiteToken generated token
   * @return HelloDto
   */
  public HelloDto  getWithNameUsingGET (String name, String aRLiteToken) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling getWithNameUsingGET");
    }

    // create path and map variables
    String localVarPath = "/arLite/rest/v1/NO_AUTH/getWithName/{name}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "name" + "\\}", apiInvoker.escapeString(name.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    // form params
    Map<String, String> localVarFormParams = new HashMap<String, String>();


    localVarHeaderParams.put("ARLiteToken", ApiInvoker.parameterToString(aRLiteToken));

    String[] localVarContentTypes = {
      
    };
    String localVarContentType = localVarContentTypes.length > 0 ? localVarContentTypes[0] : "application/json";

    if (localVarContentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      localVarPostBody = localVarBuilder.build();
    } else {
      // normal form params
          }

    try {
      String localVarResponse = apiInvoker.invokeAPI(basePath, localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarContentType);
      if(localVarResponse != null){
        return (HelloDto) ApiInvoker.deserialize(localVarResponse, "", HelloDto.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  /**
   * return modified object user
   * 
   * @param hello hello
   * @param aRLiteToken generated token
   * @return HelloDto
   */
  public HelloDto  modifyUsingPOST (HelloDto hello, String aRLiteToken) throws ApiException {
    Object localVarPostBody = hello;
    // verify the required parameter 'hello' is set
    if (hello == null) {
       throw new ApiException(400, "Missing the required parameter 'hello' when calling modifyUsingPOST");
    }

    // create path and map variables
    String localVarPath = "/arLite/rest/v1/NO_AUTH/modify/".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    // form params
    Map<String, String> localVarFormParams = new HashMap<String, String>();


    localVarHeaderParams.put("ARLiteToken", ApiInvoker.parameterToString(aRLiteToken));

    String[] localVarContentTypes = {
      "application/json"
    };
    String localVarContentType = localVarContentTypes.length > 0 ? localVarContentTypes[0] : "application/json";

    if (localVarContentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      localVarPostBody = localVarBuilder.build();
    } else {
      // normal form params
          }

    try {
      String localVarResponse = apiInvoker.invokeAPI(basePath, localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarContentType);
      if(localVarResponse != null){
        return (HelloDto) ApiInvoker.deserialize(localVarResponse, "", HelloDto.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
}
# HellorestApi

All URIs are relative to *https://app.remoteme.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHelloDtoUsingGET**](HellorestApi.md#getHelloDtoUsingGET) | **GET** /arLite/rest/v1/NO_AUTH/getHelloDto/ | return some object
[**getWithNameUsingGET**](HellorestApi.md#getWithNameUsingGET) | **GET** /arLite/rest/v1/NO_AUTH/getWithName/{name}/ | say hello to You
[**modifyUsingPOST**](HellorestApi.md#modifyUsingPOST) | **POST** /arLite/rest/v1/NO_AUTH/modify/ | return modified object user


<a name="getHelloDtoUsingGET"></a>
# **getHelloDtoUsingGET**
> HelloDto getHelloDtoUsingGET(aRLiteToken)

return some object

### Example
```java
// Import classes:
//import org.remoteme.client.api.HellorestApi;

HellorestApi apiInstance = new HellorestApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    HelloDto result = apiInstance.getHelloDtoUsingGET(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HellorestApi#getHelloDtoUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**HelloDto**](HelloDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getWithNameUsingGET"></a>
# **getWithNameUsingGET**
> HelloDto getWithNameUsingGET(name, aRLiteToken)

say hello to You

### Example
```java
// Import classes:
//import org.remoteme.client.api.HellorestApi;

HellorestApi apiInstance = new HellorestApi();
String name = "name_example"; // String | name
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    HelloDto result = apiInstance.getWithNameUsingGET(name, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HellorestApi#getWithNameUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| name |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**HelloDto**](HelloDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyUsingPOST"></a>
# **modifyUsingPOST**
> HelloDto modifyUsingPOST(hello, aRLiteToken)

return modified object user

### Example
```java
// Import classes:
//import org.remoteme.client.api.HellorestApi;

HellorestApi apiInstance = new HellorestApi();
HelloDto hello = new HelloDto(); // HelloDto | hello
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    HelloDto result = apiInstance.modifyUsingPOST(hello, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HellorestApi#modifyUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **hello** | [**HelloDto**](HelloDto.md)| hello |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**HelloDto**](HelloDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


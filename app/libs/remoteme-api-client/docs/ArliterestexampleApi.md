# ArliterestexampleApi

All URIs are relative to *https://app.remoteme.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMessageTypeUsingPOST**](ArliterestexampleApi.md#getMessageTypeUsingPOST) | **POST** /arLite/restExample/v1/getMessageType/ | getMessageType
[**getUserMessageUsingGET**](ArliterestexampleApi.md#getUserMessageUsingGET) | **GET** /arLite/restExample/v1/getUserMessage/ | getUserMessage


<a name="getMessageTypeUsingPOST"></a>
# **getMessageTypeUsingPOST**
> ARemoteMeMessage getMessageTypeUsingPOST(pam, aRLiteToken)

getMessageType

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestexampleApi;

ArliterestexampleApi apiInstance = new ArliterestexampleApi();
ARemoteMeMessage pam = new ARemoteMeMessage(); // ARemoteMeMessage | pam
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    ARemoteMeMessage result = apiInstance.getMessageTypeUsingPOST(pam, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestexampleApi#getMessageTypeUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pam** | [**ARemoteMeMessage**](ARemoteMeMessage.md)| pam |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**ARemoteMeMessage**](ARemoteMeMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="getUserMessageUsingGET"></a>
# **getUserMessageUsingGET**
> UserMessage getUserMessageUsingGET(aRLiteToken)

getUserMessage

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestexampleApi;

ArliterestexampleApi apiInstance = new ArliterestexampleApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    UserMessage result = apiInstance.getUserMessageUsingGET(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestexampleApi#getUserMessageUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**UserMessage**](UserMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


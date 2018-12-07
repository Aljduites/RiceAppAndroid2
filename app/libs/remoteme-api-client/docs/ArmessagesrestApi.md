# ArmessagesrestApi

All URIs are relative to *https://app.remoteme.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sendMessageUsingPUT2**](ArmessagesrestApi.md#sendMessageUsingPUT2) | **PUT** /arLite/restExample/v1/message/send/bytes/ | send message
[**sendMessageUsingPUT3**](ArmessagesrestApi.md#sendMessageUsingPUT3) | **PUT** /arLite/restExample/v1/message/send/json/ | send message


<a name="sendMessageUsingPUT2"></a>
# **sendMessageUsingPUT2**
> sendMessageUsingPUT2(message, byteArrayARemoteMeMessageToSend_, aRLiteToken)

send message

Send message

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArmessagesrestApi;

ArmessagesrestApi apiInstance = new ArmessagesrestApi();
byte[] message = message_example; // byte[] | message
String byteArrayARemoteMeMessageToSend_ = "byteArrayARemoteMeMessageToSend__example"; // String | 
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    apiInstance.sendMessageUsingPUT2(message, byteArrayARemoteMeMessageToSend_, aRLiteToken);
} catch (ApiException e) {
    System.err.println("Exception when calling ArmessagesrestApi#sendMessageUsingPUT2");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **message** | **byte[]**| message |
 **byteArrayARemoteMeMessageToSend_** | **String**|  |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: */*

<a name="sendMessageUsingPUT3"></a>
# **sendMessageUsingPUT3**
> sendMessageUsingPUT3(message, jsonAMessageToSend, aRLiteToken)

send message

Send message

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArmessagesrestApi;

ArmessagesrestApi apiInstance = new ArmessagesrestApi();
AMessage message = new AMessage(); // AMessage | message
String jsonAMessageToSend = "jsonAMessageToSend_example"; // String | 
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    apiInstance.sendMessageUsingPUT3(message, jsonAMessageToSend, aRLiteToken);
} catch (ApiException e) {
    System.err.println("Exception when calling ArmessagesrestApi#sendMessageUsingPUT3");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **message** | [**AMessage**](AMessage.md)| message |
 **jsonAMessageToSend** | **String**|  |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


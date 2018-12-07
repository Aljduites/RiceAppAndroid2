# ArliterestApi

All URIs are relative to *https://app.remoteme.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getARLiteTokenUsingGET**](ArliterestApi.md#getARLiteTokenUsingGET) | **GET** /arLite/rest/v1/getToken/ | generateToken
[**getVariableValueUsingGET**](ArliterestApi.md#getVariableValueUsingGET) | **GET** /arLite/rest/v1/getVariableValue/{name}/{type}/ | getVariableValue
[**helloUsingGET**](ArliterestApi.md#helloUsingGET) | **GET** /arLite/rest/v1/hello/{name}/ | hello
[**logoutUsingGET**](ArliterestApi.md#logoutUsingGET) | **GET** /arLite/rest/v1/logout/ | clears session
[**registerErUsingPOST**](ArliterestApi.md#registerErUsingPOST) | **POST** /arLite/rest/v1/registerEr/ | Register
[**registerUsingPOST**](ArliterestApi.md#registerUsingPOST) | **POST** /arLite/rest/v1/register/ | Register
[**sendRemoteMeMessageUsingPUT**](ArliterestApi.md#sendRemoteMeMessageUsingPUT) | **PUT** /arLite/rest/v1/sendMessage/ | sendRemoteMeMessage
[**sendUsingGET2**](ArliterestApi.md#sendUsingGET2) | **GET** /arLite/rest/v1/getAllDevices/ | send
[**throwErrorUsingGET**](ArliterestApi.md#throwErrorUsingGET) | **GET** /arLite/rest/v1/throwError/ | throwError
[**updateMessageTokenUsingPUT**](ArliterestApi.md#updateMessageTokenUsingPUT) | **PUT** /arLite/rest/v1/addMessageToken/{deviceId}/{messageToken}/ | updateMessageToken


<a name="getARLiteTokenUsingGET"></a>
# **getARLiteTokenUsingGET**
> TokenDto getARLiteTokenUsingGET(username, password, aRLiteToken)

generateToken

token can be later use for authorize requests, and websockets, then add it in header as ARLiteToken

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String username = "username_example"; // String | username
String password = "password_example"; // String | password
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    TokenDto result = apiInstance.getARLiteTokenUsingGET(username, password, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#getARLiteTokenUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **String**| username |
 **password** | **String**| password |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**TokenDto**](TokenDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getVariableValueUsingGET"></a>
# **getVariableValueUsingGET**
> AVariableValue getVariableValueUsingGET(name, type, aRLiteToken)

getVariableValue

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String name = "name_example"; // String | name
String type = "type_example"; // String | type
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    AVariableValue result = apiInstance.getVariableValueUsingGET(name, type, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#getVariableValueUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| name |
 **type** | **String**| type |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**AVariableValue**](AVariableValue.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="helloUsingGET"></a>
# **helloUsingGET**
> HelloDto helloUsingGET(name, aRLiteToken)

hello

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String name = "name_example"; // String | name
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    HelloDto result = apiInstance.helloUsingGET(name, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#helloUsingGET");
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
 - **Accept**: */*

<a name="logoutUsingGET"></a>
# **logoutUsingGET**
> PlainResultDto logoutUsingGET(aRLiteToken)

clears session

clear session cookie at server side - usefull for logout since server creating session

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    PlainResultDto result = apiInstance.logoutUsingGET(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#logoutUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**PlainResultDto**](PlainResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="registerErUsingPOST"></a>
# **registerErUsingPOST**
> RegisterDto registerErUsingPOST(registerDto, aRLiteToken)

Register

Registers device - return deviceId new or old one

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
AndroidRegisterDto registerDto = new AndroidRegisterDto(); // AndroidRegisterDto | registerDto
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    RegisterDto result = apiInstance.registerErUsingPOST(registerDto, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#registerErUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registerDto** | [**AndroidRegisterDto**](AndroidRegisterDto.md)| registerDto |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**RegisterDto**](RegisterDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="registerUsingPOST"></a>
# **registerUsingPOST**
> RegisterDto registerUsingPOST(registerDto, aRLiteToken)

Register

Registers device - return deviceId new or old one

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
AndroidRegisterDto registerDto = new AndroidRegisterDto(); // AndroidRegisterDto | registerDto
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    RegisterDto result = apiInstance.registerUsingPOST(registerDto, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#registerUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registerDto** | [**AndroidRegisterDto**](AndroidRegisterDto.md)| registerDto |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**RegisterDto**](RegisterDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="sendRemoteMeMessageUsingPUT"></a>
# **sendRemoteMeMessageUsingPUT**
> StandardResponse sendRemoteMeMessageUsingPUT(messageDto, aRLiteToken)

sendRemoteMeMessage

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
AMessage messageDto = new AMessage(); // AMessage | messageDto
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    StandardResponse result = apiInstance.sendRemoteMeMessageUsingPUT(messageDto, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#sendRemoteMeMessageUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **messageDto** | [**AMessage**](AMessage.md)| messageDto |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**StandardResponse**](StandardResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="sendUsingGET2"></a>
# **sendUsingGET2**
> List&lt;DeviceDto&gt; sendUsingGET2(aRLiteToken)

send

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    List<DeviceDto> result = apiInstance.sendUsingGET2(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#sendUsingGET2");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**List&lt;DeviceDto&gt;**](DeviceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="throwErrorUsingGET"></a>
# **throwErrorUsingGET**
> Integer throwErrorUsingGET(aRLiteToken)

throwError

just throw error noting more so u can check

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    Integer result = apiInstance.throwErrorUsingGET(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#throwErrorUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateMessageTokenUsingPUT"></a>
# **updateMessageTokenUsingPUT**
> StandardResponse updateMessageTokenUsingPUT(deviceId, messageToken, aRLiteToken)

updateMessageToken

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArliterestApi;

ArliterestApi apiInstance = new ArliterestApi();
Integer deviceId = 56; // Integer | deviceId
String messageToken = "messageToken_example"; // String | messageToken
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    StandardResponse result = apiInstance.updateMessageTokenUsingPUT(deviceId, messageToken, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArliterestApi#updateMessageTokenUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deviceId** | **Integer**| deviceId |
 **messageToken** | **String**| messageToken |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**StandardResponse**](StandardResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


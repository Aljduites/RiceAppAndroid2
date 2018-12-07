# ArvariablesrestApi

All URIs are relative to *https://app.remoteme.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addSchedulerUsingPOST**](ArvariablesrestApi.md#addSchedulerUsingPOST) | **POST** /arLite/rest/v1/variables/{variableName}/{variableType}/schedulers/ | add new Scheduler
[**createVariableUsingPOST**](ArvariablesrestApi.md#createVariableUsingPOST) | **POST** /arLite/rest/v1/variables/ | add variables
[**getSchedulersUsingGET**](ArvariablesrestApi.md#getSchedulersUsingGET) | **GET** /arLite/rest/v1/variables/{variableName}/{variableType}/schedulers/ | get schedulers for variable
[**getVariablesUsingGET**](ArvariablesrestApi.md#getVariablesUsingGET) | **GET** /arLite/rest/v1/variables/ | get all local variables
[**removeSchedulerUsingDELETE**](ArvariablesrestApi.md#removeSchedulerUsingDELETE) | **DELETE** /arLite/rest/v1/variables/schedulers/{schedulerId}/ | add new Scheduler
[**removeVariableUsingDELETE**](ArvariablesrestApi.md#removeVariableUsingDELETE) | **DELETE** /arLite/rest/v1/variables/{variableName}/{variableType}/ | remove variable
[**updateSchedulerUsingPUT**](ArvariablesrestApi.md#updateSchedulerUsingPUT) | **PUT** /arLite/rest/v1/variables/schedulers/ | add new Scheduler
[**updateVariableUsingPUT**](ArvariablesrestApi.md#updateVariableUsingPUT) | **PUT** /arLite/rest/v1/variables/{variableName}/{variableType}/ | update variables


<a name="addSchedulerUsingPOST"></a>
# **addSchedulerUsingPOST**
> VariableSchedulerDto addSchedulerUsingPOST(variableSchedulerDto, variableName, variableType, aRLiteToken)

add new Scheduler

add new VariableScheduler id is ignored

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
VariableSchedulerDto variableSchedulerDto = new VariableSchedulerDto(); // VariableSchedulerDto | variableSchedulerDto
String variableName = "variableName_example"; // String | Variable name
String variableType = "variableType_example"; // String | Variable type
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    VariableSchedulerDto result = apiInstance.addSchedulerUsingPOST(variableSchedulerDto, variableName, variableType, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#addSchedulerUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **variableSchedulerDto** | [**VariableSchedulerDto**](VariableSchedulerDto.md)| variableSchedulerDto |
 **variableName** | **String**| Variable name |
 **variableType** | **String**| Variable type | [enum: BOOLEAN, INTEGER, TEXT, SMALL_INTEGER_3, SMALL_INTEGER_2, INTEGER_BOOLEAN, DOUBLE, TEXT_2, SMALL_INTEGER_2_TEXT_2]
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**VariableSchedulerDto**](VariableSchedulerDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createVariableUsingPOST"></a>
# **createVariableUsingPOST**
> VariableDto createVariableUsingPOST(v, aRLiteToken)

add variables

return enw created variable, mode is set as local

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
VariableDto v = new VariableDto(); // VariableDto | v
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    VariableDto result = apiInstance.createVariableUsingPOST(v, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#createVariableUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **v** | [**VariableDto**](VariableDto.md)| v |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**VariableDto**](VariableDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getSchedulersUsingGET"></a>
# **getSchedulersUsingGET**
> List&lt;VariableSchedulerDto&gt; getSchedulersUsingGET(variableType, type, variableName, aRLiteToken)

get schedulers for variable

Get schedulers for variable

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
String variableType = "variableType_example"; // String | variableType
String type = "type_example"; // String | Variable type
String variableName = "variableName_example"; // String | Variable name
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    List<VariableSchedulerDto> result = apiInstance.getSchedulersUsingGET(variableType, type, variableName, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#getSchedulersUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **variableType** | **String**| variableType | [enum: BOOLEAN, INTEGER, TEXT, SMALL_INTEGER_3, SMALL_INTEGER_2, INTEGER_BOOLEAN, DOUBLE, TEXT_2, SMALL_INTEGER_2_TEXT_2]
 **type** | **String**| Variable type |
 **variableName** | **String**| Variable name |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**List&lt;VariableSchedulerDto&gt;**](VariableSchedulerDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getVariablesUsingGET"></a>
# **getVariablesUsingGET**
> List&lt;VariableDto&gt; getVariablesUsingGET(aRLiteToken)

get all local variables

If network device created WEbSocketServer for local connection it will return localIP and port othervise null is return

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    List<VariableDto> result = apiInstance.getVariablesUsingGET(aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#getVariablesUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**List&lt;VariableDto&gt;**](VariableDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="removeSchedulerUsingDELETE"></a>
# **removeSchedulerUsingDELETE**
> Object removeSchedulerUsingDELETE(schedulerId, aRLiteToken)

add new Scheduler

update VariableScheduler

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
Integer schedulerId = 56; // Integer | schedulerId
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    Object result = apiInstance.removeSchedulerUsingDELETE(schedulerId, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#removeSchedulerUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **schedulerId** | **Integer**| schedulerId |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="removeVariableUsingDELETE"></a>
# **removeVariableUsingDELETE**
> VariableDto removeVariableUsingDELETE(variableName, variableType, aRLiteToken)

remove variable

return rmeoved variable

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
String variableName = "variableName_example"; // String | variableName
String variableType = "variableType_example"; // String | variableType
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    VariableDto result = apiInstance.removeVariableUsingDELETE(variableName, variableType, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#removeVariableUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **variableName** | **String**| variableName |
 **variableType** | **String**| variableType | [enum: BOOLEAN, INTEGER, TEXT, SMALL_INTEGER_3, SMALL_INTEGER_2, INTEGER_BOOLEAN, DOUBLE, TEXT_2, SMALL_INTEGER_2_TEXT_2]
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**VariableDto**](VariableDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateSchedulerUsingPUT"></a>
# **updateSchedulerUsingPUT**
> Object updateSchedulerUsingPUT(variableSchedulerDto, aRLiteToken)

add new Scheduler

update VariableScheduler

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
VariableSchedulerDto variableSchedulerDto = new VariableSchedulerDto(); // VariableSchedulerDto | variableSchedulerDto
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    Object result = apiInstance.updateSchedulerUsingPUT(variableSchedulerDto, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#updateSchedulerUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **variableSchedulerDto** | [**VariableSchedulerDto**](VariableSchedulerDto.md)| variableSchedulerDto |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateVariableUsingPUT"></a>
# **updateVariableUsingPUT**
> VariableDto updateVariableUsingPUT(variableName, variableType, v, aRLiteToken)

update variables

the variable type

### Example
```java
// Import classes:
//import org.remoteme.client.api.ArvariablesrestApi;

ArvariablesrestApi apiInstance = new ArvariablesrestApi();
String variableName = "variableName_example"; // String | variableName
String variableType = "variableType_example"; // String | variableType
VariableDto v = new VariableDto(); // VariableDto | v
String aRLiteToken = "aRLiteToken_example"; // String | generated token
try {
    VariableDto result = apiInstance.updateVariableUsingPUT(variableName, variableType, v, aRLiteToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArvariablesrestApi#updateVariableUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **variableName** | **String**| variableName |
 **variableType** | **String**| variableType | [enum: BOOLEAN, INTEGER, TEXT, SMALL_INTEGER_3, SMALL_INTEGER_2, INTEGER_BOOLEAN, DOUBLE, TEXT_2, SMALL_INTEGER_2_TEXT_2]
 **v** | [**VariableDto**](VariableDto.md)| v |
 **aRLiteToken** | **String**| generated token | [optional]

### Return type

[**VariableDto**](VariableDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


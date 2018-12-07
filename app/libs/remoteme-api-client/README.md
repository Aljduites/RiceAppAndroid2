# remoteme-api-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>org.remoteme</groupId>
    <artifactId>remoteme-api-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.remoteme:remoteme-api-client:0.0.1-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/remoteme-api-client-0.0.1-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.remoteme.client.api.ArliterestApi;

public class ArliterestApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://app.remoteme.org*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ArliterestApi* | [**getARLiteTokenUsingGET**](docs/ArliterestApi.md#getARLiteTokenUsingGET) | **GET** /arLite/rest/v1/getToken/ | generateToken
*ArliterestApi* | [**getVariableValueUsingGET**](docs/ArliterestApi.md#getVariableValueUsingGET) | **GET** /arLite/rest/v1/getVariableValue/{name}/{type}/ | getVariableValue
*ArliterestApi* | [**helloUsingGET**](docs/ArliterestApi.md#helloUsingGET) | **GET** /arLite/rest/v1/hello/{name}/ | hello
*ArliterestApi* | [**logoutUsingGET**](docs/ArliterestApi.md#logoutUsingGET) | **GET** /arLite/rest/v1/logout/ | clears session
*ArliterestApi* | [**registerErUsingPOST**](docs/ArliterestApi.md#registerErUsingPOST) | **POST** /arLite/rest/v1/registerEr/ | Register
*ArliterestApi* | [**registerUsingPOST**](docs/ArliterestApi.md#registerUsingPOST) | **POST** /arLite/rest/v1/register/ | Register
*ArliterestApi* | [**sendRemoteMeMessageUsingPUT**](docs/ArliterestApi.md#sendRemoteMeMessageUsingPUT) | **PUT** /arLite/rest/v1/sendMessage/ | sendRemoteMeMessage
*ArliterestApi* | [**sendUsingGET2**](docs/ArliterestApi.md#sendUsingGET2) | **GET** /arLite/rest/v1/getAllDevices/ | send
*ArliterestApi* | [**throwErrorUsingGET**](docs/ArliterestApi.md#throwErrorUsingGET) | **GET** /arLite/rest/v1/throwError/ | throwError
*ArliterestApi* | [**updateMessageTokenUsingPUT**](docs/ArliterestApi.md#updateMessageTokenUsingPUT) | **PUT** /arLite/rest/v1/addMessageToken/{deviceId}/{messageToken}/ | updateMessageToken
*ArliterestexampleApi* | [**getMessageTypeUsingPOST**](docs/ArliterestexampleApi.md#getMessageTypeUsingPOST) | **POST** /arLite/restExample/v1/getMessageType/ | getMessageType
*ArliterestexampleApi* | [**getUserMessageUsingGET**](docs/ArliterestexampleApi.md#getUserMessageUsingGET) | **GET** /arLite/restExample/v1/getUserMessage/ | getUserMessage
*ArmessagesrestApi* | [**sendMessageUsingPUT2**](docs/ArmessagesrestApi.md#sendMessageUsingPUT2) | **PUT** /arLite/restExample/v1/message/send/bytes/ | send message
*ArmessagesrestApi* | [**sendMessageUsingPUT3**](docs/ArmessagesrestApi.md#sendMessageUsingPUT3) | **PUT** /arLite/restExample/v1/message/send/json/ | send message
*ArvariablesrestApi* | [**addSchedulerUsingPOST**](docs/ArvariablesrestApi.md#addSchedulerUsingPOST) | **POST** /arLite/rest/v1/variables/{variableName}/{variableType}/schedulers/ | add new Scheduler
*ArvariablesrestApi* | [**createVariableUsingPOST**](docs/ArvariablesrestApi.md#createVariableUsingPOST) | **POST** /arLite/rest/v1/variables/ | add variables
*ArvariablesrestApi* | [**getSchedulersUsingGET**](docs/ArvariablesrestApi.md#getSchedulersUsingGET) | **GET** /arLite/rest/v1/variables/{variableName}/{variableType}/schedulers/ | get schedulers for variable
*ArvariablesrestApi* | [**getVariablesUsingGET**](docs/ArvariablesrestApi.md#getVariablesUsingGET) | **GET** /arLite/rest/v1/variables/ | get all local variables
*ArvariablesrestApi* | [**removeSchedulerUsingDELETE**](docs/ArvariablesrestApi.md#removeSchedulerUsingDELETE) | **DELETE** /arLite/rest/v1/variables/schedulers/{schedulerId}/ | add new Scheduler
*ArvariablesrestApi* | [**removeVariableUsingDELETE**](docs/ArvariablesrestApi.md#removeVariableUsingDELETE) | **DELETE** /arLite/rest/v1/variables/{variableName}/{variableType}/ | remove variable
*ArvariablesrestApi* | [**updateSchedulerUsingPUT**](docs/ArvariablesrestApi.md#updateSchedulerUsingPUT) | **PUT** /arLite/rest/v1/variables/schedulers/ | add new Scheduler
*ArvariablesrestApi* | [**updateVariableUsingPUT**](docs/ArvariablesrestApi.md#updateVariableUsingPUT) | **PUT** /arLite/rest/v1/variables/{variableName}/{variableType}/ | update variables
*HellorestApi* | [**getHelloDtoUsingGET**](docs/HellorestApi.md#getHelloDtoUsingGET) | **GET** /arLite/rest/v1/NO_AUTH/getHelloDto/ | return some object
*HellorestApi* | [**getWithNameUsingGET**](docs/HellorestApi.md#getWithNameUsingGET) | **GET** /arLite/rest/v1/NO_AUTH/getWithName/{name}/ | say hello to You
*HellorestApi* | [**modifyUsingPOST**](docs/HellorestApi.md#modifyUsingPOST) | **POST** /arLite/rest/v1/NO_AUTH/modify/ | return modified object user


## Documentation for Models

 - [AMessage](docs/AMessage.md)
 - [ARemoteMeMessage](docs/ARemoteMeMessage.md)
 - [AVariableValue](docs/AVariableValue.md)
 - [AndroidRegisterDto](docs/AndroidRegisterDto.md)
 - [DeviceDto](docs/DeviceDto.md)
 - [HelloDto](docs/HelloDto.md)
 - [PlainResultDto](docs/PlainResultDto.md)
 - [RegisterDto](docs/RegisterDto.md)
 - [StandardResponse](docs/StandardResponse.md)
 - [TokenDto](docs/TokenDto.md)
 - [UserMessage](docs/UserMessage.md)
 - [VariableDto](docs/VariableDto.md)
 - [VariableSchedulerDto](docs/VariableSchedulerDto.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

contact@remoteme.org


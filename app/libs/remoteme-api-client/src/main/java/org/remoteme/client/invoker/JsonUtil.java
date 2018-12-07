package org.remoteme.client.invoker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.remoteme.client.model.*;

public class JsonUtil {
  public static GsonBuilder gsonBuilder;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  }

  public static Gson getGson() {
    return gsonBuilder.create();
  }

  public static String serialize(Object obj){
    return getGson().toJson(obj);
  }

  public static <T> T deserializeToList(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
  }

  public static <T> T deserializeToObject(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
  }

  public static Type getListTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("AMessage".equalsIgnoreCase(className)) {
      return new TypeToken<List<AMessage>>(){}.getType();
    }
    
    if ("ARemoteMeMessage".equalsIgnoreCase(className)) {
      return new TypeToken<List<ARemoteMeMessage>>(){}.getType();
    }
    
    if ("AVariableValue".equalsIgnoreCase(className)) {
      return new TypeToken<List<AVariableValue>>(){}.getType();
    }
    
    if ("AndroidRegisterDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<AndroidRegisterDto>>(){}.getType();
    }
    
    if ("DeviceDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<DeviceDto>>(){}.getType();
    }
    
    if ("HelloDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<HelloDto>>(){}.getType();
    }
    
    if ("PlainResultDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<PlainResultDto>>(){}.getType();
    }
    
    if ("RegisterDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<RegisterDto>>(){}.getType();
    }
    
    if ("StandardResponse".equalsIgnoreCase(className)) {
      return new TypeToken<List<StandardResponse>>(){}.getType();
    }
    
    if ("TokenDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<TokenDto>>(){}.getType();
    }
    
    if ("UserMessage".equalsIgnoreCase(className)) {
      return new TypeToken<List<UserMessage>>(){}.getType();
    }
    
    if ("VariableDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<VariableDto>>(){}.getType();
    }
    
    if ("VariableSchedulerDto".equalsIgnoreCase(className)) {
      return new TypeToken<List<VariableSchedulerDto>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("AMessage".equalsIgnoreCase(className)) {
      return new TypeToken<AMessage>(){}.getType();
    }
    
    if ("ARemoteMeMessage".equalsIgnoreCase(className)) {
      return new TypeToken<ARemoteMeMessage>(){}.getType();
    }
    
    if ("AVariableValue".equalsIgnoreCase(className)) {
      return new TypeToken<AVariableValue>(){}.getType();
    }
    
    if ("AndroidRegisterDto".equalsIgnoreCase(className)) {
      return new TypeToken<AndroidRegisterDto>(){}.getType();
    }
    
    if ("DeviceDto".equalsIgnoreCase(className)) {
      return new TypeToken<DeviceDto>(){}.getType();
    }
    
    if ("HelloDto".equalsIgnoreCase(className)) {
      return new TypeToken<HelloDto>(){}.getType();
    }
    
    if ("PlainResultDto".equalsIgnoreCase(className)) {
      return new TypeToken<PlainResultDto>(){}.getType();
    }
    
    if ("RegisterDto".equalsIgnoreCase(className)) {
      return new TypeToken<RegisterDto>(){}.getType();
    }
    
    if ("StandardResponse".equalsIgnoreCase(className)) {
      return new TypeToken<StandardResponse>(){}.getType();
    }
    
    if ("TokenDto".equalsIgnoreCase(className)) {
      return new TypeToken<TokenDto>(){}.getType();
    }
    
    if ("UserMessage".equalsIgnoreCase(className)) {
      return new TypeToken<UserMessage>(){}.getType();
    }
    
    if ("VariableDto".equalsIgnoreCase(className)) {
      return new TypeToken<VariableDto>(){}.getType();
    }
    
    if ("VariableSchedulerDto".equalsIgnoreCase(className)) {
      return new TypeToken<VariableSchedulerDto>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};

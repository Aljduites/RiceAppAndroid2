package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class ARemoteMeMessage  {
  
  public enum MessageTypeEnum {
     USER_MESSAGE,  USER_MESSAGE_DELIVER_STATUS,  USER_SYNC_MESSAGE,  VARIABLE_CHANGE_MESSAGE,  VARIABLE_CHANGE_PROPAGATE_MESSAGE,  SYNC_MESSAGE,  SYNC_MESSAGE_RESPONSE,  VARIABLE_OBSERVE_MESSAGE,  REGISTER_DEVICE,  REGISTER_CHILD_DEVICE,  ADD_DATA,  LOG,  SYSTEM_MESSAGE,  WEB_RTC_CONNECTION_CHANGE, 
  };
  @SerializedName("messageType")
  private MessageTypeEnum messageType = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public MessageTypeEnum getMessageType() {
    return messageType;
  }
  public void setMessageType(MessageTypeEnum messageType) {
    this.messageType = messageType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ARemoteMeMessage aRemoteMeMessage = (ARemoteMeMessage) o;
    return (this.messageType == null ? aRemoteMeMessage.messageType == null : this.messageType.equals(aRemoteMeMessage.messageType));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.messageType == null ? 0: this.messageType.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ARemoteMeMessage {\n");
    
    sb.append("  messageType: ").append(messageType).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

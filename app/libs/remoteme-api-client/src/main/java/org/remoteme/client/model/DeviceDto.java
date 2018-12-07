package org.remoteme.client.model;

import java.util.*;
import org.remoteme.client.model.DeviceDto;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class DeviceDto  {
  
  @SerializedName("active")
  private Boolean active = null;
  @SerializedName("childs")
  private List<DeviceDto> childs = null;
  public enum ConnectedEnum {
     CONNECTED,  NOT_CONNECTED,  NA, 
  };
  @SerializedName("connected")
  private ConnectedEnum connected = null;
  @SerializedName("deviceId")
  private Integer deviceId = null;
  @SerializedName("name")
  private String name = null;
  public enum TypeEnum {
     RASPBERRY_PI,  ARDUINO,  OTHER_NETWORK,  SMARTPHONE,  WEBPAGE,  SCRIPT,  LEAF_EXTERNAL_SCRIPT,  LEAF_OTHER,  OTHER, 
  };
  @SerializedName("type")
  private TypeEnum type = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getActive() {
    return active;
  }
  public void setActive(Boolean active) {
    this.active = active;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<DeviceDto> getChilds() {
    return childs;
  }
  public void setChilds(List<DeviceDto> childs) {
    this.childs = childs;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public ConnectedEnum getConnected() {
    return connected;
  }
  public void setConnected(ConnectedEnum connected) {
    this.connected = connected;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getDeviceId() {
    return deviceId;
  }
  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceDto deviceDto = (DeviceDto) o;
    return (this.active == null ? deviceDto.active == null : this.active.equals(deviceDto.active)) &&
        (this.childs == null ? deviceDto.childs == null : this.childs.equals(deviceDto.childs)) &&
        (this.connected == null ? deviceDto.connected == null : this.connected.equals(deviceDto.connected)) &&
        (this.deviceId == null ? deviceDto.deviceId == null : this.deviceId.equals(deviceDto.deviceId)) &&
        (this.name == null ? deviceDto.name == null : this.name.equals(deviceDto.name)) &&
        (this.type == null ? deviceDto.type == null : this.type.equals(deviceDto.type));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.active == null ? 0: this.active.hashCode());
    result = 31 * result + (this.childs == null ? 0: this.childs.hashCode());
    result = 31 * result + (this.connected == null ? 0: this.connected.hashCode());
    result = 31 * result + (this.deviceId == null ? 0: this.deviceId.hashCode());
    result = 31 * result + (this.name == null ? 0: this.name.hashCode());
    result = 31 * result + (this.type == null ? 0: this.type.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceDto {\n");
    
    sb.append("  active: ").append(active).append("\n");
    sb.append("  childs: ").append(childs).append("\n");
    sb.append("  connected: ").append(connected).append("\n");
    sb.append("  deviceId: ").append(deviceId).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

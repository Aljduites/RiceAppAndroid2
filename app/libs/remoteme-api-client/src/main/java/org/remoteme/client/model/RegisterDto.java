package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class RegisterDto  {
  
  @SerializedName("deviceId")
  private Integer deviceId = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getDeviceId() {
    return deviceId;
  }
  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterDto registerDto = (RegisterDto) o;
    return (this.deviceId == null ? registerDto.deviceId == null : this.deviceId.equals(registerDto.deviceId));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.deviceId == null ? 0: this.deviceId.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterDto {\n");
    
    sb.append("  deviceId: ").append(deviceId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

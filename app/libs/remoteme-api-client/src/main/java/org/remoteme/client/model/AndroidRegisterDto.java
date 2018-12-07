package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class AndroidRegisterDto  {
  
  @SerializedName("niceName")
  private String niceName = null;
  @SerializedName("smartPhoneId")
  private String smartPhoneId = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getNiceName() {
    return niceName;
  }
  public void setNiceName(String niceName) {
    this.niceName = niceName;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getSmartPhoneId() {
    return smartPhoneId;
  }
  public void setSmartPhoneId(String smartPhoneId) {
    this.smartPhoneId = smartPhoneId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AndroidRegisterDto androidRegisterDto = (AndroidRegisterDto) o;
    return (this.niceName == null ? androidRegisterDto.niceName == null : this.niceName.equals(androidRegisterDto.niceName)) &&
        (this.smartPhoneId == null ? androidRegisterDto.smartPhoneId == null : this.smartPhoneId.equals(androidRegisterDto.smartPhoneId));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.niceName == null ? 0: this.niceName.hashCode());
    result = 31 * result + (this.smartPhoneId == null ? 0: this.smartPhoneId.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AndroidRegisterDto {\n");
    
    sb.append("  niceName: ").append(niceName).append("\n");
    sb.append("  smartPhoneId: ").append(smartPhoneId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

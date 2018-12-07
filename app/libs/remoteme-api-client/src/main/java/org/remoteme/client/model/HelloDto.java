package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class HelloDto  {
  
  @SerializedName("helloResponse")
  private String helloResponse = null;
  @SerializedName("userName")
  private String userName = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getHelloResponse() {
    return helloResponse;
  }
  public void setHelloResponse(String helloResponse) {
    this.helloResponse = helloResponse;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HelloDto helloDto = (HelloDto) o;
    return (this.helloResponse == null ? helloDto.helloResponse == null : this.helloResponse.equals(helloDto.helloResponse)) &&
        (this.userName == null ? helloDto.userName == null : this.userName.equals(helloDto.userName));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.helloResponse == null ? 0: this.helloResponse.hashCode());
    result = 31 * result + (this.userName == null ? 0: this.userName.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class HelloDto {\n");
    
    sb.append("  helloResponse: ").append(helloResponse).append("\n");
    sb.append("  userName: ").append(userName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

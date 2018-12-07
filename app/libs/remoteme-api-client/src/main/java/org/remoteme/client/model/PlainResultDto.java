package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class PlainResultDto  {
  
  @SerializedName("success")
  private Boolean success = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getSuccess() {
    return success;
  }
  public void setSuccess(Boolean success) {
    this.success = success;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlainResultDto plainResultDto = (PlainResultDto) o;
    return (this.success == null ? plainResultDto.success == null : this.success.equals(plainResultDto.success));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.success == null ? 0: this.success.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlainResultDto {\n");
    
    sb.append("  success: ").append(success).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

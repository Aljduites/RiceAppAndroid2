package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class StandardResponse  {
  
  @SerializedName("succeed")
  private Boolean succeed = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getSucceed() {
    return succeed;
  }
  public void setSucceed(Boolean succeed) {
    this.succeed = succeed;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StandardResponse standardResponse = (StandardResponse) o;
    return (this.succeed == null ? standardResponse.succeed == null : this.succeed.equals(standardResponse.succeed));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.succeed == null ? 0: this.succeed.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StandardResponse {\n");
    
    sb.append("  succeed: ").append(succeed).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

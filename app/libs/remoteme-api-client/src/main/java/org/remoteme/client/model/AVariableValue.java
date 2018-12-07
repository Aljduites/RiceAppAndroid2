package org.remoteme.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class AVariableValue  {
  
  @SerializedName("dataSize")
  private Integer dataSize = null;
  public enum TypeEnum {
     BOOLEAN,  INTEGER,  TEXT,  SMALL_INTEGER_3,  SMALL_INTEGER_2,  INTEGER_BOOLEAN,  DOUBLE,  TEXT_2,  SMALL_INTEGER_2_TEXT_2, 
  };
  @SerializedName("type")
  private TypeEnum type = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getDataSize() {
    return dataSize;
  }
  public void setDataSize(Integer dataSize) {
    this.dataSize = dataSize;
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
    AVariableValue aVariableValue = (AVariableValue) o;
    return (this.dataSize == null ? aVariableValue.dataSize == null : this.dataSize.equals(aVariableValue.dataSize)) &&
        (this.type == null ? aVariableValue.type == null : this.type.equals(aVariableValue.type));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.dataSize == null ? 0: this.dataSize.hashCode());
    result = 31 * result + (this.type == null ? 0: this.type.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AVariableValue {\n");
    
    sb.append("  dataSize: ").append(dataSize).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

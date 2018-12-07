package org.remoteme.client.model;

import java.util.*;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class VariableSchedulerDto  {
  
  @SerializedName("cron")
  private String cron = null;
  public enum ModeEnum {
     CRON,  TIME, 
  };
  @SerializedName("mode")
  private ModeEnum mode = null;
  @SerializedName("nextTimeRun")
  private Long nextTimeRun = null;
  @SerializedName("time")
  private String time = null;
  @SerializedName("values")
  private List<String> values = null;
  @SerializedName("variableSchedulerId")
  private Integer variableSchedulerId = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getCron() {
    return cron;
  }
  public void setCron(String cron) {
    this.cron = cron;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public ModeEnum getMode() {
    return mode;
  }
  public void setMode(ModeEnum mode) {
    this.mode = mode;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Long getNextTimeRun() {
    return nextTimeRun;
  }
  public void setNextTimeRun(Long nextTimeRun) {
    this.nextTimeRun = nextTimeRun;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<String> getValues() {
    return values;
  }
  public void setValues(List<String> values) {
    this.values = values;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getVariableSchedulerId() {
    return variableSchedulerId;
  }
  public void setVariableSchedulerId(Integer variableSchedulerId) {
    this.variableSchedulerId = variableSchedulerId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableSchedulerDto variableSchedulerDto = (VariableSchedulerDto) o;
    return (this.cron == null ? variableSchedulerDto.cron == null : this.cron.equals(variableSchedulerDto.cron)) &&
        (this.mode == null ? variableSchedulerDto.mode == null : this.mode.equals(variableSchedulerDto.mode)) &&
        (this.nextTimeRun == null ? variableSchedulerDto.nextTimeRun == null : this.nextTimeRun.equals(variableSchedulerDto.nextTimeRun)) &&
        (this.time == null ? variableSchedulerDto.time == null : this.time.equals(variableSchedulerDto.time)) &&
        (this.values == null ? variableSchedulerDto.values == null : this.values.equals(variableSchedulerDto.values)) &&
        (this.variableSchedulerId == null ? variableSchedulerDto.variableSchedulerId == null : this.variableSchedulerId.equals(variableSchedulerDto.variableSchedulerId));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.cron == null ? 0: this.cron.hashCode());
    result = 31 * result + (this.mode == null ? 0: this.mode.hashCode());
    result = 31 * result + (this.nextTimeRun == null ? 0: this.nextTimeRun.hashCode());
    result = 31 * result + (this.time == null ? 0: this.time.hashCode());
    result = 31 * result + (this.values == null ? 0: this.values.hashCode());
    result = 31 * result + (this.variableSchedulerId == null ? 0: this.variableSchedulerId.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VariableSchedulerDto {\n");
    
    sb.append("  cron: ").append(cron).append("\n");
    sb.append("  mode: ").append(mode).append("\n");
    sb.append("  nextTimeRun: ").append(nextTimeRun).append("\n");
    sb.append("  time: ").append(time).append("\n");
    sb.append("  values: ").append(values).append("\n");
    sb.append("  variableSchedulerId: ").append(variableSchedulerId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

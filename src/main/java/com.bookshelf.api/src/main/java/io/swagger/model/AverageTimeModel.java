package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AverageTimeModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-18T21:15:44.869+03:00")

public class AverageTimeModel   {
  @JsonProperty("daysCount")
  private Integer daysCount = null;

  @JsonProperty("hoursCount")
  private Integer hoursCount = null;

  @JsonProperty("minutesCount")
  private Integer minutesCount = null;

  public AverageTimeModel daysCount(Integer daysCount) {
    this.daysCount = daysCount;
    return this;
  }

  /**
   * Get daysCount
   * @return daysCount
  **/
  @ApiModelProperty(value = "")


  public Integer getDaysCount() {
    return daysCount;
  }

  public void setDaysCount(Integer daysCount) {
    this.daysCount = daysCount;
  }

  public AverageTimeModel hoursCount(Integer hoursCount) {
    this.hoursCount = hoursCount;
    return this;
  }

  /**
   * Get hoursCount
   * @return hoursCount
  **/
  @ApiModelProperty(value = "")


  public Integer getHoursCount() {
    return hoursCount;
  }

  public void setHoursCount(Integer hoursCount) {
    this.hoursCount = hoursCount;
  }

  public AverageTimeModel minutesCount(Integer minutesCount) {
    this.minutesCount = minutesCount;
    return this;
  }

  /**
   * Get minutesCount
   * @return minutesCount
  **/
  @ApiModelProperty(value = "")


  public Integer getMinutesCount() {
    return minutesCount;
  }

  public void setMinutesCount(Integer minutesCount) {
    this.minutesCount = minutesCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AverageTimeModel averageTimeModel = (AverageTimeModel) o;
    return Objects.equals(this.daysCount, averageTimeModel.daysCount) &&
        Objects.equals(this.hoursCount, averageTimeModel.hoursCount) &&
        Objects.equals(this.minutesCount, averageTimeModel.minutesCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(daysCount, hoursCount, minutesCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AverageTimeModel {\n");
    
    sb.append("    daysCount: ").append(toIndentedString(daysCount)).append("\n");
    sb.append("    hoursCount: ").append(toIndentedString(hoursCount)).append("\n");
    sb.append("    minutesCount: ").append(toIndentedString(minutesCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


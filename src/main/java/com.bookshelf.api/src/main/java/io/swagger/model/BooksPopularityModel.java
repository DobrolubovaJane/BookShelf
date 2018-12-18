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
 * BooksPopularityModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-18T21:15:44.869+03:00")

public class BooksPopularityModel   {
  @JsonProperty("readersCount")
  private Integer readersCount = null;

  public BooksPopularityModel readersCount(Integer readersCount) {
    this.readersCount = readersCount;
    return this;
  }

  /**
   * Get readersCount
   * @return readersCount
  **/
  @ApiModelProperty(value = "")


  public Integer getReadersCount() {
    return readersCount;
  }

  public void setReadersCount(Integer readersCount) {
    this.readersCount = readersCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooksPopularityModel booksPopularityModel = (BooksPopularityModel) o;
    return Objects.equals(this.readersCount, booksPopularityModel.readersCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(readersCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BooksPopularityModel {\n");
    
    sb.append("    readersCount: ").append(toIndentedString(readersCount)).append("\n");
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


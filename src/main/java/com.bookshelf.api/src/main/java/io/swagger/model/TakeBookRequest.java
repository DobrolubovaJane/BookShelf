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
 * TakeBookRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-18T21:15:44.869+03:00")

public class TakeBookRequest   {
  @JsonProperty("bookId")
  private String bookId = null;

  public TakeBookRequest bookId(String bookId) {
    this.bookId = bookId;
    return this;
  }

  /**
   * Get bookId
   * @return bookId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TakeBookRequest takeBookRequest = (TakeBookRequest) o;
    return Objects.equals(this.bookId, takeBookRequest.bookId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TakeBookRequest {\n");
    
    sb.append("    bookId: ").append(toIndentedString(bookId)).append("\n");
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


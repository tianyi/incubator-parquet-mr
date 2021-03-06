package parquet.column.page;

import parquet.Ints;
import parquet.bytes.BytesInput;
import parquet.column.Encoding;
import parquet.column.statistics.Statistics;

public class DataPageV1 extends DataPage {

  private final BytesInput bytes;
  private final Statistics<?> statistics;
  private final Encoding rlEncoding;
  private final Encoding dlEncoding;
  private final Encoding valuesEncoding;

  /**
   * @param bytes the bytes for this page
   * @param valueCount count of values in this page
   * @param uncompressedSize the uncompressed size of the page
   * @param statistics of the page's values (max, min, num_null)
   * @param rlEncoding the repetition level encoding for this page
   * @param dlEncoding the definition level encoding for this page
   * @param valuesEncoding the values encoding for this page
   * @param dlEncoding
   */
  public DataPageV1(BytesInput bytes, int valueCount, int uncompressedSize, Statistics<?> stats, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) {
    super(Ints.checkedCast(bytes.size()), uncompressedSize, valueCount);
    this.bytes = bytes;
    this.statistics = stats;
    this.rlEncoding = rlEncoding;
    this.dlEncoding = dlEncoding;
    this.valuesEncoding = valuesEncoding;
  }

  /**
   * @return the bytes for the page
   */
  public BytesInput getBytes() {
    return bytes;
  }

  /**
   *
   * @return the statistics for this page (max, min, num_nulls)
   */
  public Statistics<?> getStatistics() {
    return statistics;
  }

  /**
   * @return the definition level encoding for this page
   */
  public Encoding getDlEncoding() {
    return dlEncoding;
  }

  /**
   * @return the repetition level encoding for this page
   */
  public Encoding getRlEncoding() {
    return rlEncoding;
  }

  /**
   * @return the values encoding for this page
   */
  public Encoding getValueEncoding() {
    return valuesEncoding;
  }

  @Override
  public String toString() {
    return "Page [bytes.size=" + bytes.size() + ", valueCount=" + getValueCount() + ", uncompressedSize=" + getUncompressedSize() + "]";
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visit(this);
  }
}

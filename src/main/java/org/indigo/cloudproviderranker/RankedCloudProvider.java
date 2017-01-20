package org.indigo.cloudproviderranker;

/**
 * Doc TODO.
 */
public class RankedCloudProvider {
  /**
   * Doc TODO.
   */
  private String  name;
  /**
   * Doc TODO.
   */
  private float   rank;
  /**
   * Doc TODO.
   */
  private boolean ranked;
  /**
   * Doc TODO.
   */
  private String  errorReason;

  /**
   * Doc TODO.
   */
  public RankedCloudProvider(final String name,  
                             final float rank,
                             final boolean ranked,
                             final String error) {
    this.name        = name;
    this.rank        = rank;
    this.errorReason = error;
    this.ranked      = ranked;
  }

  /**
   * Doc TODO.
   */
  public final String getName() {
    return name;
  }
  /**
   * Doc TODO.
   */
  public final float getRank() {
    return rank;
  }
  /**
   * Doc TODO.
   */
  public final boolean isRanked() {
    return ranked;
  }
  /**
   * Doc TODO.
   */
  public final String getRankError() {
    return errorReason;
  }
  /**
   * Doc TODO.
   */
  public final void addToRank(final float rankValue) {
    rank+= rankValue;
  }
}

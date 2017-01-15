package org.indigo.cloudproviderranker;

/**
 *
 * Class for a cloud provider which has been ranked (less info required comparing to CloudProvider)
 *
 */
public class RankedCloudProvider {
  private String  name;
  private float   rank;
  private boolean ranked;
  private String  errorReason;

  public RankedCloudProvider(final String name,  
                             final float rank,
                             final boolean ranked,
                             final String error) {
    this.name        = name;
    this.rank        = rank;
    this.errorReason = error;
    this.ranked      = ranked;
  }

  public final String getName() {
    return name;
  }
  public final float getRank() {
    return rank;
  }
  public final boolean isRanked() {
    return ranked;
  }
  public final String getRankError() {
    return errorReason;
  }
  public final void addToRank(final float f) {
    rank+= f;
  }
}

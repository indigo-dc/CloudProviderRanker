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
    
  public RankedCloudProvider(String name, float rank, boolean ranked, String error) {
    this.name        = name;
    this.rank        = rank;
    this.errorReason = error;
    this.ranked      = ranked;
  }
    
  public String  getName()      { return name; }
  public float   getRank()      { return rank; }
  public boolean isRanked()     { return ranked; }
  public String  getRankError() { return errorReason; }
  public void    addToRank(float f) { rank+= f; }
}

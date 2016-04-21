package org.indigo.cloudproviderruleengine;

/**
 *
 * Class for a cloud provider which has been ranked (less info required comparing to CloudProvider)
 *
 */
public class RankedCloudProvider {
    private int     id;
    private String  name;
    private float   rank;
    private boolean ranked;
    private String  errorReason;
    
    public RankedCloudProvider( int id, String name, float rank, boolean ranked, String error) {
	this.id          = id;
	this.name        = name;
	this.rank        = rank;
	this.errorReason = error;
	this.ranked      = ranked;
    }
    
    public int getID() { return id; }
    public String getName() { return name; }
    public float getRank( ) { return rank; }
    public boolean isRanked( ) { return ranked; }
    public String getRankError( ) { return errorReason; }
}

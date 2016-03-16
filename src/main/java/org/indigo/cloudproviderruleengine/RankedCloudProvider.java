package org.indigo.cloudproviderruleengine;

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
}

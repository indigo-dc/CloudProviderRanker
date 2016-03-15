package org.indigo.cloudproviderruleengine;

public class RankedCloudProvider {
	private int     id;
	private String  name;
	private float   rank;
	
	public RankedCloudProvider( int id, String name, float rank) {
		this.id   = id;
		this.name = name;
		this.rank = rank;
	}
}

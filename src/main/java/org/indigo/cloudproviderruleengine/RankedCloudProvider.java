package org.indigo.cloudproviderruleengine;

public class RankedCloudProvider {
	private int id;
	private String name;
	private long rank;
	
	public RankedCloudProvider( int id, String name, long rank) {
		this.id   = id;
		this.name = name;
		this.rank = rank;
	}
}

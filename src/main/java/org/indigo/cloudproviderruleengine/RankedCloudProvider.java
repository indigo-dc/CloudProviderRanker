package org.indigo.cloudproviderruleengine;

public class RankedCloudProvider {
	private int     id;
	private String  name;
	private double  rank;
	
	public RankedCloudProvider( int id, String name, double rank) {
		this.id   = id;
		this.name = name;
		this.rank = rank;
	}
}

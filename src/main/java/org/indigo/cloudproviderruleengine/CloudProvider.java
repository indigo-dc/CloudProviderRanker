package org.indigo.cloudproviderruleengine;

public class CloudProvider implements Comparable<CloudProvider> {
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(CloudProvider cp) {
	  if( this.totalRank > cp.totalRank ) return 1;
	  if( this.totalRank == cp.totalRank ) return 0;
	  return -1;
	}
	
	private int 	id;
	private String 	name;
	private double	totalRank;
	
	private int    	totalVCPU;
	private long    totalVRAM;
	private long    totalVDISK;
	private int     inUseVCPU;
	private long    inUseVRAM;
	private long    inUseVDISK;

	public int 	getID( )         { return id;         }
	public String 	getName( )       { return name;       }
	public int 	getTotalVCPU( )  { return totalVCPU;  }
	public long 	getTotalVRAM( )  { return totalVRAM;  }
	public long 	getTotalVDISK( ) { return totalVDISK; }
	public int 	getInUseVCPU ( ) { return inUseVCPU;  }
	public long 	getInUseVRAM( )  { return inUseVRAM;  }
	public long 	getInUseVDISK( ) { return inUseVDISK; }
	public double 	getTotalRank( )  { return totalRank;  }
	  
        public void addToTotalRank( double add ) {
	  totalRank += add;
	}

	public void zeroRank( ) {
		totalRank = 0;
	}

}

package org.indigo.cloudproviderruleengine;

/**
 *
 *
 * Class for a Cloud Provider as required by the server to perform the ranking
 * When the server receives a JSON text describing a cloud provider, the text is
 * automatically converted into an instance of this class by a Gson library's function
 *
 *
 */
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
    
    public static String ID            = "id";
    public static String NAME          = "name";
    public static String TOTALVCPU     = "totalVCPU";
    public static String TOTALVRAM     = "totalVRAM";
    public static String TOTALVDISK    = "totalVDISK";
    public static String TOTALVEPHDISK = "totalVEPHDISK";
    public static String INUSEVCPU     = "inUseVCPU";
    public static String INUSEVRAM     = "inUseVRAM";
    public static String INUSEVEPHDISK = "inUseVEPHDISK";
    public static String INUSEVDISK    = "inUseVDISK";

    
    private int     id;
    private String  name;
    private float   totalRank;
    
    private int     totalVCPU;
    private long    totalVRAM;
    private long    totalVEPHDISK;
    private long    totalVDISK;
    private int     inUseVCPU;
    private long    inUseVRAM;
    private long    inUseVEPHDISK;
    private long    inUseVDISK;

    private boolean goodParsing;
    private String  parseError;
    
    public int 	   getID( )            { return id;            }
    public String  getName( )          { return name;          }
    public int 	   getTotalVCPU( )     { return totalVCPU;     }
    public long    getTotalVRAM( )     { return totalVRAM;     }
    public long    getTotalVEPHDISK( ) { return totalVEPHDISK; }
    public long    getTotalVDISK( )    { return totalVDISK;    }
    public int 	   getInUseVCPU ( )    { return inUseVCPU;     }
    public long    getInUseVRAM( )     { return inUseVRAM;     }
    public long    getInUseVEPHDISK( ) { return inUseVEPHDISK; }
    public long    getInUseVDISK( )    { return inUseVDISK;    }
    public float   getTotalRank( )     { return totalRank;     }
    public String  getParseError( )    { return parseError;    }
    public boolean isGoodParsed( )     { return goodParsing;   }
    public void    setGoodParsed( )    { goodParsing = true;   }
    public void    setWrongParsed( )   { goodParsing = false;  }
    public void    setErrorMessage( String err ) { parseError = err; }

    public void addToTotalRank( float add ) {
	totalRank += add;
    }

    public void zeroRank( ) {
	totalRank = 0;
    }

}

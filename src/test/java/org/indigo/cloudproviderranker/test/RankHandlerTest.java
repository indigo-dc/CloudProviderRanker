package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.RankHandler;
import org.indigo.cloudproviderranker.ParseResult;

public class RankHandlerTest {
  @Test
  public void test( ) {
    RankHandler r = new RankHandler();
    //String toParse = "{\"preferences\":[]}";
    if(r!=null) {
	
	ParseResult response = r.parseRequest( "{\"preferences\":[]}" );
	//System.err.println("response="+response);
	response = r.parseRequest( "{\"preferences\":[{\"service_type\":\"compute\",\"priority\": [{\"sla_id\": \"1\",\"service_id\": \"a1\",\"weight\": 0.5}, {\"sla_id\": \"2\",\"service_id\": \"a2\",\"weight\": 0.5}]}],\"sla\":[{\"customer\":\"customer1\", \"provider\":\"provider1\",\"id\":\"1\",\"services\":[{\"type\":\"compute\",\"service_id\":\"1\",\"targets\": [{\"type\":\"computing_time\",\"unit\":\"h\", \"restrictions\": {}}]}]}]}" );
	response = r.parseRequest( "{\"sla\":[{\"customer\":\"customer1\", \"provider\":\"provider1\",\"id\":\"1\",\"services\":[{\"type\":\"compute\",\"service_id\":\"1\",\"targets\": [{\"type\":\"computing_time\",\"unit\":\"h\", \"restrictions\": {}}]}]}]}" );
	
	//System.err.println("response="+response);
    }

  }
}

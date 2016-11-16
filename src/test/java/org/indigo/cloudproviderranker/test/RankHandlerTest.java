package org.indigo.cloudproviderranker.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.indigo.cloudproviderranker.RankHandler;
import org.indigo.cloudproviderranker.ParseResult;
import java.io.StringBufferInputStream;

public class RankHandlerTest {
  @Test
  public void test( ) {
    RankHandler r = new RankHandler();
    //String toParse = "{\"preferences\":[]}";
    if(r!=null) {
	StringBufferInputStream s = new StringBufferInputStream("{\"preferences\":[]}" );
	ParseResult response = r.parseRequest( s/*"{\"preferences\":[]}"*/ );
	//System.err.println("response="+response);

	s = new StringBufferInputStream( "{\"preferences\":[{\"service_type\":\"compute\",\"priority\": [{\"sla_id\": \"1\",\"service_id\": \"a1\",\"weight\": 0.5}, {\"sla_id\": \"2\",\"service_id\": \"a2\",\"weight\": 0.5}]}],\"sla\":[{\"customer\":\"customer1\", \"provider\":\"provider1\",\"id\":\"1\",\"services\":[{\"type\":\"compute\",\"service_id\":\"1\",\"targets\": [{\"type\":\"computing_time\",\"unit\":\"h\", \"restrictions\": {}}]}]}]}" );

	response = r.parseRequest( s );

	s = new StringBufferInputStream( "{\"sla\":[{\"customer\":\"customer1\", \"provider\":\"provider1\",\"id\":\"1\",\"services\":[{\"type\":\"compute\",\"service_id\":\"1\",\"targets\": [{\"type\":\"computing_time\",\"unit\":\"h\", \"restrictions\": {}}]}]}]}" );

	response = r.parseRequest( s );
	
	//System.err.println("response="+response);
    }

  }
}

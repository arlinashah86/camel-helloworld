package my.com.arlinashah86;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SimpleRouteTest extends CamelTestSupport {
		 
	    @EndpointInject(uri = "mock:result")
	    protected MockEndpoint resultEndpoint;
	 
	    @Produce(uri = "direct:start")
	    protected ProducerTemplate template;
	 
	 
	    @Test
	    public void testSendMatchingMessage() throws Exception {
	        String expectedBody = "<matched/>";
	 
	        resultEndpoint.expectedBodiesReceived(expectedBody);
	 
	        template.sendBody(expectedBody);
	 
	        resultEndpoint.assertIsSatisfied();
	    }
	 	 
	    @Override
	    protected RouteBuilder createRouteBuilder() {
	        return new RouteBuilder() {
	            public void configure() {
	                from("direct:start").to("log:my.com.arlinashah86?level=DEBUG").to("mock:result");
	            }
	        };
	    }
	}




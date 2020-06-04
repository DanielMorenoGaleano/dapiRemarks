package com.avianca.pagos.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avianca.pagos.rest.dto.DataReq;
import com.avianca.pagos.rest.dto.RequestDTO;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 
 * @author Assert Solutions S.A.S <info@assertsolutions.com>
 *         <br/>
 *         Date: 9/04/2018 9:17:11 a.m.
 *
 */
public class dapiremarkTest extends CamelSpringTestSupport {

    private static final String PROPERTIES_FILE_DIR = "src/test/resources/";
    private static Properties testProperties = new Properties();
    
    @Test
    public void testRoute() throws Exception {
	final String fromRoute = "direct:fromRoute";

	context.getRouteDefinition("restServerRoute").adviceWith(context, new AdviceWithRouteBuilder() {
	    @Override
	    public void configure() throws Exception {
		replaceFromWith(fromRoute);
		weaveAddLast().log("Finishing the unit test of the route ").to("mock://endroute");
	    }
	});
	context.start();
	// Agregamos un mock endpoint
	MockEndpoint mockEndpoint = getMockEndpoint("mock://endroute");
	mockEndpoint.expectedMinimumMessageCount(1);
	RequestDTO dto = new RequestDTO();
//	DataReq datareq = new DataReq();
//	ArrayList<DataReq> data = new ArrayList<DataReq>();
	dto.setFreetext("COUPON MR2D2TBFGJRDIM2QGVLUE2BTLJ4HQZCIG5DXMNT2NFISKM3EEUZWI===");
	dto.setRemarkType("GeneralRemark");
//	data.add(datareq);
//	dto.setList(data);
	Map<String, Object> map = new HashMap<>();
	map.put("orderId", "J6FZ3G");
	map.put("lastName", "Perez A");		
	map.put("Auth", "UG2dsHcNZPaHKhfBmgUKQWWWFz6q");
	template.sendBodyAndHeaders(fromRoute, dto, map);
	mockEndpoint.assertIsSatisfied(2000L);
    }

    /**
     * Carga del archivo de propiedades para los Test Unitarios
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void init() throws Exception {
	testProperties.load(dapiremarkTest.class.getResourceAsStream("/dapiremark.properties"));
    } 
    
    @BeforeClass
    public static void setUpProperties() throws Exception {
	System.setProperty("karaf.home", PROPERTIES_FILE_DIR);
	System.setProperty("project.artifactId", "Test-Maven-Artifact");
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
	return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml",
		"META-INF/spring/properties-beans.xml");
    }

}

package jp.co.mti.mixjuke;


import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.springframework.web.context.ContextLoaderListener;

/**
 * User: nhphuoc
 * Date: 10/23/13
 * Time: 4:14 PM
 */

public class AbstractMixJukeJersey extends JerseyTest {
    @Override
    protected AppDescriptor configure() {
        System.setProperty(
                "jersey.test.containerFactory",
                "com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory");
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
                Boolean.TRUE);
        return new WebAppDescriptor.Builder("jp.co.mti.mixjuke.ws")
                .contextPath("/")
                .contextParam("contextConfigLocation",
                        "classpath:applicationContext.xml,classpath:database/hibernate.xml")
                .servletClass(SpringServlet.class)
                .initParam("com.sun.jersey.config.property.packages",
                        "jp.co.mti.mixjuke.ws")
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .clientConfig(clientConfig)
                .contextListenerClass(ContextLoaderListener.class).build();
    }
}

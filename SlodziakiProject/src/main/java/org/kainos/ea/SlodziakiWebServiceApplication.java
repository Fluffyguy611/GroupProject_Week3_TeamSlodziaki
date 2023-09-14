package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.ClientController;
import org.kainos.ea.resources.EmployeeController;
import org.kainos.ea.resources.ProjectController;

public class SlodziakiWebServiceApplication extends Application<SlodziakiWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SlodziakiWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "SłodziakiWebService";
    }

    @Override
    public void initialize(final Bootstrap<SlodziakiWebServiceConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new SwaggerBundle<SlodziakiWebServiceConfiguration>(){
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SlodziakiWebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final SlodziakiWebServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new EmployeeController());
        environment.jersey().register(new ProjectController());
        environment.jersey().register(new ClientController());
    }
}

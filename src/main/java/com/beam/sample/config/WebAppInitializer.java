package com.beam.sample.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;


public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		rootContext.setDisplayName("sample");

		// set session tracking mode
		container.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// add request context listener
		container.addListener(new RequestContextListener());

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebMvcConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(
				dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		System.out.println("Initialization done!");
		
		final FilterRegistration.Dynamic jaasFilter = container.addFilter("springSecurityFilterChain",
				new DelegatingFilterProxy());
		jaasFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

	}

}

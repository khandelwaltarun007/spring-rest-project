/*
 * package com.spring.rest.example1.configuration;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.hateoas.client.LinkDiscoverer; import
 * org.springframework.hateoas.client.LinkDiscoverers; import
 * org.springframework.hateoas.mediatype.collectionjson.
 * CollectionJsonLinkDiscoverer; import
 * org.springframework.plugin.core.SimplePluginRegistry;
 * 
 * import springfox.documentation.swagger2.annotations.EnableSwagger2;
 * 
 * @Configuration
 * 
 * @EnableSwagger2 public class SwaggerConfiguration {
 * 
 * This method generating error with hateoas
 * 
 * @Bean public Docket api() { return new
 * Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any
 * ()) .paths(PathSelectors.any()).build(); }
 * 
 * @Bean public LinkDiscoverers api() { List<LinkDiscoverer> plugins = new
 * ArrayList<>(); plugins.add(new CollectionJsonLinkDiscoverer()); return new
 * LinkDiscoverers(SimplePluginRegistry.create(plugins)); } }
 */
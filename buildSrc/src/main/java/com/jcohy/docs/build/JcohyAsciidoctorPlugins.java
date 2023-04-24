package com.jcohy.docs.build;

import java.util.Map;

import io.github.jcohy.gradle.asciidoctor.AsciidoctorConventionsPlugin;
import io.github.jcohy.gradle.conventions.ConventionsPlugin;
import io.github.jcohy.gradle.deployed.DeployedPlugin;
import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask;
import org.asciidoctor.gradle.jvm.AsciidoctorJPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;

/**
 * Copyright: Copyright (c) 2021 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 *
 * <p> Description:
 *
 * @author jiac
 * @version 1.0.0 2021/7/5:23:11
 * @since 1.0.0
 */
public class JcohyAsciidoctorPlugins implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        PluginContainer plugins = project.getPlugins();
        plugins.apply(AsciidoctorJPlugin.class);
        plugins.apply(AsciidoctorConventionsPlugin.class);
        plugins.apply(ConventionsPlugin.class);
        plugins.apply(DeployedPlugin.class);
        project.getTasks().withType(AbstractAsciidoctorTask.class,(asciidoctorTask) -> {
            configureAsciidoctorTask(project, asciidoctorTask);
            asciidoctorTask.setGroup("documentation");
        });
    }

    private void configureAsciidoctorTask(Project project, AbstractAsciidoctorTask asciidoctorTask) {
        asciidoctorTask.languages("zh-cn");
        configureCommonAttributes(project, asciidoctorTask);
    }

    private void configureCommonAttributes(Project project, AbstractAsciidoctorTask asciidoctorTask) {
        Map<String, Object> attributes = ProjectVersion.getAttributesMap();
        Map<String, Object> docsUrlMaps = ProjectVersion.getDocsUrlMaps();
        addAsciidoctorTaskAttributes(project,attributes);
        attributes.put("image-resource", project.getBuildDir() + "/docs/src/"+ asciidoctorTask.getName() + "/images");

        asciidoctorTask.attributes(attributes);
        asciidoctorTask.attributes(docsUrlMaps);
    }

    private void addAsciidoctorTaskAttributes(Project project,Map<String, Object> attributes) {
        attributes.put("docs-java", project.getProjectDir() + "/src/main/java/com/jcohy");
        attributes.put("docs-kotlin", project.getProjectDir() + "/src/main/kotlin/com/jcohy");
        attributes.put("docs-groovy", project.getProjectDir() + "/src/main/groovy/com/jcohy");


        attributes.put("rootProject", project.getRootProject().getProjectDir());
        attributes.put("sources-root", project.getProjectDir() + "/src");
        attributes.put("image-resource", "https://resources.jcohy.com/jcohy-docs/images/" + project.getVersion() + "/" + project.getName());
        attributes.put("spring-api-doc", "https://docs.spring.io/" + project.getName());
        attributes.put("doc-root","https://docs.jcohy.com");
        attributes.put("spring-docs-prefix","https://docs.spring.io/spring-framework/docs/");
        attributes.put("gh-samples-url","https://github.com/spring-projects/spring-security/master/");
    }
}

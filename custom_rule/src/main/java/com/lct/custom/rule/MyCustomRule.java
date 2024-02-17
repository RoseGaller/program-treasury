package com.lct.custom.rule;

import org.apache.maven.enforcer.rule.api.AbstractEnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;
import javax.inject.Named;

@Named("myCustomRule")
public class MyCustomRule extends AbstractEnforcerRule {

    @Inject
    private MavenProject project;

    @Override
    public void execute() throws EnforcerRuleException {
        int length = project.getVersion().split(".").length;
        getLog().debug(project.getVersion()+"模块的版本号");
        if(length != 3){
            throw new EnforcerRuleException("模块的版本号必须是数字三段式");
        }
    }
}

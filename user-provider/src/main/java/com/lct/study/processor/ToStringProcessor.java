package com.lct.study.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.lct.study.processor.ToString")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ToStringProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement currentAnnotation : annotations) {
            for (TypeElement annotatedElement : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(currentAnnotation))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(annotatedElement.getQualifiedName()).append(" [");

                for (VariableElement field : ElementFilter.fieldsIn(annotatedElement.getEnclosedElements())) {
                    stringBuilder.append(field.getSimpleName()).append(" = ").append(field.getSimpleName()).append(", ");
                }

                stringBuilder.append("]");
                messager.printMessage(Diagnostic.Kind.NOTE, stringBuilder.toString());
            }
        }
            return true;
    }
}

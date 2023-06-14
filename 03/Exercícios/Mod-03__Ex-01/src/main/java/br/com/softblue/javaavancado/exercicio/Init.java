package br.com.softblue.javaavancado.exercicio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation utilizada para indicar se um método deve ser invocado logo após a sua instanciação.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {

	boolean runOnInstantiation() default true;
}

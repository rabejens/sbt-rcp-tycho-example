package org.scalatest.junit;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.scalatest.Suite;

@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface ScalaTests {

	public Class<? extends Suite>[] value();
}

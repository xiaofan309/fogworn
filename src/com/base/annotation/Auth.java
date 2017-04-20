package com.base.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Documented
@Inherited
public @interface Auth
{
  boolean verifyLogin() default true;
  
  boolean verifyURL() default true;
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.annotation.Auth
 * JD-Core Version:    0.7.0.1
 */
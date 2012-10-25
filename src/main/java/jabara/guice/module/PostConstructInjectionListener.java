/**
 * 
 */
package jabara.guice.module;

import jabara.general.ExceptionUtil;
import jabara.general.ReflectionUtil;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import com.google.inject.spi.InjectionListener;

/**
 * 
 * @param <T> -
 * @author jabaraster
 */
class PostConstructInjectionListener<T> implements InjectionListener<T> {
    /**
     * @see com.google.inject.spi.InjectionListener#afterInjection(java.lang.Object)
     */
    @Override
    public void afterInjection(final T pInjectee) {
        for (final Method postConstructMethod : ReflectionUtil.getAnnotatedMethod(pInjectee.getClass(), PostConstruct.class)) {
            try {
                postConstructMethod.invoke(pInjectee);
            } catch (final Exception e) {
                throw ExceptionUtil.rethrow(e);
            }
        }
    }

}

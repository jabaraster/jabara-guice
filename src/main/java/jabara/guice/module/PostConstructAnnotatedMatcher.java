/**
 * 
 */
package jabara.guice.module;

import jabara.general.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;

/**
 * 
 * @author jabaraster
 */
public class PostConstructAnnotatedMatcher extends AbstractMatcher<TypeLiteral<?>> {

    private final ConcurrentMap<Class<?>, List<Method>> cache = new ConcurrentHashMap<Class<?>, List<Method>>();

    /**
     * @see com.google.inject.matcher.Matcher#matches(java.lang.Object)
     */
    @Override
    public boolean matches(final TypeLiteral<?> pT) {
        @SuppressWarnings("unchecked")
        final Class<? extends Object> type = (Class<? extends Object>) pT.getType();
        List<Method> b = this.cache.get(type);
        if (b != null) {
            return !b.isEmpty();
        }
        b = ReflectionUtil.getAnnotatedMethod(type, PostConstruct.class);
        this.cache.put(type, b);
        return !b.isEmpty();
    }

}

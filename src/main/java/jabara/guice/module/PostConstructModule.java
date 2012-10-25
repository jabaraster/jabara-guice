/**
 * 
 */
package jabara.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * @author jabaraster
 */
public class PostConstructModule extends AbstractModule {

    /**
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        final PostConstructAnnotatedMatcher matcher = new PostConstructAnnotatedMatcher();
        bindListener(matcher, new TypeListener() {
            @Override
            public <I> void hear(@SuppressWarnings("unused") final TypeLiteral<I> pType, final TypeEncounter<I> pEncounter) {
                pEncounter.register(new PostConstructInjectionListener<I>());
            }
        });
    }

}

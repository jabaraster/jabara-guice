/**
 * 
 */
package jabara.guice.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.PostConstruct;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * @author jabaraster
 */
public class LifecycleModuleTest {

    /**
     * 
     */
    @SuppressWarnings({ "static-method", "synthetic-access" })
    @Test
    public void _PostConstruct() {
        final Injector injector = Guice.createInjector(new PostConstructModule());

        assertTrue(injector.getInstance(PostConstructableClass.class).constructed);
        assertFalse(injector.getInstance(NonPostConstructableClass.class).constructed);
    }

    static class NonPostConstructableClass {

        private boolean constructed = false;

        void onPostConstruct() {
            this.constructed = true;
        }
    }

    static class PostConstructableClass {

        private boolean constructed = false;

        @PostConstruct
        void onPostConstruct() {
            this.constructed = true;
        }
    }
}

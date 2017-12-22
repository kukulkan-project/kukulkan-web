package mx.infotec.dads.kukulkan.engine.service.layers;

/**
 * Constants for Layer types in the generator
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Layer {

    private Layer() {

    }

    public static class FrontEnd {
        private FrontEnd() {
        }

        public static class Angular {
            public static final String SERVICE_NAME = "angular-js";

            private Angular() {
            }
        }
    }

    public static class Web {
        private Web() {

        }

        public static class SpringRest {
            public static final String SERVICE_NAME = "spring-rest";

            private SpringRest() {

            }

        }
    }

    public static class Business {
        private Business() {
        }

        public static class SpringService {
            public static final String SERVICE_NAME = "spring-service";

            private SpringService() {
            }

        }
    }

    public static class DataAccess {
        private DataAccess() {
        }

        public static class Repository {
            public static final String SERVICE_NAME = "spring-repository";
            private Repository() {

            }
        }
    }

    public static class Domain {
        private Domain() {
        }

        public static class Core {
            public static final String SERVICE_NAME = "domain-core";

            private Core() {

            }
        }
    }
}

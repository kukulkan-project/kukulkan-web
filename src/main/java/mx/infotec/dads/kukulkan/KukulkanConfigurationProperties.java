/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kukulkan")
public class KukulkanConfigurationProperties {
    private final Data data = new Data();
    private final Config config = new Config();

    public Data getData() {
        return data;
    }

    public Config getConfig() {
        return config;
    }

    public static class Data {

        private final Mysql mysql = new Mysql();

        public static class Mysql {
            private String uri;
            private String user;
            private String password;

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }

        public Mysql getMysql() {
            return mysql;
        }
    }

    public static class Config {

        private String inputdir;
        private String outputdir;
        private String documenturl;
        private ExternalApps externalApps;

        public static class ExternalApps {
            private String modeler;
            private String presentation;
            private String slides;

            public String getModeler() {
                return modeler;
            }

            public void setModeler(String modeler) {
                this.modeler = modeler;
            }

            public String getPresentation() {
                return presentation;
            }

            public void setPresentation(String presentation) {
                this.presentation = presentation;
            }

            public String getSlides() {
                return slides;
            }

            public void setSlides(String slides) {
                this.slides = slides;
            }
        }

        public String getInputdir() {
            return inputdir;
        }

        public void setInputdir(String inputdir) {
            this.inputdir = inputdir;
        }

        public String getOutputdir() {
            return outputdir;
        }

        public void setOutputdir(String outputdir) {
            this.outputdir = outputdir;
        }

        public String getDocumenturl() {
            return documenturl;
        }

        public void setDocumenturl(String documenturl) {
            this.documenturl = documenturl;
        }

        public ExternalApps getExternalApps() {
            return externalApps;
        }

        public void setExternalApps(ExternalApps externalApps) {
            this.externalApps = externalApps;
        }
    }
}

package mx.infotec.dads.kukulkan.config.dbmigrations;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import mx.infotec.dads.kukulkan.domain.Category;
import mx.infotec.dads.kukulkan.domain.Contact;
import mx.infotec.dads.kukulkan.domain.Service;

@ChangeLog(order = "002")
public class ServiceInfoMigration {

    @ChangeSet(order = "01", author = "initiator", id = "addCategories")
    public void addCategories(MongoTemplate mongoTemplate) {
        // Saving categories...
        Category infraestructuraCat = new Category();
        infraestructuraCat.setId("1");
        infraestructuraCat.setName("Infraestructura");
        infraestructuraCat.setDescription(
                "Utilizamos las mejores prácticas del mercado, mediante la adopción de tecnologías de vanguardia. Ofrecemos el uso o cambio de diversos sistemas, dependiendo de las necesidades de operación, garantizando que no existirán reproducciones totales o parciales de sistemas o programas extra fuera de tu institución. Nuestro objetivo es ofrecer un servicio tanto correctivo, adaptativo y perfectivo.");
        mongoTemplate.save(infraestructuraCat);

        Category serviciosIntegralesCat = new Category();
        serviciosIntegralesCat.setId("2");
        serviciosIntegralesCat.setName("Servicios Integrales en TIC");
        serviciosIntegralesCat.setDescription(
                "Ofrecemos a las organizaciones diferentes soluciones en Tecnologías de la Información y Comunicación (TIC) para dar soporte a sus actividades de negocio por medio de la mejora en sus sistemas informáticos. Nuestras soluciones están diseñadas para atender las necesidades específicas de cada cliente.");
        mongoTemplate.save(serviciosIntegralesCat);

        Category unidadGestionSeguridadCat = new Category();
        unidadGestionSeguridadCat.setId("3");
        unidadGestionSeguridadCat.setName("Unidad de Gestión de Seguridad");
        unidadGestionSeguridadCat.setDescription(
                "Ofrecemos a las organizaciones diferentes soluciones en Tecnologías de la Información y Comunicación (TIC) para dar soporte a sus actividades de negocio por medio de la mejora en sus sistemas informáticos. Nuestras soluciones están diseñadas para atender las necesidades específicas de cada cliente.");
        mongoTemplate.save(unidadGestionSeguridadCat);

        Category solucionesTecCat = new Category();
        solucionesTecCat.setId("4");
        solucionesTecCat.setName("Soluciones Tecnológicas");
        solucionesTecCat.setDescription(
                "Proporcionamos soluciones innovadoras tanto en el ámbito de las Tecnologías de la Información y Comunicación (TIC), como en la transformación y mejora de los procesos al interior de las organizaciones. Utilizamos metodologías, técnicas y modelos de vanguardia para garantizar la satisfacción de nuestros clientes.");
        mongoTemplate.save(solucionesTecCat);

        Category centroDatosCat = new Category();
        centroDatosCat.setId("5");
        centroDatosCat.setName("Centro de Datos");
        centroDatosCat.setDescription(
                "Contamos con dos centros de datos que ofrecen una amplia gama de servicios en materia de Tecnologías de la Información (TI), garantizando la alta disponibilidad y cumpliendo con los requerimientos mínimos de cada proyecto. Nuestras instalaciones cuentan con tecnología de punta y equipo seguro y confiable");
        mongoTemplate.save(centroDatosCat);

        Category consultoriaNegocioCat = new Category();
        consultoriaNegocioCat.setId("6");
        consultoriaNegocioCat.setName("Consultoría de Negocio");
        consultoriaNegocioCat.setDescription(
                "A través de los servicios de consultoría, ayudamos a las organizaciones a vincular las estrategias organizacionales con las de las Tecnologías de la Información y Comunicación (TIC), con un enfoque en las actividades sustantivas, la transformación digital y la generación de valor");
        mongoTemplate.save(consultoriaNegocioCat);

        Category servExtTICCat = new Category();
        servExtTICCat.setId("7");
        servExtTICCat.setName("Servicios Extendidos en TIC");
        servExtTICCat.setDescription(
                "A través de nuestro modelo de negocio de servicios extendidos en Tecnologías de la Información y Comunicación (TIC), proporcionamos reclutamiento, selección y administración de personal relacionado con las TIC, acorde a las necesidades y condiciones de tu organización y del mercado");
        mongoTemplate.save(servExtTICCat);

        Category servSoporteCat = new Category();
        servSoporteCat.setId("8");
        servSoporteCat.setName("Servicios de Soporte");
        servSoporteCat.setDescription("\n"
                + "Nuestros servicios de soporte están diseñados para simplificar y eficientar los servicios de Tecnologías de la Información y Comunicación (TIC) que brindan nuestros clientes. A través de diversas técnicas, identificamos áreas de oportunidad y mejora, respecto al desempeño de los servicios de cada organización.");
        mongoTemplate.save(servSoporteCat);

        // Saving contacts...
        Contact infraestructuraContact = new Contact();
        infraestructuraContact.setId("1");
        infraestructuraContact.setName("José Aurelio Muñoz Beltrán");
        infraestructuraContact.setEmail("aurelio.munoz@infotec.mx");
        infraestructuraContact.setPhone("5624 2800 ext. 4201");
        mongoTemplate.save(infraestructuraContact);

        Contact serviciosIntegralesContact = new Contact();
        serviciosIntegralesContact.setId("2");
        serviciosIntegralesContact.setName("Luis Emmanuel Martinez Borrayo");
        serviciosIntegralesContact.setEmail("luis.martinez@infotec.mx");
        serviciosIntegralesContact.setPhone("5624 2800 ext. 4416");
        mongoTemplate.save(serviciosIntegralesContact);

        Contact unidadGestionSeguridadContact = new Contact();
        unidadGestionSeguridadContact.setId("3");
        unidadGestionSeguridadContact.setName("Jorge Arturo Tokunaga Pérez");
        unidadGestionSeguridadContact.setEmail("jorge.tokunaga@infotec.mx");
        unidadGestionSeguridadContact.setPhone("4202 2800 ext. 4962");
        mongoTemplate.save(unidadGestionSeguridadContact);

        Contact solucionesTecContact = new Contact();
        solucionesTecContact.setId("4");
        solucionesTecContact.setName("Daniel Cortés Pichardo");
        solucionesTecContact.setEmail("daniel.cortes@infotec.mx");
        solucionesTecContact.setPhone("5624 2800 ext. 4712");
        mongoTemplate.save(solucionesTecContact);

        Contact centroDatosContact = new Contact();
        centroDatosContact.setId("5");
        centroDatosContact.setName("Ricardo Israel Romero Salazar");
        centroDatosContact.setEmail("ricardo.romero@infotec.mx");
        centroDatosContact.setPhone("5624 2800 ext. 6314");
        mongoTemplate.save(centroDatosContact);

        Contact consultoriaNegocioContact = new Contact();
        consultoriaNegocioContact.setId("6");
        consultoriaNegocioContact.setName("Carlos Alberto Ricaño Alcalá");
        consultoriaNegocioContact.setEmail("carlos.ricano@infotec.mx");
        consultoriaNegocioContact.setPhone("5624 2800 ext. 5219");
        mongoTemplate.save(consultoriaNegocioContact);

        Contact servExtTICContact = new Contact();
        servExtTICContact.setId("7");
        servExtTICContact.setName("Sergio Arturo Martínez Peña");
        servExtTICContact.setEmail("sergio.martinez@infotec.mx");
        servExtTICContact.setPhone("5624 2800 ext. 5220");
        mongoTemplate.save(servExtTICContact);

        Contact servSoporteContact = new Contact();
        servSoporteContact.setId("8");
        servSoporteContact.setName("Francisco José Ronquillo Arrona");
        servSoporteContact.setEmail("francisco.ronquillo@infotec.mx");
        servSoporteContact.setPhone("Tel: 5624 2856 ext. 4404");
        mongoTemplate.save(servSoporteContact);

        // Saving contacts in categories
        infraestructuraCat.setContact(infraestructuraContact);
        mongoTemplate.save(infraestructuraCat);

        serviciosIntegralesCat.setContact(serviciosIntegralesContact);
        mongoTemplate.save(serviciosIntegralesCat);

        unidadGestionSeguridadCat.setContact(unidadGestionSeguridadContact);
        mongoTemplate.save(unidadGestionSeguridadCat);

        solucionesTecCat.setContact(solucionesTecContact);
        mongoTemplate.save(solucionesTecCat);

        centroDatosCat.setContact(solucionesTecContact);
        mongoTemplate.save(centroDatosCat);

        consultoriaNegocioCat.setContact(consultoriaNegocioContact);
        mongoTemplate.save(consultoriaNegocioCat);

        servExtTICCat.setContact(servExtTICContact);
        mongoTemplate.save(servExtTICCat);

        servSoporteCat.setContact(servSoporteContact);
        mongoTemplate.save(servSoporteCat);

        // Saving services...
        Service serviceAdminServidores = new Service();
        serviceAdminServidores.setId("1");
        serviceAdminServidores.setName("Administración de Servidores");
        serviceAdminServidores.setDescription(
                "Nuestro servicio especializado en la administración de servidores consiste en la operación, administración, actualización y mantenimiento lógico de los siguientes sistemas operativos:");
        serviceAdminServidores.setCategory(infraestructuraCat);
        serviceAdminServidores.setUrl("https://www.infotec.mx/es_mx/infotec/administracion_de_servidores");
        mongoTemplate.save(serviceAdminServidores);

        Service serviceAlmacYProc = new Service();
        serviceAlmacYProc.setId("2");
        serviceAlmacYProc.setName("Almacenamiento y procesamiento");
        serviceAlmacYProc.setDescription(
                "Nuestro servicio de almacenamiento y procesamiento integra los recursos informáticos como la memoria, procesador, almacenamiento, administración y control de parámetros con soluciones de respaldos.\n"
                        + "\n"
                        + "Operamos en nuestro Centro de Datos con certificación Tier III, ubicado en Aguascalientes, bajo los lineamientos del Uptime Institute y las normas internacional y mexicana de gestión de servicios de Tecnologías de la Información: ISO-/IEC 20000-1:2011 y NMX-I-20000-1-NYCE-2012");
        serviceAlmacYProc.setCategory(infraestructuraCat);
        serviceAlmacYProc.setUrl("https://www.infotec.mx/es_mx/infotec/almacenamiento_y_procesamiento");
        mongoTemplate.save(serviceAlmacYProc);

        Service serviceIntegralesTIC = new Service();
        serviceIntegralesTIC.setId("3");
        serviceIntegralesTIC.setName("Administración de base de datos y servicios web");
        serviceIntegralesTIC.setDescription(
                "Proporcionamos soluciones integrales para la administración de base de datos y aplicaciones web, mediante la actualización de aplicaciones y nuevas versiones de software. Nos comprometemos en mantener operando, de manera óptima, tus servicios de administración de dominios de internet, de e-mail, de contenidos de sitios web y redes sociales, de acuerdo con tus necesidades");
        serviceIntegralesTIC.setCategory(serviciosIntegralesCat);
        serviceIntegralesTIC
                .setUrl("https://www.infotec.mx/es_mx/infotec/administracion_de_base_de_datos_y_servicios_web");
        mongoTemplate.save(serviceIntegralesTIC);

        Service serviceTelcomYRedes = new Service();
        serviceTelcomYRedes.setId("4");
        serviceTelcomYRedes.setName("Telecomunicaciones y redes");
        serviceTelcomYRedes.setDescription(
                "Proporcionamos servicios administrados de telecomunicaciones y conectividad, asegurando la confiabilidad y disponibilidad de acuerdo con las necesidades de nuestros clientes y bajo las metodologías de gestión de Tecnologías de la Información y Comunicación (TIC) como ISO 20000, ITIL, 27000,TIER III, COBIT, entre otras.\n"
                        + "\n"
                        + "Contamos con soporte técnico de segundo nivel para la atención y administración de equipos (hardware y software) de transporte de datos (detección de fallas y corrección).");
        serviceTelcomYRedes.setCategory(serviciosIntegralesCat);
        serviceTelcomYRedes.setUrl("https://www.infotec.mx/es_mx/infotec/telecomunicaciones_y_redes");
        mongoTemplate.save(serviceTelcomYRedes);

        Service serviceCentroOpSeguridad = new Service();
        serviceCentroOpSeguridad.setId("5");
        serviceCentroOpSeguridad.setName("Centro de Operaciones y Seguridad");
        serviceCentroOpSeguridad.setDescription(
                "Nuestro Centro de Operaciones de Seguridad (SOC) está compuesto por personas certificadas en Tecnologías de la Información (TI), en estándares (ISO) y herramientas tecnológicas, quienes se encargan de administrar, tanto de forma reactiva como proactiva, las amenazas y vulnerabilidades a tu información.\n"
                        + "\n"
                        + "A través de nuestro centro, brindamos la atención de reportes, fallas, incidentes y requerimientos de infraestructura de seguridad perimetral.\n"
                        + "\n"
                        + "Contamos con soporte técnico de segundo nivel para la atención y administración de equipos (hardware y software) de transporte de datos (detección de fallas y corrección).");
        serviceCentroOpSeguridad.setCategory(unidadGestionSeguridadCat);
        serviceCentroOpSeguridad.setUrl("https://www.infotec.mx/es_mx/infotec/centro_de_operaciones_de_seguridad_soc");
        mongoTemplate.save(serviceCentroOpSeguridad);

        Service serviceCiberSeguridad = new Service();
        serviceCiberSeguridad.setId("6");
        serviceCiberSeguridad.setName("Ciberseguridad");
        serviceCiberSeguridad.setDescription(
                "Ofrecemos a nuestros clientes un panorama general de seguridad para tomar decisiones sobre la creación de campañas de seguridad que mitiguen vulnerabilidades, tomar medidas de prevención de incidentes y asegurar la destrucción de la información obsoleta en sus equipos.");
        serviceCiberSeguridad.setCategory(unidadGestionSeguridadCat);
        serviceCiberSeguridad.setUrl("https://www.infotec.mx/es_mx/infotec/ciberseguridad");
        mongoTemplate.save(serviceCiberSeguridad);

        Service serviceNormaSeguridad = new Service();
        serviceNormaSeguridad.setId("7");
        serviceNormaSeguridad.setName("Normatividad de seguridad");
        serviceNormaSeguridad.setDescription(
                "Apoyamos al aseguramiento de la información, ubicando los riesgos inherentes del manejo de la misma, y establecemos el proceso para preservar la confidencialidad, integridad y disponibilidad de la información.\n");
        serviceNormaSeguridad.setCategory(unidadGestionSeguridadCat);
        serviceNormaSeguridad.setUrl("https://www.infotec.mx/es_mx/infotec/normatividad_de_seguridad");
        mongoTemplate.save(serviceNormaSeguridad);

        Service serviceDesSoftAltaCalidad = new Service();
        serviceDesSoftAltaCalidad.setId("8");
        serviceDesSoftAltaCalidad.setName("Desarrollo de software de alta calidad");
        serviceDesSoftAltaCalidad.setDescription(
                "Implementamos soluciones de alto valor agregado mediante la transformación de las ideas y necesidades de nuestros clientes. Hacemos más eficiente la automatización de los procesos de negocio y funcionalidades identificadas por nuestros especialistas.\n");
        serviceDesSoftAltaCalidad.setCategory(solucionesTecCat);
        serviceDesSoftAltaCalidad.setUrl("https://www.infotec.mx/es_mx/infotec/Desarrollo_de_software_de_alta_calidad");
        mongoTemplate.save(serviceDesSoftAltaCalidad);

        Service serviceBigDataanalitica = new Service();
        serviceBigDataanalitica.setId("9");
        serviceBigDataanalitica.setName("Big Data y analítica");
        serviceBigDataanalitica.setDescription(
                "En INFOTEC, entendemos los retos que hoy en día enfrentan las organizaciones en torno a la acumulación de grandes volúmenes de información. Por ello, ofrecemos el servicio de big data y analítica para la gestión de grandes cantidades de datos y de los procedimientos usados para encontrar patrones repetitivos dentro de éstos.\n");
        serviceBigDataanalitica.setCategory(solucionesTecCat);
        serviceBigDataanalitica.setUrl("https://www.infotec.mx/es_mx/infotec/big_data_y_analitica");
        mongoTemplate.save(serviceBigDataanalitica);

        Service serviceArqSoftware = new Service();
        serviceArqSoftware.setId("10");
        serviceArqSoftware.setName("Arquitectura de software");
        serviceArqSoftware.setDescription(
                "A través de nuestro servicio de arquitectura de software, buscamos maximizar los beneficios del uso de aplicaciones desarrolladas por terceros o in-house, a través de la estandarización de las mejores prácticas y tecnologías en el mercado.\n"
                        + "\n"
                        + "Alineamos los objetivos de la organización con los requerimientos técnicos de la solución, reflejándolos en las aplicaciones por medio de evaluaciones de arquitecturas, pruebas de carga y estrés, etcétera..\n");
        serviceArqSoftware.setCategory(solucionesTecCat);
        serviceArqSoftware.setUrl("https://www.infotec.mx/es_mx/infotec/arquitectura_de_software");
        mongoTemplate.save(serviceArqSoftware);

        Service serviceAsegCalidad = new Service();
        serviceAsegCalidad.setId("11");
        serviceAsegCalidad.setName("Aseguramiento de la calidad");
        serviceAsegCalidad.setDescription(
                "Contamos con especialistas de pruebas de software, comprometidos con la visión de servicio y calidad necesaria para garantizar el funcionamiento de las aplicaciones, siguiendo las principales metodologías y herramientas de pruebas.\n"
                        + "\n"
                        + "Alineamos los objetivos de la organización con los requerimientos técnicos de la solución, reflejándolos en las aplicaciones por medio de evaluaciones de arquitecturas, pruebas de carga y estrés, etcétera..\n");
        serviceAsegCalidad.setCategory(solucionesTecCat);
        serviceAsegCalidad.setUrl("https://www.infotec.mx/es_mx/infotec/aseguramiento_de_la_calidad");
        mongoTemplate.save(serviceAsegCalidad);

        Service serviceImplSolEmpresariales = new Service();
        serviceImplSolEmpresariales.setId("12");
        serviceImplSolEmpresariales.setName("Implementación de soluciones empresariales");
        serviceImplSolEmpresariales.setDescription(
                "Brindamos a nuestros clientes la capacidad para mejorar la productividad y competitividad de su organización o negocio, a través de la implementación y personalización de soluciones empresariales y de servicios de consultoría para la automatización de sus procesos.\n"
                        + "\n"
                        + "Establecemos oportunidades de mejora a través del análisis de problemas, identificación de requisitos y gestión de cambios.\n");
        serviceImplSolEmpresariales.setCategory(solucionesTecCat);
        serviceImplSolEmpresariales
                .setUrl("https://www.infotec.mx/es_mx/infotec/implementacion_de_soluciones_empresariales");
        mongoTemplate.save(serviceImplSolEmpresariales);

        Service serviceEvolSoftwareAltaCalidad = new Service();
        serviceEvolSoftwareAltaCalidad.setId("13");
        serviceEvolSoftwareAltaCalidad.setName("Evolución de software de alta calidad");
        serviceEvolSoftwareAltaCalidad.setDescription(
                "Gestionamos de manera estratégica aplicaciones ya en operación mediante procesos bien definidos que permiten llevar el mantenimiento de manera segura y controlada, minimizando el impacto en la operación y afectación a los usuarios finales.\n"
                        + "\n"
                        + "Nuestros procesos llevan la trazabilidad de cada afectación desde que ingresa la solicitud de cambio, a través de cada una de las fases de atención y verificación hasta su liberación final.\n");
        serviceEvolSoftwareAltaCalidad.setCategory(solucionesTecCat);
        serviceEvolSoftwareAltaCalidad
                .setUrl("https://www.infotec.mx/es_mx/infotec/evolucion_de_software_de_alta_calidad");
        mongoTemplate.save(serviceEvolSoftwareAltaCalidad);

        Service serviceDiseñoPortalWeb = new Service();
        serviceDiseñoPortalWeb.setId("14");
        serviceDiseñoPortalWeb.setName("Diseño de portales web");
        serviceDiseñoPortalWeb.setDescription(
                "Desarrollamos portales web para cualquier tipo de proyecto. Nuestro enfoque se basa en la metodología UX (Experiencia de Usuario), la cual se centra en las necesidades e intereses de los usuarios, aumentando la satisfacción y facilidad de uso.\n"
                        + "\n"
                        + "Trabajamos de manera metódica para la satisfacción de nuestros clientes, no sólo con la creación y actualización periódica de contenidos, sino también dando acompañamiento para el posicionamiento de éstos y con la generación de estadísticas de indicadores clave así como su interpretación, extendiendo la comunicación o difusión con los usuarios por medio de las redes sociales.\n");
        serviceDiseñoPortalWeb.setCategory(solucionesTecCat);
        serviceDiseñoPortalWeb.setUrl("https://www.infotec.mx/es_mx/infotec/diseno_de_portales_web");
        mongoTemplate.save(serviceDiseñoPortalWeb);

        Service serviceCentroDatosSanFernando = new Service();
        serviceCentroDatosSanFernando.setId("15");
        serviceCentroDatosSanFernando.setName("Centro de datos San Fernando");
        serviceCentroDatosSanFernando.setDescription(
                "Contamos con un Centro de Datos en la Ciudad de México, que cumple 21 años de operar de forma ininterrumpida. Cuenta con una disponibilidad de 99.89% al año de efectividad. Además del excelente servicio que oferta, ha servido como legado del conocimiento donde han surgido grandes talentos en el diseño, construcción y operación de centros de datos.\n"
                        + "\n"
                        + "Nuestro Centro de Datos cuenta con el premio al \"Reconocimiento especial al equipo de data center del año\" y al \"Liderazgo en el sector público\" otorgado por el Data Center Dynamics en su Latam Awards 2014.\n");
        serviceCentroDatosSanFernando.setCategory(centroDatosCat);
        serviceCentroDatosSanFernando.setUrl("https://www.infotec.mx/es_mx/infotec/centro_de_datos_san_fernando");
        mongoTemplate.save(serviceCentroDatosSanFernando);

        Service serviceCentroDatosAgs = new Service();
        serviceCentroDatosAgs.setId("15");
        serviceCentroDatosAgs.setName("Centro de Datos Aguascalientes TIER III");
        serviceCentroDatosAgs.setDescription(
                "Contamos con un Centro de Datos ubicado en la ciudad de Aguascalientes, certificado en el nivel TIER III emitido por el Uptime Institute.\n"
                        + "\n"
                        + "Aseguramos el máximo rendimiento, nivel óptimo de disponibilidad, balanceo de cargas, respaldo de energía y seguridad para hacer frente a tus recursos de información.\n");
        serviceCentroDatosAgs.setCategory(centroDatosCat);
        serviceCentroDatosAgs.setUrl("https://www.infotec.mx/es_mx/infotec/centro_de_datos_aguascalientes_tier_iii");
        mongoTemplate.save(serviceCentroDatosAgs);

        Service serviceEstrategiaTIC = new Service();
        serviceEstrategiaTIC.setId("16");
        serviceEstrategiaTIC.setName("Estrategia en TIC");
        serviceEstrategiaTIC.setDescription(
                "Definimos las estrategias que servirán como guía para la operación eficiente y efectiva de las áreas de Tecnologías de la Información y Comunicación (TIC), enfocándonos, principalmente, en aquellas que atienden a la Estrategia Digital Nacional.\n");
        serviceEstrategiaTIC.setCategory(consultoriaNegocioCat);
        serviceEstrategiaTIC.setUrl("https://www.infotec.mx/es_mx/infotec/estrategia_en_tic");
        mongoTemplate.save(serviceEstrategiaTIC);

        Service serviceMejoraProcesos = new Service();
        serviceMejoraProcesos.setId("17");
        serviceMejoraProcesos.setName("Mejora de procesos en TIC");
        serviceMejoraProcesos.setDescription(
                "Analizamos y optimizamos la operación de las áreas de Tecnologías de la Información y Comunicación (TIC) alineadas a las mejores prácticas y marcos normativos como son ITIL, COBIT, PMBOK y MAAGTIC-SI y TOGAF para asegurar que las organizaciones incorporen en su cadena de valor las TIC y aumenten la capacidad de cumplir con sus objetivos estratégicos.\n");
        serviceMejoraProcesos.setCategory(consultoriaNegocioCat);
        serviceMejoraProcesos.setUrl("https://www.infotec.mx/es_mx/infotec/mejora_de_procesos_en_tic");
        mongoTemplate.save(serviceMejoraProcesos);

        Service serviceOptimizacionOp = new Service();
        serviceOptimizacionOp.setId("18");
        serviceOptimizacionOp.setName("Optimización de la operación");
        serviceOptimizacionOp.setDescription(
                "Nuestro modelo nos permite incorporar elementos de valor a la organización por medio de la optimización de sus operaciones y el uso de las Tecnologías de la Información y Comunicación (TIC).\n"
                        + "\n"
                        + "Implementamos herramientas que apoyen los procesos de tu organización, como la automatización de procesos, generación de tableros de indicadores, operación de la contabilidad gubernamental con apego a la normatividad establecida por el Consejo Nacional de Armonización Contable (Conac), la gestión de redes sociales, entre otros.\n");
        serviceOptimizacionOp.setCategory(consultoriaNegocioCat);
        serviceOptimizacionOp.setUrl("https://www.infotec.mx/es_mx/infotec/optimizacion_de_la_operacion");
        mongoTemplate.save(serviceOptimizacionOp);

        Service serviceExtendidosTIC = new Service();
        serviceExtendidosTIC.setId("19");
        serviceExtendidosTIC.setName("Servicios extendidos en TIC");
        serviceExtendidosTIC.setDescription(
                "A través de nuestro modelo de negocio de servicios extendidos en Tecnologías de la Información y Comunicación (TIC), proporcionamos reclutamiento, selección y administración de personal relacionado con las TIC, acorde a las necesidades y condiciones de tu organización y del mercado.\n");
        serviceExtendidosTIC.setCategory(servExtTICCat);
        serviceExtendidosTIC.setUrl("https://www.infotec.mx/es_mx/infotec/servicios_extendidos_en_tic");
        mongoTemplate.save(serviceExtendidosTIC);

        Service serviceCentroMonitoreo = new Service();
        serviceCentroMonitoreo.setId("20");
        serviceCentroMonitoreo.setName("Centro Nacional de Monitoreo");
        serviceCentroMonitoreo.setDescription(
                "Nos encargamos de prevenir y predecir la ocurrencia de eventos e incidentes para vigilar el buen comportamiento de las Tecnologías de la Información y Comunicación (TIC) que existen dentro de una red, mediante la extracción y recolección de datos e indicadores relacionados con el comportamiento vital de los diferentes elementos de configuración, existentes dentro de una red de cómputo.\n");
        serviceCentroMonitoreo.setCategory(servSoporteCat);
        serviceCentroMonitoreo.setUrl("https://www.infotec.mx/es_mx/infotec/centro_nacional_de_monitoreo");
        mongoTemplate.save(serviceCentroMonitoreo);

        Service serviceStreaming = new Service();
        serviceStreaming.setId("21");
        serviceStreaming.setName("Streaming");
        serviceStreaming.setDescription(
                "Ofrecemos la transmisión de contenido de audio y videos a través de internet para eventos, en tiempo real, empresariales o educativos, los cuales son informativos y sin interacción entre usuarios.\n");
        serviceStreaming.setCategory(servSoporteCat);
        serviceStreaming.setUrl("https://www.infotec.mx/es_mx/infotec/streaming");
        mongoTemplate.save(serviceStreaming);

        Service serviceMesaServicio = new Service();
        serviceMesaServicio.setId("22");
        serviceMesaServicio.setName("Puntos de contacto y mesa de servicio");
        serviceMesaServicio.setDescription(
                "Las organizaciones requieren de servicios que respondan de una manera oportuna y eficiente a sus eventos de Tecnologías de la Información y Comunicación (TIC), por medio de un sólo punto de contacto.\n"
                        + "\n"
                        + "A través de la Mesa Central de Servicios INFOTEC (MCSI), la cual fungirá como el único punto de contacto entre INFOTEC y el cliente, ofreceremos la recepción de eventos de TIC (incidentes, solicitudes de servicio, problemas, cambios, etcétera.)\n");
        serviceMesaServicio.setCategory(servSoporteCat);
        serviceMesaServicio.setUrl("https://www.infotec.mx/es_mx/infotec/puntos_de_contacto_y_mesa_de_servicio");
        mongoTemplate.save(serviceMesaServicio);

    }

}

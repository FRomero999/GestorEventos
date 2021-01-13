module com.paco.gestoreventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.logging;
    requires java.sql;
    requires java.xml.bind;
    requires java.naming;
    requires org.jboss.logging;
    
    opens com.paco.gestoreventos to javafx.fxml, org.hibernate.orm.core;
    opens models;
    exports com.paco.gestoreventos;
}

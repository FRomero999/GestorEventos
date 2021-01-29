module com.paco.gestoreventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.hibernate.orm.core;
    requires java.logging;
    requires java.sql;
    requires java.xml.bind;
    requires java.naming;
    requires org.jboss.logging;
    requires javafx.swing;
    requires jasperreports;
    requires java.base;
    
    opens com.paco.gestoreventos to javafx.fxml, org.hibernate.orm.core, javafx.swing, java.sql, java.web;
    opens models;
    exports com.paco.gestoreventos;
}
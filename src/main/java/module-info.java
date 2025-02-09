module org.andrejsuharew.valutpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires com.google.gson;
    requires jdk.compiler;

    opens org.andrejsuharew.valutpro to javafx.fxml;
    exports org.andrejsuharew.valutpro;
}
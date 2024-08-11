module vsb.aku.tanks {
	requires transitive javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
    opens vsb.aku.tanks to javafx.fxml;
    exports vsb.aku.tanks.model;
    exports vsb.aku.tanks.controller;
    exports vsb.aku.tanks;
}

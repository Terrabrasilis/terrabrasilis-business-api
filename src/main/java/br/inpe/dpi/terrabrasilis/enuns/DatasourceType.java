package br.inpe.dpi.terrabrasilis.enuns;

import java.util.stream.Stream;

/**
 * @author Jether Rois
 */
public enum DatasourceType {
    OWS("OWS"),
    TMS("TMS"),
    WMS("WMS");

    private String type;

    DatasourceType(String type) {               
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static Stream<DatasourceType> stream() {
        return Stream.of(DatasourceType.values()); 
    }
    
}
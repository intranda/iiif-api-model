package de.intranda.api.annotation;

import de.intranda.api.annotation.wa.TypedResource;

public class GeoLocation extends TypedResource {

    private Geometry geometry;
    private Properties properties;
    private ViewPoint view;
    
    public GeoLocation(Geometry geometry, Properties properties, ViewPoint view) {
        super(null, "Feature", "application/json");
        this.geometry = geometry;
        this.properties = properties;
        this.view = view;
    }
    
    public Geometry getGeometry() {
        return geometry;
    }
    
    public Properties getProperties() {
        return properties;
    }
    
    public ViewPoint getView() {
        return view;
    }
    
    public static class Properties {
        private String name;
        
        public Properties(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    public static class Geometry {
        
        private double[] coordinates;
        private String type;
        
        public Geometry(double[] coordinates, String type) {
            this.type = type;
            this.coordinates = coordinates;
        }
        
        public double[] getCoordinates() {
            return coordinates;
        }
        
        public String getType() {
            return type;
        }
    }
    
    public static class ViewPoint {
        
        private double[] center;
        private double zoom;
        
        public ViewPoint(double zoom, double[] center) {
            this.zoom = zoom;
            this.center = center;
        }
        
        public double getZoom() {
            return zoom;
        }
        
        public double[] getCenter() {
            return center;
        }
    }

}

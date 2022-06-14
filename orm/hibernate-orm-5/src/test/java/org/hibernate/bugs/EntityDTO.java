package org.hibernate.bugs;

import java.net.URI;

public class EntityDTO {
    String label;

    URI uri;

    public EntityDTO(String label, URI uri) {
        this.label = label;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return (label != null ? (label + ": ") : "") + uri.toASCIIString();
    }
}

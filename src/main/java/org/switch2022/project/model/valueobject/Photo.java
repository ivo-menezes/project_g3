package org.switch2022.project.model.valueobject;

import java.util.Objects;

public class Photo {

    private final String photoName;

    public Photo(String photoName) {
        if(photoName == null || photoName.isBlank() || photoName.isEmpty())
            throw new IllegalArgumentException("photoName cannot be null/blank/empty");

        this.photoName = photoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo that = (Photo) o;
        return Objects.equals(photoName, that.photoName);
    }

    @Override
    public int hashCode() { return Objects.hash(photoName);}
}

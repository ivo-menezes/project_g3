package org.switch2022.project.model.valueobject;

import java.util.Objects;

public class Photo {

    private final String photo;

    public Photo(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Photo)) {return false;}
        Photo that = (Photo) o;
        return Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() { return Objects.hash(photo);}

    @Override
    public String toString() {
        return photo;
    }
}

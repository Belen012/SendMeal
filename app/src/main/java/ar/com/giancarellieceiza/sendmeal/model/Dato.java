package ar.com.giancarellieceiza.sendmeal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dato {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private int value;

    public Dato (int value) {
        this.value = value;
    }

    public void setValue (int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}

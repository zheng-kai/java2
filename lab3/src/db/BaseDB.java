package db;

import annotation.Column;

import java.lang.annotation.Inherited;

public abstract class BaseDB {
    @Column("id")
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

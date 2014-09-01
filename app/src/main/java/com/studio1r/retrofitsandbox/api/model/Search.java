package com.studio1r.retrofitsandbox.api.model;

import java.io.Serializable;

public class Search implements Serializable {

    public static final long serialVersionUID = 1L;
    public String name;
    public String internal;
    public Group[] groups;
    public Search() {
    }
}

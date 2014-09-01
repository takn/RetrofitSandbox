package com.studio1r.retrofitsandbox.api.model;

import java.io.Serializable;

/**
 *
 */
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    public String name;
    public String internal;
    public Group[] groups;
}

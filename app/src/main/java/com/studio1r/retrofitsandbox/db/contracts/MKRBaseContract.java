package com.studio1r.retrofitsandbox.db.contracts;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;

/**
 * Created by nelsonramirez on 9/2/14.
 */
public class MKRBaseContract implements ProviGenBaseContract {

    @Column(Column.Type.TEXT)
    public static final String INTERNAL = "_internal";

    @Column(Column.Type.TEXT)
    public static final String SERIALIZED_OBJECT = "serialized_object";

    @Column(Column.Type.TEXT)
    public static final String NAME = "_name";

    @Column(Column.Type.INTEGER)
    public static final String PREFERRED_SORT_INDEX = "preferred_sort_index";

    @Column(Column.Type.TEXT)
    public static final String CODE = "_code";
}

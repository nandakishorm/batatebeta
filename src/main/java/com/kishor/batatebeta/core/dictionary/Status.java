package com.kishor.batatebeta.core.dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandakishor on 8/11/2015.
 */
public enum Status {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    DELETED;

    public static Status[] getValues() {
        return new Status[]{Status.ACTIVE, Status.INACTIVE, Status.BLOCKED};
    }
}

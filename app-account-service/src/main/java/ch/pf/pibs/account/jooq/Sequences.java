/*
 * This file is generated by jOOQ.
 */
package ch.pf.pibs.account.jooq;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Sequences {

    /**
     * The sequence <code>public.account_seq</code>
     */
    public static final Sequence<Long> ACCOUNT_SEQ = Internal.createSequence("account_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), null, 50, null, null, false, null);
}

package it.gov.scuolesuperioridizagarolo.model.bitorario;

import java.util.TreeSet;

/**
 * Created by stefano on 29/06/2017.
 */
public abstract class ClassroomFilter {
    public static final ClassroomFilter BIENNIO() {
        return new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                return c.classNumber() <= 2;
            }
        };
    }

    public static final ClassroomFilter TRIENNIO() {
        return new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                return c.classNumber() > 2;
            }
        };
    }

    public static final ClassroomFilter LICEO_SCIENZE_APPLICATE() {
        return new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                return c.flagScienzeApplicate;
            }
        };
    }

    public static final ClassroomFilter LICEO_TRADIZIONALE() {
        return new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                return !c.flagScienzeApplicate;
            }
        };
    }

    public static final ClassroomFilter TUTTO() {
        return new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                return true;
            }
        };
    }

    public ClassroomFilter and(final ClassroomFilter... filters) {
        ClassroomFilter ris = new ClassroomFilter() {
            @Override
            protected boolean _doFilter(Classroom c) {
                if (!ClassroomFilter.this._doFilter(c)) return false;

                for (ClassroomFilter x : filters) {
                    if (!x._doFilter(c)) return false;
                }
                return true;
            }
        };
        return ris;
    }

    public final TreeSet<Classroom> filter() {
        TreeSet<Classroom> ris = new TreeSet<>();
        for (Classroom c : Classroom.values()) {
            if (_doFilter(c))
                ris.add(c);
        }
        return ris;
    }

    protected abstract boolean _doFilter(Classroom c);
}

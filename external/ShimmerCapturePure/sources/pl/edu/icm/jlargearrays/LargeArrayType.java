package pl.edu.icm.jlargearrays;

/* loaded from: classes2.dex */
public enum LargeArrayType {
    LOGIC,
    BYTE { // from class: pl.edu.icm.jlargearrays.LargeArrayType.1

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isIntegerNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }
    },
    UNSIGNED_BYTE { // from class: pl.edu.icm.jlargearrays.LargeArrayType.2

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isIntegerNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }
    },
    SHORT { // from class: pl.edu.icm.jlargearrays.LargeArrayType.3

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isIntegerNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 2L;
        }
    },
    INT { // from class: pl.edu.icm.jlargearrays.LargeArrayType.4

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isIntegerNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 4L;
        }
    },
    LONG { // from class: pl.edu.icm.jlargearrays.LargeArrayType.5

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isIntegerNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 8L;
        }
    },
    FLOAT { // from class: pl.edu.icm.jlargearrays.LargeArrayType.6

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isRealNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 4L;
        }
    },
    DOUBLE { // from class: pl.edu.icm.jlargearrays.LargeArrayType.7

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isRealNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 8L;
        }
    },
    COMPLEX_FLOAT { // from class: pl.edu.icm.jlargearrays.LargeArrayType.8

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isComplexNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 4L;
        }
    },
    COMPLEX_DOUBLE { // from class: pl.edu.icm.jlargearrays.LargeArrayType.9

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isComplexNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public boolean isNumericType() {
            return true;
        }

        @Override // pl.edu.icm.jlargearrays.LargeArrayType
        public long sizeOf() {
            return 8L;
        }
    },
    STRING,
    OBJECT;

    public boolean isComplexNumericType() {
        return false;
    }

    public boolean isIntegerNumericType() {
        return false;
    }

    public boolean isNumericType() {
        return false;
    }

    public boolean isRealNumericType() {
        return false;
    }

    public long sizeOf() {
        return 1L;
    }
}

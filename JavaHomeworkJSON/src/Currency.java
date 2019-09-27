class Currency {
    private int id;
    private String code;
    private String name_short;
    private String name_full;

    Currency(int id, String code, String name_short, String name_full) {
        this.id = id;
        this.code = code;
        this.name_short = name_short;
        this.name_full = name_full;
    }

    String getCode() {
        return code;
    }
}

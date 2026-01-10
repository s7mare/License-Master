package top.gzii.license.enums;

public enum CheckModeEnum {
    GLOBAL("global"),
    ANNOTATION("annotation"),
    NONE("none");

    private final String name;

    public String getName() {
        return this.name;
    }


        CheckModeEnum(String name){
        this.name=name;
    }
}

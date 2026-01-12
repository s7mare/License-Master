package top.gzii.license.enums;


/**
 *  证书模式枚举
 */
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

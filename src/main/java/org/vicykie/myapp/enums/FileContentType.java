package org.vicykie.myapp.enums;

/**
 * Created by vicykie on 2016/6/7.
 */
public enum FileContentType implements EnumInterface {
    UNKONWN(0, "unkonwn"), IMG_GIF(1, "gif"), IMG_JPEG(2, "jpeg"), IMG_JPG(3, "jpg"), OFFICE_DOC(4, "doc"),
    IMG_BMP(5, "bmp"), OFFIC_EXCEL(6, "excel"), OFFIC_PPT(7, "ppt"),
    ZIP_ZIP(8, "zip"), ZIP_RAR(9, "rar"),
    FILE_TXT(10, "txt"),
    IMG_PNG(11, "png"),;
    private int code;
    private String text;

    FileContentType(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setCode(int code) {
        this.code =code;
    }
}

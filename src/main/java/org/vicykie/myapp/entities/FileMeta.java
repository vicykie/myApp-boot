package org.vicykie.myapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vicykie.myapp.enums.FileContentType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vicykie on 2016/6/7.
 */
@Document(collection = "file_meta")
public class FileMeta implements Serializable {
    @Id
    private String id;
    private String path;
    private int size;
    private FileContentType type;
    private String originalName;
    private String reName;
    private String diskLocation;
    private Date uploadDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FileContentType getType() {
        return type;
    }

    public void setType(FileContentType type) {
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public String getDiskLocation() {
        return diskLocation;
    }

    public void setDiskLocation(String diskLocation) {
        this.diskLocation = diskLocation;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}

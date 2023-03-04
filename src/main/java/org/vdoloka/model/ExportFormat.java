package org.vdoloka.model;

public enum ExportFormat {
    PDF("application/pdf", "pdf"),
    XLSX("application/octet-stream", "xlsx"),
    DOCX("application/octet-stream", "docx");

    private final String mimeType;
    private final String fileExtension;

    ExportFormat(String mimeType, String fileExtension) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}

package pl.aplazuk.companyonline.domain.entities.document;

public enum DocumentStatus {

    UNSPECIFIED("Niezmodyfikowany"),
    SPECIFIED("Zmodyfikowany"),
    PAID("Zapłacony"),
    BOOKED("Zaksięgowany");


    private final String description;

    DocumentStatus(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}

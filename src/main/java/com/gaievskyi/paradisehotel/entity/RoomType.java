package com.gaievskyi.paradisehotel.entity;

public enum RoomType {
    SINGLE("src/app/images/rooms/Single.jpg","A room which has single bed facility."),
    DOUBLE("src/app/images/rooms/Double.jpg","A room which has double bed facility."),
    TWIN("src/app/images/rooms/Twin.jpg","A room which has two single bed separated by a center table."),
    FAMILY("src/app/images/rooms/Family.jpg","Two rooms which shares a common door, mostly used by families."),
    PRESIDENTIAL("src/app/images/rooms/Presidential.jpg","Presidential Suite features two lavish bedrooms with king-sized beds, a marble bathroom with a Jacuzzi, expansive areas for living and dining.");

    private final String imgURL;
    private final String description;

    RoomType(String imgURL, String description){
        this.imgURL = imgURL;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }
}

package com.edify.hunterhint.models;

public class FindRequest {
    private String region;
    private String district;
    private String huntingResource;
    private String hotel;
    private String bath;
    private String checkInDate;
    private String leaveDate;
    private String noDate;
    private String guests;
    private String hunters;
    private String price;
    private String method;
    private String guiding;

    public FindRequest() {
    }

    public String getRegion() {
        return this.region;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getHuntingResource() {
        return this.huntingResource;
    }

    public String getHotel() {
        return this.hotel;
    }

    public String getBath() {
        return this.bath;
    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public String getLeaveDate() {
        return this.leaveDate;
    }

    public String getNoDate() {
        return this.noDate;
    }

    public String getGuests() {
        return this.guests;
    }

    public String getHunters() {
        return this.hunters;
    }

    public String getPrice() {
        return this.price;
    }

    public String getMethod() {
        return this.method;
    }

    public String getGuiding() {
        return this.guiding;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setHuntingResource(String huntingResource) {
        this.huntingResource = huntingResource;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setNoDate(String noDate) {
        this.noDate = noDate;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public void setHunters(String hunters) {
        this.hunters = hunters;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setGuiding(String guiding) {
        this.guiding = guiding;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FindRequest)) return false;
        final FindRequest other = (FindRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        if (this$region == null ? other$region != null : !this$region.equals(other$region)) return false;
        final Object this$district = this.getDistrict();
        final Object other$district = other.getDistrict();
        if (this$district == null ? other$district != null : !this$district.equals(other$district)) return false;
        final Object this$huntingResource = this.getHuntingResource();
        final Object other$huntingResource = other.getHuntingResource();
        if (this$huntingResource == null ? other$huntingResource != null : !this$huntingResource.equals(other$huntingResource))
            return false;
        final Object this$hotel = this.getHotel();
        final Object other$hotel = other.getHotel();
        if (this$hotel == null ? other$hotel != null : !this$hotel.equals(other$hotel)) return false;
        final Object this$bath = this.getBath();
        final Object other$bath = other.getBath();
        if (this$bath == null ? other$bath != null : !this$bath.equals(other$bath)) return false;
        final Object this$checkInDate = this.getCheckInDate();
        final Object other$checkInDate = other.getCheckInDate();
        if (this$checkInDate == null ? other$checkInDate != null : !this$checkInDate.equals(other$checkInDate))
            return false;
        final Object this$leaveDate = this.getLeaveDate();
        final Object other$leaveDate = other.getLeaveDate();
        if (this$leaveDate == null ? other$leaveDate != null : !this$leaveDate.equals(other$leaveDate)) return false;
        final Object this$noDate = this.getNoDate();
        final Object other$noDate = other.getNoDate();
        if (this$noDate == null ? other$noDate != null : !this$noDate.equals(other$noDate)) return false;
        final Object this$guests = this.getGuests();
        final Object other$guests = other.getGuests();
        if (this$guests == null ? other$guests != null : !this$guests.equals(other$guests)) return false;
        final Object this$hunters = this.getHunters();
        final Object other$hunters = other.getHunters();
        if (this$hunters == null ? other$hunters != null : !this$hunters.equals(other$hunters)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
        final Object this$guiding = this.getGuiding();
        final Object other$guiding = other.getGuiding();
        if (this$guiding == null ? other$guiding != null : !this$guiding.equals(other$guiding)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FindRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $region = this.getRegion();
        result = result * PRIME + ($region == null ? 43 : $region.hashCode());
        final Object $district = this.getDistrict();
        result = result * PRIME + ($district == null ? 43 : $district.hashCode());
        final Object $huntingResource = this.getHuntingResource();
        result = result * PRIME + ($huntingResource == null ? 43 : $huntingResource.hashCode());
        final Object $hotel = this.getHotel();
        result = result * PRIME + ($hotel == null ? 43 : $hotel.hashCode());
        final Object $bath = this.getBath();
        result = result * PRIME + ($bath == null ? 43 : $bath.hashCode());
        final Object $checkInDate = this.getCheckInDate();
        result = result * PRIME + ($checkInDate == null ? 43 : $checkInDate.hashCode());
        final Object $leaveDate = this.getLeaveDate();
        result = result * PRIME + ($leaveDate == null ? 43 : $leaveDate.hashCode());
        final Object $noDate = this.getNoDate();
        result = result * PRIME + ($noDate == null ? 43 : $noDate.hashCode());
        final Object $guests = this.getGuests();
        result = result * PRIME + ($guests == null ? 43 : $guests.hashCode());
        final Object $hunters = this.getHunters();
        result = result * PRIME + ($hunters == null ? 43 : $hunters.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $method = this.getMethod();
        result = result * PRIME + ($method == null ? 43 : $method.hashCode());
        final Object $guiding = this.getGuiding();
        result = result * PRIME + ($guiding == null ? 43 : $guiding.hashCode());
        return result;
    }

    public String toString() {
        return "FindRequest(region=" + this.getRegion() + ", district=" + this.getDistrict() + ", huntingResource=" + this.getHuntingResource() + ", hotel=" + this.getHotel() + ", bath=" + this.getBath() + ", checkInDate=" + this.getCheckInDate() + ", leaveDate=" + this.getLeaveDate() + ", noDate=" + this.getNoDate() + ", guests=" + this.getGuests() + ", hunters=" + this.getHunters() + ", price=" + this.getPrice() + ", method=" + this.getMethod() + ", guiding=" + this.getGuiding() + ")";
    }
}
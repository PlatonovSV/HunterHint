package com.edify.hunterhint.models;

public class FindOfferRequest {
    private String huntingResource;
    private String checkInDate;
    private String leaveDate;
    private String noDate;
    private String guests;
    private String hunters;
    private String method;
    private String guiding;
    private String groundId;

    public FindOfferRequest() {
    }

    public String getHuntingResource() {
        return this.huntingResource;
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

    public String getMethod() {
        return this.method;
    }

    public String getGuiding() {
        return this.guiding;
    }

    public String getGroundId() {
        return this.groundId;
    }

    public void setHuntingResource(String huntingResource) {
        this.huntingResource = huntingResource;
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

    public void setMethod(String method) {
        this.method = method;
    }

    public void setGuiding(String guiding) {
        this.guiding = guiding;
    }

    public void setGroundId(String groundId) {
        this.groundId = groundId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FindOfferRequest)) return false;
        final FindOfferRequest other = (FindOfferRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$huntingResource = this.getHuntingResource();
        final Object other$huntingResource = other.getHuntingResource();
        if (this$huntingResource == null ? other$huntingResource != null : !this$huntingResource.equals(other$huntingResource))
            return false;
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
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
        final Object this$guiding = this.getGuiding();
        final Object other$guiding = other.getGuiding();
        if (this$guiding == null ? other$guiding != null : !this$guiding.equals(other$guiding)) return false;
        final Object this$groundId = this.getGroundId();
        final Object other$groundId = other.getGroundId();
        if (this$groundId == null ? other$groundId != null : !this$groundId.equals(other$groundId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FindOfferRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $huntingResource = this.getHuntingResource();
        result = result * PRIME + ($huntingResource == null ? 43 : $huntingResource.hashCode());
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
        final Object $method = this.getMethod();
        result = result * PRIME + ($method == null ? 43 : $method.hashCode());
        final Object $guiding = this.getGuiding();
        result = result * PRIME + ($guiding == null ? 43 : $guiding.hashCode());
        final Object $groundId = this.getGroundId();
        result = result * PRIME + ($groundId == null ? 43 : $groundId.hashCode());
        return result;
    }

    public String toString() {
        return "FindOfferRequest(huntingResource=" + this.getHuntingResource() + ", checkInDate=" + this.getCheckInDate() + ", leaveDate=" + this.getLeaveDate() + ", noDate=" + this.getNoDate() + ", guests=" + this.getGuests() + ", hunters=" + this.getHunters() + ", method=" + this.getMethod() + ", guiding=" + this.getGuiding() + ", groundId=" + this.getGroundId() + ")";
    }
}

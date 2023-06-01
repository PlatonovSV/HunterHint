package com.edify.hunterhint.controllers;

import com.edify.hunterhint.models.*;
import com.edify.hunterhint.repositories.*;
import com.edify.hunterhint.services.HuntingFarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    private final String[] methodArr = new String[]{"Все способы", "Вольерная охота", "Натаска и нагонка собак",
            "Организация загонной охоты", "Организация охоты на реву", "Организация охоты с гончими собаками",
            "Организация охоты с засидки", "Организация охоты с лайками", "Организация охоты с легавыми собаками",
            "Организация охоты с луком/арболетом", "Организация охоты с подхода", "Самостоятельная охота"};

    private final String[] guidingArr = new String[]{"Не выбрано", "Охота с егерем", "Охота без сопровождения", "Частичное сопровождение"};

    private final HuntingFarmService huntingFarmService;
    private final HuntingFarmRep huntingFarmRep;
    private final HuntingOfferRep offerRep;
    private final MunicipalDistrictRep districtRep;
    private final RegionRep regionRep;
    private final HuntingResourcesRep resourcesRep;
    private final BookingRep bookingRep;
    private final ResourcesCostRep resourcesCostRep;
    private final ImageLinkRep imageLinkRep;
    private final CompanyNameRep companyNameRep;


    @PostMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HuntingFarm> findGround(@RequestBody FindRequest request) {
        String region = request.getRegion();
        String district = request.getDistrict();
        String huntingResource = request.getHuntingResource();
        String hotel = request.getHotel();
        String bath = request.getBath();
        String checkInDate = request.getCheckInDate();
        String leaveDate = request.getLeaveDate();
        String noDate = request.getNoDate();
        String guests = request.getGuests();
        String hunters = request.getHunters();
        String price = request.getPrice();
        String method = request.getMethod();
        String guiding = request.getGuiding();

        short quantity = (short) (Integer.parseInt(guests) + Integer.parseInt(hunters));
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate leave = LocalDate.parse(leaveDate);
        if (noDate != null) {
            checkIn = LocalDate.MIN;
            leave = LocalDate.MIN;
        }
        boolean hotelBol = hotel != null && hotel.equals("on");
        boolean bathBol = bath != null && bath.equals("on");

        List<HuntingFarm> huntingFarms = huntingFarmService.findByRegionAndMd(region, district);
        huntingFarms = huntingFarmService.bathAndHotelSearch(hotelBol, quantity, bathBol, huntingFarms);
        huntingFarms = huntingFarmService.findByDateAndCostAndOther(huntingFarms, checkIn, leave, quantity, hotelBol,
                huntingResource, Integer.parseInt(price), Integer.parseInt(guiding), Integer.parseInt(method));
        for (HuntingFarm farm : huntingFarms) {
            farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
            farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());
            List<ImageLink> imageLinks = imageLinkRep.findAllByOwnerId(farm.getId());
            if (imageLinks != null && !imageLinks.isEmpty()) {
                farm.setPreview("images/" + imageLinks.get(0).getId());
            } else {
                farm.setPreview("img/lion2.jpg");
            }
        }
        return huntingFarms;
    }

    @PostMapping(value = "/find-offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HuntingOffer> findGround(@RequestBody FindOfferRequest request) {
        String huntingResource = request.getHuntingResource();
        String checkInDate = request.getCheckInDate();
        String leaveDate = request.getLeaveDate();
        String noDate = request.getNoDate();
        String guests = request.getGuests();
        String hunters = request.getHunters();
        String method = request.getMethod();
        String guiding = request.getGuiding();
        String groundId = request.getGroundId();

        short quantity = (short) (Integer.parseInt(guests) + Integer.parseInt(hunters));
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate leave = LocalDate.parse(leaveDate);

        int guidingInt = Integer.parseInt(guiding);
        int methodInt = Integer.parseInt(method);

        if (checkIn.isAfter(leave)) {
            leave = LocalDate.MIN;
            checkIn = LocalDate.MIN;
        }

        HuntingFarm farm = huntingFarmRep.findById((long) Integer.parseInt(groundId)).get();

        farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
        farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());
        farm.setCompanyStr(companyNameRep.findById((long) farm.getCompanyId()).get().getName());

        int hotelPrice = 0;
        if (farm.isHotel()) {
            hotelPrice += quantity * farm.getAccommodationCost() * DAYS.between(checkIn, leave);
        }

        List<HuntingOffer> offers = offerRep.findByFarmId(Integer.parseInt(groundId));

        List<HuntingOffer> findOffers = new ArrayList<>();

        for (HuntingOffer offer : offers) {
            boolean corresponds = true;

            String offerResourcesType = resourcesRep.findById((long) offer.getResourcesTypeId()).get().getName();
            if (!huntingResource.equals(offerResourcesType) && !huntingResource.isEmpty()) {
                corresponds = false;
            } else {
                offer.setResourcesTypeName(offerResourcesType);
            }

            if (corresponds) {
                if (offer.getMethodId() == methodInt || methodInt == 0) {
                    offer.setMethodName(methodArr[offer.getMethodId()]);
                } else {
                    corresponds = false;
                }
            }

            if (corresponds) {
                if (offer.getGuidingPreferenceId() == guidingInt || guidingInt == 0) {
                    offer.setGuidingPreferenceName(guidingArr[offer.getGuidingPreferenceId()]);
                } else {
                    corresponds = false;
                }
            }

            if (corresponds) {
                if (farm.getHotelCapacity() < quantity) {
                    corresponds = false;
                }
            }

            if (corresponds) {
                if (farm.getMaxNumberHunters() < Integer.parseInt(hunters)) {
                    corresponds = false;
                }
            }

            if (corresponds) {
                if (noDate == null) {
                    if (!((offer.getClosingDate().isAfter(leave) || offer.getClosingDate().isEqual(leave)) && (offer.getOpeningDate().isBefore(checkIn) || offer.getOpeningDate().isEqual(checkIn)))) {
                        corresponds = false;
                    }
                }
            }

            if (corresponds) {
                offer.setMinCost(hotelPrice + offer.getEventCost());
                findOffers.add(offer);
            }
        }
        return findOffers;
    }
}
